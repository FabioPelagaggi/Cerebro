package com.infnet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}