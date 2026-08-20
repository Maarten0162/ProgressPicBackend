package com.Maarten0162.ProgressPicBackend.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "useruuid", nullable = false)
    private UUID userUUID;

    @Column(nullable = false)
    private Instant date = Instant.now();

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    
    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        images.add(image);
        image.setRecord(this);
    }
    
}
