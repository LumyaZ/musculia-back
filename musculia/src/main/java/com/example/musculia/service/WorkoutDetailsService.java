package com.example.musculia.service;

import com.example.musculia.model.WorkoutDetails;
import java.util.List;

public interface WorkoutDetailsService {
    WorkoutDetails createWorkoutDetail(WorkoutDetails workoutDetail);
    WorkoutDetails getWorkoutDetailById(Long id);
    List<WorkoutDetails> getAllWorkoutDetails();
    List<WorkoutDetails> getWorkoutDetailsByWorkoutId(Long workoutId);
    List<WorkoutDetails> getWorkoutDetailsByExerciseId(Long exerciseId);
    WorkoutDetails updateWorkoutDetail(Long id, WorkoutDetails workoutDetailDetails);
    void deleteWorkoutDetail(Long id);
} 