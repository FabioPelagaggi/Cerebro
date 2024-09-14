package com.infnet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.refEq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.controller.MutantController;
import com.infnet.model.MutantRegistrationRequest;
import com.infnet.service.MutantService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MutantController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterMutant() throws Exception {
        // Arrange
        MutantRegistrationRequest request = new MutantRegistrationRequest(
                "Wolverine", "Logan", "Omega", new String[]{"Regeneration", "Claws"},
                "A mutant with regenerative healing factor and retractable claws.", "wolverine.png"
        );

        // Act & Assert
        mockMvc.perform(post("/api/mutants")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(mutantService).registerMutant(refEq(request));
    }
}