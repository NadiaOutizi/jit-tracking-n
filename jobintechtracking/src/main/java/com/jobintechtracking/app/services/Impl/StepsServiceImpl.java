package com.jobintechtracking.app.services.Impl;


import com.jobintechtracking.app.entities.Steps;
import com.jobintechtracking.app.repositories.StepRepository;
import com.jobintechtracking.app.services.facade.StepsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StepsServiceImpl implements StepsService {

    private final StepRepository stepRepository;
    public StepsServiceImpl(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    @Transactional
    public Steps saveStep (Steps steps) {
        return stepRepository.save(steps);
    }

    @Override
    public Steps findById(Long id) {
        return  stepRepository.findById(id).orElse(null);
    }

    public List<Steps> findAll(){
        return stepRepository.findAll();
    }

    @Override
    @Transactional
    public Steps UpdateStep(Steps step) {
        return stepRepository.save(step);
    }

    @Override
    @Transactional
    public void deleteStep(Long id) {
        stepRepository.deleteById(id);
    }

    @Override
    public List<Steps> getStepsByParcoursId (Long parcoursId){
        List<Steps> steps = stepRepository.findByParcoursId(parcoursId);
        steps.sort(Comparator.comparing(Steps::getId));
        return steps;
    }
    @Override
    public Optional<Steps> getStepById(Long id) {
        return stepRepository.findById(id);
    }
}
