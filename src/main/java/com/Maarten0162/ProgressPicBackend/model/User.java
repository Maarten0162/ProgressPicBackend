package com.Maarten0162.ProgressPicBackend.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    
    public User(UUID uuid, String usn, String email, Instant createdAt) {
        this.uuid = uuid;
        this.username = usn;
        this.email = email;
        this.createdAt = createdAt;
    }

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
