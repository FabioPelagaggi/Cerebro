package com.infnet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/mutants")
public class MutantController {

    private final MutantService mutantService;

    @Operation(summary = "Register a mutant", description = "Check if is a Mutant and register in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mutant registered successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MutantModel.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request, the subject is not a mutant"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> registerMutant(@RequestBody MutantRegistrationRequest mutantRegistrationRequest) {
        log.info("Registering mutant: {}", mutantRegistrationRequest);
        try {
            mutantService.registerMutant(mutantRegistrationRequest);
            return ResponseEntity.ok("Mutant registered successfully");
        } catch (Exception e) {
            log.error("The Subject is not a Mutant", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Subject is not a Mutant");
        }
    }

    @Operation(summary = "Get a mutant", description = "Get a mutant by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mutant found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MutantModel.class))
            }),
            @ApiResponse(responseCode = "404", description = "Mutant not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("{id}")
    public ResponseEntity<?> getMutant(@PathVariable("id") Long id){
        log.info("Getting mutant with id: {}", id);
    
        try {
            return ResponseEntity.ok(mutantService.getMutant(id));
        } catch (Exception e) {
            log.error("Mutant not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found");
        }
    }

    @Operation(summary = "Delete a mutant", description = "Delete a mutant by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mutant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Mutant not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMutant(@PathVariable("id") Long id){
        log.info("Deleting mutant with id: {}", id);
        try {
            mutantService.deleteMutant(id);
            return ResponseEntity.ok("Mutant deleted successfully");
        } catch (Exception e) {
            log.error("Mutant not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found");
        }
    }
    
    @Operation(summary = "Update a mutant", description = "Update a mutant by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mutant updated successfully"),
            @ApiResponse(responseCode = "404", description = "Mutant not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("{id}")
    public ResponseEntity<?> updateMutant(@PathVariable("id") Long id, @RequestBody MutantRegistrationRequest mutantRegistrationRequest){
        log.info("Updating mutant with id: {}", id);
        try {
            mutantService.updateMutant(id, mutantRegistrationRequest);
            return ResponseEntity.ok("Mutant updated successfully");
        } catch (Exception e) {
            log.error("Mutant not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found");
        }
    }

    @Operation(summary = "Return All mutants registed on Cerebro Database", description = "Return all mutants registed on Cerebro Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All mutants found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MutantModel[].class))
            }),
            @ApiResponse(responseCode = "404", description = "Mutants not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("all")
    public ResponseEntity<?> getAllMutants(){
        log.info("Getting all mutants");
        try {
            return ResponseEntity.ok(mutantService.getAllMutants());
        } catch (Exception e) {
            log.error("Mutants not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutants not found");
        }
    }
}