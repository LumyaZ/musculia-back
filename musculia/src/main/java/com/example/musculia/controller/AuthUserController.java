package com.example.musculia.controller;

import com.example.musculia.model.AuthUser;
import com.example.musculia.security.JwtTokenProvider;
import com.example.musculia.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthUser loginRequest) {
        System.out.println("Tentative de connexion pour l'email: " + loginRequest.getEmail());
        System.out.println("Mot de passe reçu (en clair): " + loginRequest.getPassword());
        
        Optional<AuthUser> user = authUserService.getUserByEmail(loginRequest.getEmail());
        
        if (user.isPresent()) {
            System.out.println("Utilisateur trouvé avec l'email: " + loginRequest.getEmail());
            System.out.println("Mot de passe attendu (hashé): " + user.get().getPassword());
            System.out.println("Hash du mot de passe reçu: " + passwordEncoder.encode(loginRequest.getPassword()));
            
            if (passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                System.out.println("Connexion réussie pour l'utilisateur: " + loginRequest.getEmail());
                
                String token = jwtTokenProvider.generateToken(user.get().getEmail());
                
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("user", user.get());
                
                return ResponseEntity.ok(response);
            } else {
                System.out.println("Mot de passe incorrect pour l'utilisateur: " + loginRequest.getEmail());
            }
        } else {
            System.out.println("Aucun utilisateur trouvé avec l'email: " + loginRequest.getEmail());
        }
        
        return ResponseEntity.badRequest().body("Email ou mot de passe incorrect");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthUser user) {
        System.out.println("Inscription d'un nouvel utilisateur: " + user.getEmail());
        System.out.println("Mot de passe avant hash: " + user.getPassword());
        
        // Hash du mot de passe avec BCrypt
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println("Mot de passe après hash: " + hashedPassword);
        user.setPassword(hashedPassword);
        
        AuthUser savedUser = authUserService.createUser(user);
        
        String token = jwtTokenProvider.generateToken(savedUser.getEmail());
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", savedUser);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AuthUser>> getAllUsers() {
        return ResponseEntity.ok(authUserService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AuthUser> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(authUserService.getUserById(id));
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<Optional<AuthUser>> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(authUserService.getUserByEmail(email));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AuthUser> updateUser(@PathVariable Long id, @RequestBody AuthUser userDetails) {
        return ResponseEntity.ok(authUserService.updateUser(id, userDetails));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        authUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
} 