package com.infnet;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.infnet.model.HistoryModel;

public class HistoryModelTest {

    @Test
    public void testHistoryModelBuilder() {
        LocalDateTime now = LocalDateTime.now();
        HistoryModel historyModel = HistoryModel.builder()
                .id(1L)
                .mutantId(100L)
                .name("Jubilee")
                .registerType("CREATED")
                .createdAt(now)
                .build();

        assertThat(historyModel.getId()).isEqualTo(1L);
        assertThat(historyModel.getMutantId()).isEqualTo(100L);
        assertThat(historyModel.getName()).isEqualTo("Jubilee");
        assertThat(historyModel.getRegisterType()).isEqualTo("CREATED");
        assertThat(historyModel.getCreatedAt()).isEqualTo(now);
    }

    @Test
    public void testHistoryModelNoArgsConstructor() {
        HistoryModel historyModel = new HistoryModel();

        assertThat(historyModel.getId()).isNull();
        assertThat(historyModel.getMutantId()).isNull();
        assertThat(historyModel.getName()).isNull();
        assertThat(historyModel.getRegisterType()).isNull();
        assertThat(historyModel.getCreatedAt()).isNull();
    }

    @Test
    public void testHistoryModelAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        HistoryModel historyModel = new HistoryModel(1L, 100L, "Jubilee", "CREATED", now);

        assertThat(historyModel.getId()).isEqualTo(1L);
        assertThat(historyModel.getMutantId()).isEqualTo(100L);
        assertThat(historyModel.getName()).isEqualTo("Jubilee");
        assertThat(historyModel.getRegisterType()).isEqualTo("CREATED");
        assertThat(historyModel.getCreatedAt()).isEqualTo(now);
    }

    @Test
    public void testHistoryModelSetters() {
        LocalDateTime now = LocalDateTime.now();
        HistoryModel historyModel = new HistoryModel();
        historyModel.setId(1L);
        historyModel.setMutantId(100L);
        historyModel.setName("Jubilee");
        historyModel.setRegisterType("CREATED");
        historyModel.setCreatedAt(now);

        assertThat(historyModel.getId()).isEqualTo(1L);
        assertThat(historyModel.getMutantId()).isEqualTo(100L);
        assertThat(historyModel.getName()).isEqualTo("Jubilee");
        assertThat(historyModel.getRegisterType()).isEqualTo("CREATED");
        assertThat(historyModel.getCreatedAt()).isEqualTo(now);
    }
}
