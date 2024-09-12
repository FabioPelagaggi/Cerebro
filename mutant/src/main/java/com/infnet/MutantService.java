package com.infnet;

import java.util.List;

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

    public MutantModel getMutant(Long id) {
        return mutantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Mutant not found"));
    }

    public void deleteMutant(Long id) {
        MutantModel mutant = mutantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Mutant not found"));
        
        mutantRepository.deleteById(id);

        HistoryRegistrationRequest historyRegistrationRequest = HistoryRegistrationRequest.builder()
                .mutantId(id)
                .name(mutant.getName())
                .registerType("DELETED")
                .build();
        
        restTemplate.postForObject("http://HISTORY/api/histories", historyRegistrationRequest, Void.class);
    }

    public void updateMutant(Long id, MutantRegistrationRequest request) {
        MutantModel mutant = mutantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Mutant not found"));

        mutant.setName(request.name());
        mutant.setRealName(request.realName());
        mutant.setLevel(request.level());
        mutant.setMutantPowers(request.mutantPowers());
        mutant.setDescription(request.description());
        mutant.setImage(request.image());

        mutantRepository.saveAndFlush(mutant);

        HistoryRegistrationRequest historyRegistrationRequest = HistoryRegistrationRequest.builder()
                .mutantId(id)
                .name(mutant.getName())
                .registerType("UPDATED")
                .build();

        restTemplate.postForObject("http://HISTORY/api/histories", historyRegistrationRequest, Void.class);
    }

    public List<MutantModel> getAllMutants() {
        return mutantRepository.findAll();
    }
}