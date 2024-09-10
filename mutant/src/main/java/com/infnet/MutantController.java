package com.infnet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/mutants")
public record MutantController(MutantService mutantService) {

    @PostMapping
    public void registerMutant(@RequestBody MutantRegistrationRequest mutantRegistrationRequest){
        log.info("Registering mutant: {}", mutantRegistrationRequest);
        mutantService.registerMutant(mutantRegistrationRequest);
    }
}