package com.infnet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MutantModel {

    private Long id;
    private String name;
    private String realName;
    private String level;
    private String[] mutantPowers;
    private String description;
    private String image;
}