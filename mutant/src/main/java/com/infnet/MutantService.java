package com.infnet;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MutantService {
    private final MutantRepository mutantRepository;

    public void registerMutant(MutantRegistrationRequest request) {
        MutantModel mutant = MutantModel.builder()
                .name(request.name())
                .realName(request.realName())
                .level(request.level())
                .mutantPowers(request.mutantPowers())
                .description(request.description())
                .image(request.image())
                .build();

        mutantRepository.save(mutant);
    }
}