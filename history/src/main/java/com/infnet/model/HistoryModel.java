package com.infnet.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoryModel {

    @Id
    @SequenceGenerator(name = "history_id_sequence", sequenceName = "history_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_id_sequence")
    private Long id;
    private Long mutantId;
    private String name;
    private String registerType;
    private LocalDateTime createdAt;

}
