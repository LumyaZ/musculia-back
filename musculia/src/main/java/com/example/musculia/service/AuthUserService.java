package com.example.musculia.service;

import com.example.musculia.model.AuthUser;
import java.util.List;
import java.util.Optional;

public interface AuthUserService {
    AuthUser createUser(AuthUser user);
    AuthUser getUserById(Long id);
    Optional<AuthUser> getUserByEmail(String email);
    List<AuthUser> getAllUsers();
    AuthUser updateUser(Long id, AuthUser userDetails);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
} 