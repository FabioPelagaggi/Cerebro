package com.infnet;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/histories")
public record HistoryController(HistoryService historyService) {

    @PostMapping
    public void registerHistory(@RequestBody HistoryRegistrationRequest historyRegistrationRequest){
        log.info("Registering history: {}", historyRegistrationRequest);
        historyService.registerHistory(historyRegistrationRequest);
    }

    @GetMapping("all")
    public List<HistoryModel> getAllHistories(){
        log.info("Getting all histories");
        return historyService.getAllHistories();
    }
} 
