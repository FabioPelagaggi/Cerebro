package com.infnet;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public record HistoryService(HistoryRepository historyRepository) {
    public void registerHistory(HistoryRegistrationRequest request) {
        HistoryModel history = HistoryModel.builder()
                .mutantId(request.mutantId())
                .name(request.name())
                .registerType(request.registerType())
                .createdAt(LocalDateTime.now())
                .build();
        
        historyRepository.save(history);
    }
}
