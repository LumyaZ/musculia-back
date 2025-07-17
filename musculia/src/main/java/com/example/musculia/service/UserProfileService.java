package com.example.musculia.service;

import com.example.musculia.model.UserProfile;
import java.util.List;

public interface UserProfileService {
    UserProfile createProfile(UserProfile profile);
    UserProfile getProfileById(Long id);
    UserProfile getProfileByAuthUserId(Long authUserId);
    List<UserProfile> getAllProfiles();
    UserProfile updateProfile(Long id, UserProfile profileDetails);
    void deleteProfile(Long id);
    UserProfile assignWorkoutToUser(Long profileId, Long workoutId);
} 