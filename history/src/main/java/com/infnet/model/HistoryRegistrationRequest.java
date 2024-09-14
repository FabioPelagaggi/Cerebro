package com.infnet.model;

public record HistoryRegistrationRequest(
        Long mutantId,
        String name,
        String registerType) {}