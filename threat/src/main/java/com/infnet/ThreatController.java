package com.infnet;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/threats")
public class ThreatController {

    private final ThreatService threatService;

    @GetMapping
    public List<ThreatNotificationModel> checkThreats() {
        log.info("Checking threats");
        return threatService.checkThreats();
    }

    @GetMapping("{id}")
    public ThreatNotificationModel checkThreat(@PathVariable("id") Long id) {
        log.info("Checking threat for mutant with id: {}", id);
        return threatService.checkThreat(id);
    }

}
