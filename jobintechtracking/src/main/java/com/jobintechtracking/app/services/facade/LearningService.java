package com.jobintechtracking.app.services.facade;

import com.jobintechtracking.app.entities.Learning;
import com.jobintechtracking.app.repositories.LearningRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LearningService {

    Learning save(Learning learning);
    void deleteById(Long id);
    Learning findById(Long id);
    List<Learning> findAll();
    List<Learning> getLearnDoByStepId(Long stepsId);
    Learning Updatelearning(Learning learning);
}
