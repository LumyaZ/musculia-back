package com.example.musculia.controller;

import com.example.musculia.model.AuthUser;
import com.example.musculia.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity<AuthUser> registerUser(@RequestBody AuthUser user) {
        System.out.println(user);
        return ResponseEntity.ok(authUserService.createUser(user));
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