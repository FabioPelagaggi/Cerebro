package com.infnet.model;

public record MutantRegistrationRequest(
        String name,
        String realName,
        String level,
        String[] mutantPowers,
        String description,
        String image
) {}