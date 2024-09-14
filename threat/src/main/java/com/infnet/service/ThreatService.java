package com.infnet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infnet.model.MutantModel;
import com.infnet.model.ThreatNotificationModel;
import com.infnet.producer.ThreatWarningProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ThreatService {
    private final RestTemplate restTemplate;
    private final ThreatWarningProducer threatWarningProducer;

    public List<ThreatNotificationModel> checkThreats() {
        MutantModel[] allMutants = restTemplate.getForObject(
                "http://MUTANT/api/mutants/all",
                MutantModel[].class);

        List<ThreatNotificationModel> threatReport = new ArrayList<>();

        for (MutantModel mutant : allMutants) {
            int threatLevel = calcThreat(mutant);

            ThreatNotificationModel threatNotificationModel = ThreatNotificationModel.builder()
                    .mutantId(mutant.getId())
                    .name(mutant.getName())
                    .threatLevel(threatLevel)
                    .build();

            threatReport.add(threatNotificationModel);

            if (threatLevel > 5) {
                sendThreatWarning(threatNotificationModel);
            }
        }

        return threatReport;

    }

    public ThreatNotificationModel checkThreat(Long mutantId) {
        MutantModel mutant = restTemplate.getForObject(
                "http://MUTANT/api/mutants/{id}",
                MutantModel.class,
                mutantId);

        int threatLevel = calcThreat(mutant);

        return ThreatNotificationModel.builder()
                .name(mutant.getName())
                .threatLevel(threatLevel)
                .build();
    }

    private int calcThreat(MutantModel mutant) {
        int threatLevel = mutant.getMutantPowers().length;

        if (mutant.getLevel().equalsIgnoreCase("Beta")) {
            threatLevel = threatLevel * 2;
        } else if (mutant.getLevel().equalsIgnoreCase("Alpha")) {
            threatLevel = threatLevel * 3;
        } else if (mutant.getLevel().equalsIgnoreCase("Omega")) {
            threatLevel = threatLevel * 4;
        }

        return threatLevel;
    }

    private void sendThreatWarning(ThreatNotificationModel threatNotification) {
        try {
            threatWarningProducer.sendWarning(threatNotification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
