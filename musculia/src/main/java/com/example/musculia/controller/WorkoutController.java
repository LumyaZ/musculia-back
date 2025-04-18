package com.example.musculia.controller;

import com.example.musculia.model.Workout;
import com.example.musculia.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        return ResponseEntity.ok(workoutService.createWorkout(workout));
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.getWorkoutById(id));
    }

    @GetMapping("/user/{userProfileId}")
    public ResponseEntity<List<Workout>> getWorkoutsByUserProfileId(@PathVariable Long userProfileId) {
        return ResponseEntity.ok(workoutService.getWorkoutsByUserProfileId(userProfileId));
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<List<Workout>> getWorkoutsByProgramId(@PathVariable Long programId) {
        return ResponseEntity.ok(workoutService.getWorkoutsByProgramId(programId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout workoutDetails) {
        return ResponseEntity.ok(workoutService.updateWorkout(id, workoutDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.ok().build();
    }
} 