package com.infnet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infnet.model.XgeneCheckHistory;
import com.infnet.repository.XgeneRepository;

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

    public List<XgeneCheckHistory> getAllSubjects() {
        return xgeneRepository.findAll();
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