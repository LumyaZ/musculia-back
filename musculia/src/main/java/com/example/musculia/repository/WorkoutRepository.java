package com.example.musculia.repository;

import com.example.musculia.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserProfileId(Long userProfileId);
    List<Workout> findByProgramId(Long programId);
} 