package com.example.musculia.controller;

import com.example.musculia.model.UserProfile;
import com.example.musculia.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfile profile) {
        return ResponseEntity.ok(userProfileService.createProfile(profile));
    }

    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllProfiles() {
        return ResponseEntity.ok(userProfileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getProfileById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfile> getProfileByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userProfileService.getProfileByAuthUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateProfile(@PathVariable Long id, @RequestBody UserProfile profileDetails) {
        return ResponseEntity.ok(userProfileService.updateProfile(id, profileDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        userProfileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }
} 