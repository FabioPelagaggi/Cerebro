package com.infnet;

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
public class XgeneCheckHistory {
    @Id
    @SequenceGenerator(name = "xgene_id_sequence", sequenceName = "xgene_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "xgene_id_sequence")
    private Long id;
    private long mutantId;
    private boolean xgene;
    private LocalDateTime checkedAt;

}

   