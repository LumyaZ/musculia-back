package com.example.musculia.controller;

import com.example.musculia.model.TrainingProgram;
import com.example.musculia.service.TrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class TrainingProgramController {

    @Autowired
    private TrainingProgramService trainingProgramService;

    @PostMapping
    public ResponseEntity<TrainingProgram> createProgram(@RequestBody TrainingProgram program) {
        return ResponseEntity.ok(trainingProgramService.createProgram(program));
    }

    @GetMapping
    public ResponseEntity<List<TrainingProgram>> getAllPrograms() {
        return ResponseEntity.ok(trainingProgramService.getAllPrograms());
    }

    @GetMapping("/default")
    public ResponseEntity<List<TrainingProgram>> getDefaultPrograms() {
        return ResponseEntity.ok(trainingProgramService.getDefaultPrograms());
    }

    @GetMapping("/user/{userProfileId}")
    public ResponseEntity<List<TrainingProgram>> getProgramsByUserProfileId(@PathVariable Long userProfileId) {
        return ResponseEntity.ok(trainingProgramService.getProgramsByUserProfileId(userProfileId));
    }

    @GetMapping("/user/{userProfileId}/custom")
    public ResponseEntity<List<TrainingProgram>> getUserPrograms(@PathVariable Long userProfileId) {
        return ResponseEntity.ok(trainingProgramService.getUserPrograms(userProfileId));
    }

    @PostMapping("/default/{defaultProgramId}/apply/{userProfileId}")
    public ResponseEntity<TrainingProgram> applyDefaultProgramToUser(
            @PathVariable Long defaultProgramId, 
            @PathVariable Long userProfileId) {
        return ResponseEntity.ok(trainingProgramService.applyDefaultProgramToUser(defaultProgramId, userProfileId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingProgram> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingProgramService.getProgramById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingProgram> updateProgram(@PathVariable Long id, @RequestBody TrainingProgram programDetails) {
        return ResponseEntity.ok(trainingProgramService.updateProgram(id, programDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        trainingProgramService.deleteProgram(id);
        return ResponseEntity.ok().build();
    }
} 