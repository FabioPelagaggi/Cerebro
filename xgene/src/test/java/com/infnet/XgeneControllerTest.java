package com.infnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infnet.controller.XgeneController;
import com.infnet.model.XgeneCheckResponse;
import com.infnet.service.XgeneService;

public class XgeneControllerTest {

    @Mock
    private XgeneService xgeneService;

    @InjectMocks
    private XgeneController xgeneController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsMutant() {
        Long mutantId = 12345L;
        boolean isMutant = true;

        when(xgeneService.check(mutantId)).thenReturn(isMutant);

        XgeneCheckResponse response = xgeneController.isMutant(mutantId);

        assertEquals(isMutant, response.isMutant());
    }
}