package com.infnet.model;

import lombok.Builder;

@Builder
public record HistoryRegistrationRequest(
        Long mutantId,
        String name,
        String registerType
) {}