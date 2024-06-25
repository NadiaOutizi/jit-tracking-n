package com.jobintechtracking.app.repositories;

import com.jobintechtracking.app.entities.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcoursRepository extends JpaRepository<Parcours , Long> {
}
