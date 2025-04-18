package com.example.musculia.serviceimpl;

import com.example.musculia.model.TrainingProgram;
import com.example.musculia.model.UserProfile;
import com.example.musculia.repository.TrainingProgramRepository;
import com.example.musculia.repository.UserProfileRepository;
import com.example.musculia.service.TrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public TrainingProgram createProgram(TrainingProgram program) {
        UserProfile userProfile = userProfileRepository.findById(program.getUserProfile().getId())
                .orElseThrow(() -> new EntityNotFoundException("User profile not found"));
        program.setUserProfile(userProfile);
        return trainingProgramRepository.save(program);
    }

    @Override
    public TrainingProgram getProgramById(Long id) {
        return trainingProgramRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training program not found with id: " + id));
    }

    @Override
    public List<TrainingProgram> getAllPrograms() {
        return trainingProgramRepository.findAll();
    }

    @Override
    public List<TrainingProgram> getProgramsByUserProfileId(Long userProfileId) {
        return trainingProgramRepository.findByUserProfileId(userProfileId);
    }

    @Override
    public TrainingProgram updateProgram(Long id, TrainingProgram programDetails) {
        TrainingProgram program = getProgramById(id);
        
        program.setProgramType(programDetails.getProgramType());
        program.setDuration(programDetails.getDuration());
        program.setDaysPerWeek(programDetails.getDaysPerWeek());
        
        return trainingProgramRepository.save(program);
    }

    @Override
    public void deleteProgram(Long id) {
        if (!trainingProgramRepository.existsById(id)) {
            throw new EntityNotFoundException("Training program not found with id: " + id);
        }
        trainingProgramRepository.deleteById(id);
    }
} 