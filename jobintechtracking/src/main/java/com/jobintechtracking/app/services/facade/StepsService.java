package com.jobintechtracking.app.services.facade;

import com.jobintechtracking.app.entities.Steps;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface StepsService {

    Steps saveStep (Steps steps);
    Steps findById(Long id);
    List<Steps> findAll();
    Steps UpdateStep(Steps step);
    void deleteStep(Long id);
    List<Steps> getStepsByParcoursId (Long parcoursId);
    Optional<Steps> getStepById(Long id);
}
