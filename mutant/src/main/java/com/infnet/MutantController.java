package com.infnet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/mutants")
public class MutantController {

    private final MutantService mutantService;

    @PostMapping
    public void registerMutant(@RequestBody MutantRegistrationRequest mutantRegistrationRequest){
        log.info("Registering mutant: {}", mutantRegistrationRequest);
        mutantService.registerMutant(mutantRegistrationRequest);
    }
}