package com.infnet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infnet.model.HistoryModel;
import com.infnet.model.HistoryRegistrationRequest;
import com.infnet.repository.HistoryRepository;

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

    public List<HistoryModel> getAllHistories() {
        return historyRepository.findAll();
    }
}
