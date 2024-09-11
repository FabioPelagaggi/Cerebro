package com.infnet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/xgene")
public class XgeneController {

    private final XgeneService xgeneService;

    @GetMapping("{mutantId}")
    public XgeneCheckResponse isMutant(@PathVariable("mutantId") Long mutantId) {
        boolean isMutant = xgeneService.check(mutantId);
        return new XgeneCheckResponse(isMutant);
    }

}