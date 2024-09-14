package com.infnet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infnet.model.MutantModel;
import com.infnet.model.MutantRegistrationRequest;
import com.infnet.repository.MutantRepository;
import com.infnet.service.MutantService;

public class MutantServiceTest {

    @Mock
    private MutantRepository mutantRepository;

    @InjectMocks
    private MutantService mutantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterMutant() {
        // Arrange
        MutantRegistrationRequest request = new MutantRegistrationRequest(
                "Wolverine", "Logan", "Omega", new String[]{"Regeneration", "Claws"},
                "A mutant with regenerative healing factor and retractable claws.", "wolverine.png"
        );

        // Act
        mutantService.registerMutant(request);

        // Assert
        verify(mutantRepository, times(1)).save(
                MutantModel.builder()
                        .name(request.name())
                        .realName(request.realName())
                        .level(request.level())
                        .mutantPowers(request.mutantPowers())
                        .description(request.description())
                        .image(request.image())
                        .build()
        );
    }
}