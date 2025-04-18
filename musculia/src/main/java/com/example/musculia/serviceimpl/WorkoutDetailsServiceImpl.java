package com.example.musculia.serviceimpl;

import com.example.musculia.model.WorkoutDetails;
import com.example.musculia.repository.WorkoutDetailsRepository;
import com.example.musculia.service.WorkoutDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class WorkoutDetailsServiceImpl implements WorkoutDetailsService {

    @Autowired
    private WorkoutDetailsRepository workoutDetailsRepository;

    @Override
    public WorkoutDetails createWorkoutDetail(WorkoutDetails workoutDetail) {
        return workoutDetailsRepository.save(workoutDetail);
    }

    @Override
    public WorkoutDetails getWorkoutDetailById(Long id) {
        return workoutDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutDetail not found with id: " + id));
    }

    @Override
    public List<WorkoutDetails> getAllWorkoutDetails() {
        return workoutDetailsRepository.findAll();
    }

    @Override
    public List<WorkoutDetails> getWorkoutDetailsByWorkoutId(Long workoutId) {
        return workoutDetailsRepository.findByWorkoutId(workoutId);
    }

    @Override
    public List<WorkoutDetails> getWorkoutDetailsByExerciseId(Long exerciseId) {
        return workoutDetailsRepository.findByExerciseId(exerciseId);
    }

    @Override
    public WorkoutDetails updateWorkoutDetail(Long id, WorkoutDetails workoutDetailDetails) {
        WorkoutDetails workoutDetail = getWorkoutDetailById(id);
        
        workoutDetail.setWorkout(workoutDetailDetails.getWorkout());
        workoutDetail.setExercise(workoutDetailDetails.getExercise());
        workoutDetail.setRepetitions(workoutDetailDetails.getRepetitions());
        workoutDetail.setSets(workoutDetailDetails.getSets());
        workoutDetail.setWeight(workoutDetailDetails.getWeight());
        workoutDetail.setDifficulty(workoutDetailDetails.getDifficulty());

        return workoutDetailsRepository.save(workoutDetail);
    }

    @Override
    public void deleteWorkoutDetail(Long id) {
        if (!workoutDetailsRepository.existsById(id)) {
            throw new EntityNotFoundException("WorkoutDetail not found with id: " + id);
        }
        workoutDetailsRepository.deleteById(id);
    }
} 