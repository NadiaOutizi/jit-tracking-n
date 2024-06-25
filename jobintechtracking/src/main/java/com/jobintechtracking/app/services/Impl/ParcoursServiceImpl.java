package com.jobintechtracking.app.services.Impl;

import com.jobintechtracking.app.entities.Parcours;
import com.jobintechtracking.app.repositories.ParcoursRepository;
import com.jobintechtracking.app.services.facade.ParcoursService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParcoursServiceImpl implements ParcoursService {

    private ParcoursRepository parcoursRepository;
    public ParcoursServiceImpl(ParcoursRepository parcoursRepository) {
        this.parcoursRepository = parcoursRepository;
    }

    @Override
    @Transactional
    public Parcours save(Parcours parcours) {
        return parcoursRepository.save(parcours);
    }

    @Override
    public Parcours findById(Long id) {
        return  parcoursRepository.findById(id).orElse(null);
    }

    @Override
    public List<Parcours> findAll(){
        return parcoursRepository.findAll();
    }

    @Override
    @Transactional
    public Parcours update(Parcours parcours) {
        return parcoursRepository.save(parcours);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        parcoursRepository.deleteById(id);
    }
}
