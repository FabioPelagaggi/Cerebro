package com.infnet;

import java.time.LocalDateTime;

public record HistoryRegistrationRequest(
        Long mutantId,
        String name,
        String registerType,
        LocalDateTime createdAt
) {}