package com.infnet;

public record MutantRegistrationRequest(
        String name,
        String realName,
        String level,
        String[] mutantPowers,
        String description,
        String image
) {
}