package com.infnet;

public record HistoryRegistrationRequest(
        Long mutantId,
        String name,
        String registerType) {}