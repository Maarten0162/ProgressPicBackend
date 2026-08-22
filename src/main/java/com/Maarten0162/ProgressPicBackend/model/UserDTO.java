package com.Maarten0162.ProgressPicBackend.model;

import java.time.Instant;
import java.util.UUID;

public record UserDTO(
    String email,
    String password,
    String username,
    UUID userid,
    Instant createdAt
) {}
