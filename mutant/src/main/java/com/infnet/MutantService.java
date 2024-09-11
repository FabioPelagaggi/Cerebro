package com.infnet;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MutantService {
    private final MutantRepository mutantRepository;
    private final RestTemplate restTemplate;

    public void registerMutant(MutantRegistrationRequest request) {
        MutantModel mutant = MutantModel.builder()
                .name(request.name())
                .realName(request.realName())
                .level(request.level())
                .mutantPowers(request.mutantPowers())
                .description(request.description())
                .image(request.image())
                .build();

        mutantRepository.saveAndFlush(mutant);

        XgeneCheckResponse xgeneCheckResponse = restTemplate.getForObject(
                "http://XGENE/api/xgene/{mutantId}",
                XgeneCheckResponse.class,
                mutant.getId());

        if (!xgeneCheckResponse.isMutant()) {
            mutantRepository.delete(mutant);
        } else {

            HistoryRegistrationRequest historyRegistrationRequest = HistoryRegistrationRequest.builder()
                    .mutantId(mutant.getId())
                    .name(mutant.getName())
                    .registerType("CREATED")
                    .build();

            restTemplate.postForObject("http://HISTORY/api/histories", historyRegistrationRequest, Void.class);
        }

    }
}