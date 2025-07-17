package com.example.musculia.serviceimpl;

import com.example.musculia.model.Workout;
import com.example.musculia.repository.WorkoutRepository;
import com.example.musculia.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workout not found with id: " + id));
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    @Override
    public Workout updateWorkout(Long id, Workout workoutDetails) {
        Workout workout = getWorkoutById(id);
        
        workout.setSessionDate(workoutDetails.getSessionDate());
        workout.setDuration(workoutDetails.getDuration());
        workout.setNotes(workoutDetails.getNotes());
        workout.setWorkoutDetails(workoutDetails.getWorkoutDetails());

        return workoutRepository.save(workout);
    }

    @Override
    public void deleteWorkout(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new EntityNotFoundException("Workout not found with id: " + id);
        }
        workoutRepository.deleteById(id);
    }
} 