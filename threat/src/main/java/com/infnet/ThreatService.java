package com.infnet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ThreatService {
    private final RestTemplate restTemplate;

    public List<ThreatNotificationModel> checkThreats() {
        MutantModel[] allMutants = restTemplate.getForObject(
                "http://MUTANT/api/mutants/all",
                MutantModel[].class);

        List<ThreatNotificationModel> threatReport = new ArrayList<>();

        for (MutantModel mutant : allMutants) {
            int threatLevel = mutant.getMutantPowers().length;

            if (mutant.getLevel() == "Beta") {
                threatLevel = threatLevel * 2;
            } else if (mutant.getLevel() == "Alpha") {
                threatLevel = threatLevel * 3;
            } else if (mutant.getLevel() == "Omega") {
                threatLevel = threatLevel * 4;
            }

            threatReport.add(ThreatNotificationModel.builder()
                    .name(mutant.getName())
                    .threatLevel(threatLevel)
                    .build());
        }

        return threatReport;

    }
}
