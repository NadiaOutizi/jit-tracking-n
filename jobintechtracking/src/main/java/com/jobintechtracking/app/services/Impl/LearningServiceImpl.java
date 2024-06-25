package com.jobintechtracking.app.services.Impl;

import com.jobintechtracking.app.entities.Learning;
import com.jobintechtracking.app.repositories.LearningRepository;
import com.jobintechtracking.app.services.facade.LearningService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningServiceImpl implements LearningService {

    private final LearningRepository learningRepository;
    public LearningServiceImpl(LearningRepository learningRepository) {
        this.learningRepository = learningRepository;
    }

    @Override
    @Transactional
    public Learning save(Learning learning) {
        return learningRepository.save(learning);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        learningRepository.deleteById(id);
    }
    @Override
    public Learning findById(Long id) {
        return  learningRepository.findById(id).orElse(null);
    }

    @Override
    public List<Learning> findAll(){
        return learningRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Learning> getLearnDoByStepId(Long stepsId) {
        return learningRepository.findBystepsId(stepsId);
    }

    @Override
    @Transactional
    public Learning Updatelearning(Learning learning) {
        return learningRepository.save(learning);
    }
}
