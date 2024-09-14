package com.infnet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.model.XgeneCheckHistory;
import com.infnet.model.XgeneCheckResponse;
import com.infnet.service.XgeneService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/xgene")
public class XgeneController {

    private final XgeneService xgeneService;
    private static final Logger log = LoggerFactory.getLogger(XgeneController.class); // Initialize the logger object

    @GetMapping("{mutantId}")
    public XgeneCheckResponse isMutant(@PathVariable("mutantId") Long mutantId) {
        boolean isMutant = xgeneService.check(mutantId);

        log.info("Subject with ID {} is {}", mutantId, isMutant ? "mutant" : "not mutant");
        
        return new XgeneCheckResponse(isMutant);
    }

    @GetMapping("all")
    public List<XgeneCheckHistory> getAllSubjects() {
        log.info("Getting all subjects");
        return xgeneService.getAllSubjects();
    }

}