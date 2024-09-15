package com.infnet.controller;

import java.util.List;

import org.apiguardian.api.API;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.model.ThreatNotificationModel;
import com.infnet.service.ThreatService;

import io.swagger.v3.oas.annotations.Operation;
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
            @ApiResponse(responseCode = "200", description = "Threats checked successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<ThreatNotificationModel> checkThreats() {
        log.info("Checking threats");
        return threatService.checkThreats();
    }

    
    @Operation(summary = "Check threat", description = "Check threat for a mutant", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Threat checked successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("{id}")
    public ThreatNotificationModel checkThreat(@PathVariable("id") Long id) {
        log.info("Checking threat for mutant with id: {}", id);
        return threatService.checkThreat(id);
    }

}
