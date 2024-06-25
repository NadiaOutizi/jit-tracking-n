package com.jobintechtracking.app.services.Impl;

import com.jobintechtracking.app.DTO.StepWithStatusDTO;
import com.jobintechtracking.app.entities.Steps;
import com.jobintechtracking.app.entities.Student;
import com.jobintechtracking.app.entities.StudentStep;
import com.jobintechtracking.app.repositories.StepRepository;
import com.jobintechtracking.app.repositories.StudentRepository;
import com.jobintechtracking.app.repositories.StudentStepRepository;
import com.jobintechtracking.app.services.facade.StudentStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentStepServiceImpl implements StudentStepService {

    private static final Logger logger = LoggerFactory.getLogger(StudentStepServiceImpl.class);

    @Autowired
    private StudentStepRepository studentStepRepository;

    @Autowired
    private StepRepository stepsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentStep> getStudentSteps(Long studentId) {
        logger.info("Retrieving steps for student ID: {}", studentId);
        return studentStepRepository.findByStudentId(studentId);
    }

    @Override
    public Optional<StudentStep> getStudentStep(Long studentId, Long stepId) {
        logger.info("Retrieving step {} for student ID: {}", stepId, studentId);
        return studentStepRepository.findByStudentIdAndStepId(studentId, stepId)
                .stream()
                .sorted((a, b) -> b.getStartTime().compareTo(a.getStartTime()))
                .findFirst();
    }

    @Override
    @Transactional
    public StudentStep startStep(Long studentId, Long stepId) {
        logger.info("Starting step {} for student ID: {}", stepId, studentId);

        try {
            Optional<Student> studentOpt = studentRepository.findById(studentId);
            if (!studentOpt.isPresent()) {
                logger.error("Student ID: {} not found", studentId);
                return null;
            }

            Optional<Steps> stepOpt = stepsRepository.findById(stepId);
            if (!stepOpt.isPresent()) {
                logger.error("Step ID: {} not found", stepId);
                return null;
            }

            Optional<StudentStep> existingStudentStepOpt = getStudentStep(studentId, stepId);

            StudentStep studentStep;
            if (existingStudentStepOpt.isPresent()) {
                studentStep = existingStudentStepOpt.get();
                if (studentStep.getStartTime() == null) {
                    logger.info("Updating existing step for student ID: {}", studentId);
                    studentStep.setStartTime(LocalDateTime.now());
                    studentStep.setEndTime(LocalDateTime.now().plusMinutes(studentStep.getStep().getDurationInMinutes()));
                }
            } else {
                logger.info("Creating new step for student ID: {}", studentId);
                studentStep = new StudentStep();
                studentStep.setStudent(studentOpt.get());
                studentStep.setStep(stepOpt.get());
                studentStep.setStartTime(LocalDateTime.now());
                studentStep.setEndTime(LocalDateTime.now().plusMinutes(studentStep.getStep().getDurationInMinutes()));
                studentStep.setCompleted(false);
            }

            StudentStep savedStep = studentStepRepository.save(studentStep);
            logger.info("Successfully started step {} for student ID: {}", stepId, studentId);
            return savedStep;

        } catch (Exception e) {
            logger.error("An error occurred while starting step {} for student ID: {}", stepId, studentId, e);
            return null;
        }
    }

    @Override
    @Transactional
    public StudentStep completeStep(Long studentId, Long stepId, String taskUrl) {
        logger.info("Completing step {} for student ID: {}", stepId, studentId);
        Optional<StudentStep> optionalStudentStep = getStudentStep(studentId, stepId);
        if (optionalStudentStep.isPresent()) {
            StudentStep studentStep = optionalStudentStep.get();
            studentStep.setEndTime(LocalDateTime.now());
            studentStep.setCompleted(true);
            studentStep.setTaskUrl(taskUrl);

            StudentStep savedStep = studentStepRepository.save(studentStep);
            logger.info("Completed step {} for student ID: {}", stepId, studentId);
            return savedStep;
        }
        logger.error("Failed to complete step {} for student ID: {}", stepId, studentId);
        return null;
    }

    @Override
    public List<StepWithStatusDTO> getStepsWithCompletionStatus(Long studentId, Long parcoursId) {
        logger.info("Retrieving steps with completion status for student ID: {} and parcours ID: {}", studentId, parcoursId);
        List<Steps> steps = stepsRepository.findByParcoursIdOrderById(parcoursId);
        return steps.stream().map(step -> {
            StepWithStatusDTO dto = new StepWithStatusDTO();
            dto.setId(step.getId());
            dto.setTitle(step.getTitle());
            dto.setDurationInMinutes(step.getDurationInMinutes());
            Optional<StudentStep> studentStepOpt = getStudentStep(studentId, step.getId());
            if (studentStepOpt.isPresent()) {
                StudentStep studentStep = studentStepOpt.get();
                dto.setCompleted(studentStep.isCompleted());
                dto.setStartTime(studentStep.getStartTime());
                dto.setEndTime(studentStep.getEndTime());
                dto.setStatus(studentStep.isCompleted());
            } else {
                dto.setCompleted(false);
                dto.setStartTime(null);
                dto.setEndTime(null);
                dto.setStatus(false);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void checkAndCompleteSteps() {
        LocalDateTime now = LocalDateTime.now();
        List<StudentStep> stepsToComplete = studentStepRepository.findByCompletedFalseAndEndTimeBefore(now);
        for (StudentStep step : stepsToComplete) {
            step.setCompleted(true);
            studentStepRepository.save(step);
        }
        logger.info("Scheduled task completed: Steps marked as complete if their end time has passed.");
    }
}
