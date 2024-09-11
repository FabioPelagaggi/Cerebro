package com.infnet;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class XgeneService {

    private final XgeneRepository xgeneRepository;

    public boolean check(Long mutantId) {
        boolean isMutant = checkGene(mutantId);
        registerCheck(mutantId, isMutant);
        return isMutant;
    }

    public boolean checkGene(Long mutantId) {
        return Math.random() < 0.5;
    }

    public void registerCheck(Long mutantId, boolean xgene) {
        xgeneRepository.save(XgeneCheckHistory.builder()
                .mutantId(mutantId)
                .xgene(xgene)
                .checkedAt(LocalDateTime.now())
                .build());
    }

}