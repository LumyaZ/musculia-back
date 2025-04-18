package com.example.musculia.controller;

import com.example.musculia.model.WorkoutDetails;
import com.example.musculia.service.WorkoutDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-details")
public class WorkoutDetailsController {

    @Autowired
    private WorkoutDetailsService workoutDetailsService;

    @PostMapping
    public ResponseEntity<WorkoutDetails> createWorkoutDetail(@RequestBody WorkoutDetails workoutDetail) {
        return ResponseEntity.ok(workoutDetailsService.createWorkoutDetail(workoutDetail));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDetails>> getAllWorkoutDetails() {
        return ResponseEntity.ok(workoutDetailsService.getAllWorkoutDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDetails> getWorkoutDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutDetailsService.getWorkoutDetailById(id));
    }

    @GetMapping("/workout/{workoutId}")
    public ResponseEntity<List<WorkoutDetails>> getWorkoutDetailsByWorkoutId(@PathVariable Long workoutId) {
        return ResponseEntity.ok(workoutDetailsService.getWorkoutDetailsByWorkoutId(workoutId));
    }

    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<List<WorkoutDetails>> getWorkoutDetailsByExerciseId(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(workoutDetailsService.getWorkoutDetailsByExerciseId(exerciseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDetails> updateWorkoutDetail(@PathVariable Long id, @RequestBody WorkoutDetails workoutDetailDetails) {
        return ResponseEntity.ok(workoutDetailsService.updateWorkoutDetail(id, workoutDetailDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutDetail(@PathVariable Long id) {
        workoutDetailsService.deleteWorkoutDetail(id);
        return ResponseEntity.ok().build();
    }
} 