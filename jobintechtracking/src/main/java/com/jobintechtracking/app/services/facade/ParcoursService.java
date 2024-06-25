package com.jobintechtracking.app.services.facade;

import com.jobintechtracking.app.entities.Parcours;
import com.jobintechtracking.app.repositories.ParcoursRepository;

import java.util.List;

public interface ParcoursService {
    Parcours save(Parcours parcours);

    Parcours findById(Long id);

    List<Parcours> findAll();

    Parcours update(Parcours parcours);

    void deleteById(Long id);
}
