package com.example.musculia.repository;

import com.example.musculia.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByCategory(Exercise.Category category);
    List<Exercise> findByNameContainingIgnoreCase(String name);
} 