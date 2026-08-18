package com.Maarten0162.ProgressPicBackend.model.websocket;

import java.time.Instant;
import java.util.UUID;

public record PostCreatedEvent(
        UUID postId,
        UUID creatorId,
        String beforeImageUrl,
        String afterImageUrl,
        Instant beforeDate,
        Instant afterDate,
        String caption,
        Instant createdAt
) {
}
