package com.example.musculia.service;

import com.example.musculia.model.TrainingProgram;
import java.util.List;

public interface TrainingProgramService {
    TrainingProgram createProgram(TrainingProgram program);
    TrainingProgram getProgramById(Long id);
    List<TrainingProgram> getAllPrograms();
    List<TrainingProgram> getProgramsByUserProfileId(Long userProfileId);
    TrainingProgram updateProgram(Long id, TrainingProgram programDetails);
    void deleteProgram(Long id);
} 