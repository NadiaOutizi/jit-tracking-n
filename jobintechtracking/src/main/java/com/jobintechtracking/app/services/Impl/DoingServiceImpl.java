package com.jobintechtracking.app.services.Impl;

import com.jobintechtracking.app.entities.Doing;

import com.jobintechtracking.app.repositories.DoingRepository;
import com.jobintechtracking.app.services.facade.DoingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoingServiceImpl implements DoingService {

    private final DoingRepository doingRepository;

    public DoingServiceImpl(DoingRepository doingRepository) {
        this.doingRepository = doingRepository;
    }

    @Override
    @Transactional
    public Doing save(Doing doing) {
        return doingRepository.save(doing);
    }

    @Override
    public Doing findById(Long id) {
        return doingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doing> findAll() {
        return doingRepository.findAll();
    }

    @Override
    @Transactional
    public Doing updateDoing(Doing doing) {
        return doingRepository.save(doing);
    }

    @Override
    @Transactional
    public void deleteDoing(Long id) {
        doingRepository.deleteById(id);
    }

    @Override
//  @Transactional(readOnly = true)
    @Transactional
    public List<Doing> getDoingByStepId(Long stepsId) {
        return doingRepository.findByStepsId(stepsId);
    }
}
