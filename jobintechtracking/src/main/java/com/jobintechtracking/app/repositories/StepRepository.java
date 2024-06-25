package com.jobintechtracking.app.repositories;

import com.jobintechtracking.app.entities.Steps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Steps, Long> {

    List<Steps> findByParcoursId(Long parcoursId);
    List<Steps> findByParcoursIdOrderById(Long parcoursId);
}
