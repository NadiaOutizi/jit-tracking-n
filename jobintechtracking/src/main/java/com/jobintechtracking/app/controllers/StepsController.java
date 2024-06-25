package com.jobintechtracking.app.controllers;

import com.jobintechtracking.app.entities.Steps;
import com.jobintechtracking.app.services.Impl.StepsServiceImpl;
import com.jobintechtracking.app.services.facade.StepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/steps")
public class StepsController {

    private final StepsService stepsService;

    public StepsController(StepsService stepsService) {
        this.stepsService= stepsService;
    }

    @GetMapping
    public List<Steps> getAllSteps() {
        List<Steps> steps = stepsService.findAll();
        steps.sort(Comparator.comparing(Steps::getId));
        return steps;
    }

    @GetMapping("/parcours/{parcoursId}")
    public List<Steps> getStepsByParcours(@PathVariable Long parcoursId) {
        return stepsService.getStepsByParcoursId(parcoursId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Steps> getStepById(@PathVariable Long id) {
        Optional<Steps> step = stepsService.getStepById(id);
        return step.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Steps createStep(@RequestBody Steps step) {
        return stepsService.saveStep(step);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStep(@PathVariable Long id) {
        stepsService.deleteStep(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public Steps updateSteps(@PathVariable Long id, @RequestBody Steps step) {
        return stepsService.UpdateStep(step);
    }
}