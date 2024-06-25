package com.jobintechtracking.app.repositories;

import com.jobintechtracking.app.entities.StudentStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentStepRepository  extends JpaRepository<StudentStep , Long> {

    List<StudentStep> findByStudentId(Long studentId);
    List<StudentStep> findByStudentIdAndStepId(Long studentId, Long stepId);
    List<StudentStep> findByCompletedFalseAndEndTimeBefore(LocalDateTime now);
}
