package com.example.musculia.controller;

import com.example.musculia.model.AuthUser;
import com.example.musculia.model.UserProfile;
import com.example.musculia.security.JwtTokenProvider;
import com.example.musculia.service.AuthUserService;
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

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfile profile, @RequestHeader("Authorization") String token) {
        System.out.println("Création d'un profil avec le token: " + token);
        
        // Extraire le token du header Authorization
        String jwt = token.substring(7); // Enlever "Bearer "
        System.out.println("JWT extrait: " + jwt);
        
        // Récupérer l'email de l'utilisateur depuis le token
        String email = jwtTokenProvider.getEmailFromToken(jwt);
        System.out.println("Email extrait du token: " + email);
        
        // Récupérer l'utilisateur
        AuthUser user = authUserService.getUserByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        System.out.println("Utilisateur trouvé: " + user.getEmail());
        
        // Lier l'utilisateur au profil
        profile.setAuthUser(user);
        System.out.println("Profil lié à l'utilisateur: " + user.getId());
        
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