package com.example.musculia.service;

import com.example.musculia.model.Workout;
import java.util.List;

public interface WorkoutService {
    Workout createWorkout(Workout workout);
    Workout getWorkoutById(Long id);
    List<Workout> getAllWorkouts();
    Workout updateWorkout(Long id, Workout workoutDetails);
    void deleteWorkout(Long id);
} 