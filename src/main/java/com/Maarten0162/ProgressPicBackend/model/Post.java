package com.Maarten0162.ProgressPicBackend.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = false)
    private String beforeImageUrl;

    @Column(nullable = false)
    private String afterImageUrl;

    @Column(nullable = false)
    private Instant beforeDate;

    @Column(nullable = false)
    private Instant afterDate;

    @Column(nullable = false)
    private String caption;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
