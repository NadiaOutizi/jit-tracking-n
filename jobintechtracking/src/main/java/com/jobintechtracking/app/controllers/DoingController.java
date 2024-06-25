package com.jobintechtracking.app.controllers;

import com.jobintechtracking.app.entities.Doing;
import com.jobintechtracking.app.services.facade.DoingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doings")
public class DoingController {
    private final DoingService doingService;

    public DoingController(DoingService doingService) {
        this.doingService = doingService;
    }

    @PostMapping("/save")
    public ResponseEntity<Doing> saveDoing(@RequestBody Doing doing) {
        Doing savedDoing = doingService.save(doing);
        return ResponseEntity.ok(savedDoing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doing> getDoingById(@PathVariable Long id) {
        Doing doing = doingService.findById(id);
        if (doing != null) {
            return ResponseEntity.ok(doing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Doing>> getAllDoings() {
        List<Doing> doings = doingService.findAll();
        return ResponseEntity.ok(doings);
    }

    @PutMapping("/{id}")
    public Doing updateDoing(@PathVariable Long id, @RequestBody Doing doing) {
        return doingService.updateDoing(doing);
    }

    @DeleteMapping("/{id}")
    public void deleteDoing(@PathVariable Long id) {
        doingService.deleteDoing(id);
    }
}