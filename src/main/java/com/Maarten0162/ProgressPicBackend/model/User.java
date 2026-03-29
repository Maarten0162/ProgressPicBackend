package com.Maarten0162.ProgressPicBackend.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    private UUID uuid;
    
    private String username;

    private String email;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();;
    
}
