package com.jobintechtracking.app.controllers;

import com.jobintechtracking.app.entities.Parcours;
import com.jobintechtracking.app.services.Impl.ParcoursServiceImpl;
import com.jobintechtracking.app.services.facade.ParcoursService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcours")
public class ParcoursController {
    private final ParcoursService parcoursService;
    public ParcoursController(ParcoursService parcoursService) {
        this.parcoursService = parcoursService;
    }

    @PostMapping
    public ResponseEntity<Parcours> saveParcours(@RequestBody Parcours parcours) {
        Parcours savedParcours = parcoursService.save(parcours);
        return ResponseEntity.ok(savedParcours);
    }

    @GetMapping
    public ResponseEntity<List<Parcours>> getAllParcours() {
        List<Parcours> parcours = parcoursService.findAll();
        return ResponseEntity.ok(parcours);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcours> updateParcours(@PathVariable Long id ,@RequestBody Parcours parcours) {
        Parcours updatedParcours = parcoursService.update(parcours);
        return ResponseEntity.ok(updatedParcours);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteParcours(@RequestBody Long id) {
        parcoursService.deleteById(id);
        return ResponseEntity.ok("Parcour deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcours> getParcoursById(@PathVariable Long id) {
        Parcours parcours = parcoursService.findById(id);
        if (parcours != null) {
            return ResponseEntity.ok(parcours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
