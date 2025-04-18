package com.example.musculia.serviceimpl;

import com.example.musculia.model.AuthUser;
import com.example.musculia.model.UserProfile;
import com.example.musculia.repository.AuthUserRepository;
import com.example.musculia.repository.UserProfileRepository;
import com.example.musculia.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public UserProfile createProfile(UserProfile profile) {
        AuthUser authUser = authUserRepository.findById(profile.getAuthUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Auth user not found"));
                
        if (userProfileRepository.findByAuthUserId(authUser.getId()).isPresent()) {
            throw new IllegalArgumentException("A profile already exists for this user");
        }
        
        profile.setAuthUser(authUser);
        return userProfileRepository.save(profile);
    }

    @Override
    public UserProfile getProfileById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User profile not found with id: " + id));
    }

    @Override
    public UserProfile getProfileByAuthUserId(Long authUserId) {
        return userProfileRepository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new EntityNotFoundException("User profile not found for auth user id: " + authUserId));
    }

    @Override
    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile updateProfile(Long id, UserProfile profileDetails) {
        UserProfile profile = getProfileById(id);
        
        profile.setGender(profileDetails.getGender());
        profile.setWeight(profileDetails.getWeight());
        profile.setHeight(profileDetails.getHeight());
        profile.setBodyFat(profileDetails.getBodyFat());
        profile.setExperienceYears(profileDetails.getExperienceYears());
        profile.setTrainingFrequency(profileDetails.getTrainingFrequency());
        profile.setSessionDuration(profileDetails.getSessionDuration());
        profile.setEquipmentAvailable(profileDetails.getEquipmentAvailable());
        profile.setGoal(profileDetails.getGoal());
        profile.setTrainingPreference(profileDetails.getTrainingPreference());
        
        return userProfileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new EntityNotFoundException("User profile not found with id: " + id);
        }
        userProfileRepository.deleteById(id);
    }
} 