package com.example.musculia.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workout")
@Data
public class Workout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sessionDate;
    private Integer duration; // en minutes
    private String notes;
    private String categorie;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<WorkoutDetails> workoutDetails;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<WorkoutDetails> getWorkoutDetails() {
        return workoutDetails;
    }

    public void setWorkoutDetails(List<WorkoutDetails> workoutDetails) {
        this.workoutDetails = workoutDetails;
    }
} 