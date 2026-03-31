package com.Maarten0162.ProgressPicBackend.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to users.uuid
    @Column(name = "useruuid", nullable = false)
    private UUID userUUID;

    @Column(nullable = false)
    private Instant date = Instant.now();

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "front_image_url", columnDefinition = "text")
    private String frontImageUrl;

    @Column(name = "front_image_id", columnDefinition = "text")
    private String frontImageId;

    @Column(name = "side_image_url", columnDefinition = "text")
    private String sideImageUrl;

    @Column(name = "side_image_id", columnDefinition = "text")
    private String sideImageId;

    @Column(name = "back_image_url", columnDefinition = "text")
    private String backImageUrl;

    @Column(name = "back_image_id", columnDefinition = "text")
    private String backImageId;
}
