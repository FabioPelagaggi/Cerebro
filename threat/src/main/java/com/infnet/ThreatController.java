package com.infnet;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/threats")
public class ThreatController {

    private final ThreatService threatService;

    @GetMapping
    public List<ThreatNotificationModel> checkThreats() {
        return threatService.checkThreats();
    }
        
}
