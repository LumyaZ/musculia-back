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
        if (program.getUserProfile() != null) {
            UserProfile userProfile = userProfileRepository.findById(program.getUserProfile().getId())
                    .orElseThrow(() -> new EntityNotFoundException("User profile not found"));
            program.setUserProfile(userProfile);
        }
        program.setIsDefault(false); // Les programmes créés par les utilisateurs ne sont jamais par défaut
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
    public List<TrainingProgram> getDefaultPrograms() {
        return trainingProgramRepository.findByIsDefaultTrue();
    }

    @Override
    public List<TrainingProgram> getUserPrograms(Long userProfileId) {
        return trainingProgramRepository.findByIsDefaultFalseAndUserProfileId(userProfileId);
    }

    @Override
    public TrainingProgram applyDefaultProgramToUser(Long defaultProgramId, Long userProfileId) {
        // Récupérer le programme par défaut
        TrainingProgram defaultProgram = trainingProgramRepository.findById(defaultProgramId)
                .orElseThrow(() -> new EntityNotFoundException("Default program not found with id: " + defaultProgramId));
        
        if (!defaultProgram.getIsDefault()) {
            throw new IllegalArgumentException("Program with id " + defaultProgramId + " is not a default program");
        }

        // Récupérer le profil utilisateur
        UserProfile userProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new EntityNotFoundException("User profile not found with id: " + userProfileId));

        // Créer une copie du programme par défaut pour l'utilisateur
        TrainingProgram userProgram = new TrainingProgram();
        userProgram.setUserProfile(userProfile);
        userProgram.setIsDefault(false);
        userProgram.setProgramType(defaultProgram.getProgramType());
        userProgram.setDuration(defaultProgram.getDuration());
        userProgram.setDaysPerWeek(defaultProgram.getDaysPerWeek());

        return trainingProgramRepository.save(userProgram);
    }

    @Override
    public TrainingProgram updateProgram(Long id, TrainingProgram programDetails) {
        TrainingProgram program = getProgramById(id);
        
        // Empêcher la modification des programmes par défaut
        if (program.getIsDefault()) {
            throw new IllegalArgumentException("Cannot update default programs");
        }
        
        program.setProgramType(programDetails.getProgramType());
        program.setDuration(programDetails.getDuration());
        program.setDaysPerWeek(programDetails.getDaysPerWeek());
        
        return trainingProgramRepository.save(program);
    }

    @Override
    public void deleteProgram(Long id) {
        TrainingProgram program = getProgramById(id);
        
        // Empêcher la suppression des programmes par défaut
        if (program.getIsDefault()) {
            throw new IllegalArgumentException("Cannot delete default programs");
        }
        
        trainingProgramRepository.deleteById(id);
    }
} 