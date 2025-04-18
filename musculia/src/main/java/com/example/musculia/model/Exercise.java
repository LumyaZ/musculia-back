package com.example.musculia.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exercise")
@Data
public class Exercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<WorkoutDetails> workoutDetails;

    public enum Category {
        CHEST,
        BACK,
        LEGS,
        SHOULDERS,
        ARMS,
        ABS,
        CARDIO
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<WorkoutDetails> getWorkoutDetails() {
        return workoutDetails;
    }

    public void setWorkoutDetails(List<WorkoutDetails> workoutDetails) {
        this.workoutDetails = workoutDetails;
    }
} 