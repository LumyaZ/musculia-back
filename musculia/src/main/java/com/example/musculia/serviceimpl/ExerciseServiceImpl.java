package com.example.musculia.serviceimpl;

import com.example.musculia.model.Exercise;
import com.example.musculia.repository.ExerciseRepository;
import com.example.musculia.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found with id: " + id));
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public List<Exercise> getExercisesByCategory(Exercise.Category category) {
        return exerciseRepository.findByCategory(category);
    }

    @Override
    public List<Exercise> searchExercisesByName(String name) {
        return exerciseRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Exercise updateExercise(Long id, Exercise exerciseDetails) {
        Exercise exercise = getExerciseById(id);
        
        exercise.setName(exerciseDetails.getName());
        exercise.setDescription(exerciseDetails.getDescription());
        exercise.setCategory(exerciseDetails.getCategory());
        exercise.setVideoUrl(exerciseDetails.getVideoUrl());

        return exerciseRepository.save(exercise);
    }

    @Override
    public void deleteExercise(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new EntityNotFoundException("Exercise not found with id: " + id);
        }
        exerciseRepository.deleteById(id);
    }
} 