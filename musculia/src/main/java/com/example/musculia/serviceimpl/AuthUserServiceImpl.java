package com.example.musculia.serviceimpl;

import com.example.musculia.model.AuthUser;
import com.example.musculia.repository.AuthUserRepository;
import com.example.musculia.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthUser createUser(AuthUser user) {
        if (existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return authUserRepository.save(user);
    }

    @Override
    public AuthUser getUserById(Long id) {
        return authUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public Optional<AuthUser> getUserByEmail(String email) {
        return authUserRepository.findByEmail(email);
    }

    @Override
    public List<AuthUser> getAllUsers() {
        return authUserRepository.findAll();
    }

    @Override
    public AuthUser updateUser(Long id, AuthUser userDetails) {
        AuthUser user = getUserById(id);
        
        if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail())) {
            if (existsByEmail(userDetails.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            user.setEmail(userDetails.getEmail());
        }
        
        if (userDetails.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        
        user.setRole(userDetails.getRole());
        
        return authUserRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!authUserRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        authUserRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return authUserRepository.existsByEmail(email);
    }
} 