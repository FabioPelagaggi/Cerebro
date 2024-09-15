package com.infnet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.model.XgeneCheckHistory;
import com.infnet.model.XgeneCheckResponse;
import com.infnet.service.XgeneService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/xgene")
public class XgeneController {

    private final XgeneService xgeneService;
    private static final Logger log = LoggerFactory.getLogger(XgeneController.class); // Initialize the logger object

    @Operation(summary = "Check if a subject is a mutant", description = "Check if a subject is a mutant by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject checked successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = XgeneCheckResponse.class)) }),

            @ApiResponse(responseCode = "404", description = "Mutant not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("{mutantId}")
    public ResponseEntity<XgeneCheckResponse> isMutant(@PathVariable("mutantId") Long mutantId) {
        boolean isMutant = xgeneService.check(mutantId);

        log.info("Subject with ID {} is {}", mutantId, isMutant ? "mutant" : "not mutant");
        try {
            return ResponseEntity.ok(new XgeneCheckResponse(isMutant));
        } catch (Exception e) {
            log.error("Mutant not Found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Return the history of all subjects checked", description = "Return a list with all subjects checked")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subjects checked successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = XgeneCheckHistory[].class)) }),

            @ApiResponse(responseCode = "404", description = "Subjects not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("all")
    public ResponseEntity<?> getAllSubjects() {
        log.info("Getting all subjects");
        try {
            return ResponseEntity.ok(xgeneService.getAllSubjects());
        } catch (Exception e) {
            log.error("Subjects not Found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subjects not found");
        }
    }

}