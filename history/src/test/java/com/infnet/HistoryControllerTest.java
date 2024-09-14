package com.infnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.controller.HistoryController;
import com.infnet.model.HistoryRegistrationRequest;
import com.infnet.service.HistoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.refEq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HistoryService historyService;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRegisterHistory() throws Exception {
        // Arrange
        HistoryRegistrationRequest request = new HistoryRegistrationRequest(
                1L, "Wolverine", "CREATED");

        // Act & Assert
        mockMvc.perform(post("/api/histories")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(historyService).registerHistory(refEq(request));
    }
}
