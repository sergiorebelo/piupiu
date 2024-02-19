package org.sergiorebelo.piupiu.usermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;


    // Empty constructor
    public UserProfile(User user) {
        // Initialize any fields if needed
        this.user = user;
    }

    public UserProfile() {
        // Initialize any fields if needed
    }

    // Additional profile fields such as bio, location, etc.



    // Getters and setters


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}