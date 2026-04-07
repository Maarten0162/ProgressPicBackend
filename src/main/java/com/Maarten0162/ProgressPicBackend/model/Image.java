package com.Maarten0162.ProgressPicBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "record_id")
    private Record record;

    @Enumerated(EnumType.STRING)
    private ImageType type;

     @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;

    @Column(name = "image_id", columnDefinition = "text")
    private String imageId;
}

