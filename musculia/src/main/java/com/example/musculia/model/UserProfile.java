package com.example.musculia.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_profile")
@Data
public class UserProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "auth_user_id", nullable = false)
    private AuthUser authUser;

    private String gender;
    private Double weight;
    private Double height;
    private Double bodyFat;
    private Integer experienceYears;
    private Integer trainingFrequency;
    private Integer sessionDuration;
    private String equipmentAvailable;
    private String goal;
    private String trainingPreference;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "userProfile")
    private List<TrainingProgram> trainingPrograms;

    @OneToMany(mappedBy = "userProfile")
    private List<Workout> workouts;

    public enum Gender {
        male, female, other
    }

    public enum Goal {
        muscle_gain, weight_loss, toning
    }

    public enum TrainingPreference {
        strength, hypertrophy, endurance
    }

    // Getters and Setters
    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }
} 