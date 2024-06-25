package com.jobintechtracking.app.controllers;

import com.jobintechtracking.app.entities.Learning;
import com.jobintechtracking.app.services.Impl.LearningServiceImpl;
import com.jobintechtracking.app.services.facade.LearningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learnings")
public class LearningController {

    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @PostMapping("/save")
    public ResponseEntity<Learning> saveLearning(@RequestBody Learning learning) {
        Learning savedLearning = learningService.save(learning);
        return ResponseEntity.ok(savedLearning);
    }
    @PutMapping("/{id}")
    public Learning updateLearning(@PathVariable Long id, @RequestBody Learning learning) {
        return learningService.Updatelearning(learning);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteLearning(@RequestBody Long id) {
        learningService.deleteById(id);
        return ResponseEntity.ok("Learning deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Learning> getLearningById(@PathVariable Long id) {
        Learning learning = learningService.findById(id);
        if (learning != null) {
            return ResponseEntity.ok(learning);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Learning>> getAllLearnings() {
        List<Learning> learnings = learningService.findAll();
        return ResponseEntity.ok(learnings);
    }

}