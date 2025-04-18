package com.example.musculia.service;

import com.example.musculia.model.Exercise;
import java.util.List;

public interface ExerciseService {
    Exercise createExercise(Exercise exercise);
    Exercise getExerciseById(Long id);
    List<Exercise> getAllExercises();
    List<Exercise> getExercisesByCategory(Exercise.Category category);
    List<Exercise> searchExercisesByName(String name);
    Exercise updateExercise(Long id, Exercise exerciseDetails);
    void deleteExercise(Long id);
} 