package com.infnet.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.model.HistoryModel;
import com.infnet.model.HistoryRegistrationRequest;
import com.infnet.service.HistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/histories")
public record HistoryController(HistoryService historyService) {

    @Operation(summary = "Register a history", description = "Register a history of a changes in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "History registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<?> registerHistory(@RequestBody HistoryRegistrationRequest historyRegistrationRequest) {
        log.info("Registering history: {}", historyRegistrationRequest);
        try {
            historyService.registerHistory(historyRegistrationRequest);
            return ResponseEntity.ok("Mutant registered successfully");
        } catch (Exception e) {
            log.error("Internal server error", e);
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(summary = "Get all history entries", description = "Get all entries registered in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "History entries retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HistoryModel[].class))),
            @ApiResponse(responseCode = "400", description = "No entries found on the database", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("all")
    public ResponseEntity<?> getAllHistories() {
        log.info("Getting all histories");
        try {
            return ResponseEntity.ok(historyService.getAllHistories());
        } catch (Exception e) {
            log.error("Internal server error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No entries found on the database");
        }
    }
}
