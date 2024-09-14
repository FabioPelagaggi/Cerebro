package com.infnet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.model.MutantModel;
import com.infnet.model.MutantRegistrationRequest;
import com.infnet.service.MutantService;

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

    @GetMapping("{id}")
    public MutantModel getMutant(@PathVariable("id") Long id){
        log.info("Getting mutant with id: {}", id);
        return mutantService.getMutant(id);
    }

    @DeleteMapping("{id}")
    public void deleteMutant(@PathVariable("id") Long id){
        log.info("Deleting mutant with id: {}", id);
        mutantService.deleteMutant(id);
    }
    
    @PutMapping("{id}")
    public void updateMutant(@PathVariable("id") Long id, @RequestBody MutantRegistrationRequest mutantRegistrationRequest){
        log.info("Updating mutant with id: {}", id);
        mutantService.updateMutant(id, mutantRegistrationRequest);
    }

    @GetMapping("all")
    public List<MutantModel> getAllMutants(){
        log.info("Getting all mutants");
        return mutantService.getAllMutants();
    }
}