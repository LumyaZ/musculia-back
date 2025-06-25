package com.example.musculia.service;

import com.example.musculia.model.TrainingProgram;
import java.util.List;

public interface TrainingProgramService {
    TrainingProgram createProgram(TrainingProgram program);
    TrainingProgram getProgramById(Long id);
    List<TrainingProgram> getAllPrograms();
    List<TrainingProgram> getProgramsByUserProfileId(Long userProfileId);
    List<TrainingProgram> getDefaultPrograms();
    List<TrainingProgram> getUserPrograms(Long userProfileId);
    TrainingProgram applyDefaultProgramToUser(Long defaultProgramId, Long userProfileId);
    TrainingProgram updateProgram(Long id, TrainingProgram programDetails);
    void deleteProgram(Long id);
} 