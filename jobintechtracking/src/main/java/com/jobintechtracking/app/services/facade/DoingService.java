package com.jobintechtracking.app.services.facade;

import com.jobintechtracking.app.entities.Doing;
import com.jobintechtracking.app.repositories.DoingRepository;

import java.util.List;

public interface DoingService {

    Doing save(Doing doing);
    Doing findById(Long id);
    List<Doing> findAll();
    Doing updateDoing(Doing doing);
    void deleteDoing(Long id);
    List<Doing> getDoingByStepId(Long stepsId);

}
