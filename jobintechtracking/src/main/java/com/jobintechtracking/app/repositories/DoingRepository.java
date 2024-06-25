package com.jobintechtracking.app.repositories;

import com.jobintechtracking.app.entities.Doing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoingRepository extends JpaRepository<Doing, Long> {
    List<Doing> findByStepsId(long stepsId);
}
