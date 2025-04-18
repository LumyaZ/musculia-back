package com.example.musculia.repository;

import com.example.musculia.model.WorkoutDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutDetailsRepository extends JpaRepository<WorkoutDetails, Long> {
    List<WorkoutDetails> findByWorkoutId(Long workoutId);
    List<WorkoutDetails> findByExerciseId(Long exerciseId);
} 