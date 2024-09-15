package com.infnet.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.model.ThreatNotificationModel;
import com.infnet.service.ThreatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/threats", produces = "application/json")
@Tag(name = "Threat API", description = "Threat API Information")
public class ThreatController {

    private final ThreatService threatService;

    @Operation(summary = "Check threats", description = "Check threats for all mutants", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Threats checked successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ThreatNotificationModel[].class))),
            @ApiResponse(responseCode = "400", description = "No Mutants found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<?> checkThreats() {
        log.info("Checking threats");
        try {
            List<ThreatNotificationModel> threats = threatService.checkThreats();
            return ResponseEntity.ok(threats);
        } catch (Exception e) {
            log.error("Error checking threats", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Mutants found");
        }
    }

    @Operation(summary = "Check threat", description = "Check threat for a mutant", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Threat checked successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ThreatNotificationModel.class))),
            @ApiResponse(responseCode = "400", description = "No Mutant found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("{id}")
    public ResponseEntity<?> checkThreat(@PathVariable("id") Long id) {
        log.info("Checking threat for mutant with id: {}", id);
        try {
            ThreatNotificationModel threat = threatService.checkThreat(id);
            return ResponseEntity.ok(threat);
        } catch (Exception e) {
            log.error("Error checking threat", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Mutant found");
        }
    }

}
