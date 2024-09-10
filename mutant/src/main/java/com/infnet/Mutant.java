package com.infnet;

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
public class Mutant {

    @Id
    @SequenceGenerator(name = "mutant_id_sequence", sequenceName = "mutant_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mutant_id_sequence")
    private Long id;
    private String name;
    private String realName;
    private String level;
    private String[] mutantPowers;
    private String description;
    private String image;
}