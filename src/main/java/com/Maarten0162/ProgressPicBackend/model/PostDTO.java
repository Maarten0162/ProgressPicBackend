package com.Maarten0162.ProgressPicBackend.model;

import java.time.Instant;
import java.util.UUID;

public record PostDTO(
    UUID postId,
    UUID creatorId,
    String beforeImageUrl,
    String afterImageUrl,
    Instant beforeDate,
    Instant afterDate,
    String caption,
    Instant createdAt
) {}

