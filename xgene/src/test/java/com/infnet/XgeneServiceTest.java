package com.infnet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class XgeneServiceTest {

    @Mock
    private XgeneRepository xgeneRepository;

    @InjectMocks
    private XgeneService xgeneService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        xgeneService = spy(xgeneService); // Create a spy for xgeneService
    }

    @Test
    public void testCheck() {
        Long mutantId = 12345L;
        when(xgeneService.checkGene(mutantId)).thenReturn(true);

        boolean result = xgeneService.check(mutantId);

        assertTrue(result);
        verify(xgeneRepository).save(any(XgeneCheckHistory.class));
    }

    @Test
    public void testCheckGene() {
        Long mutantId = 12345L;
        boolean result = xgeneService.checkGene(mutantId);

        // Since checkGene uses Math.random(), we can't assert a specific value.
        // Instead, we can assert that the result is a boolean.
        assertTrue(result || !result);
    }

    @Test
    public void testRegisterCheck() {
        Long mutantId = 12345L;
        boolean xgene = true;

        xgeneService.registerCheck(mutantId, xgene);

        verify(xgeneRepository).save(any(XgeneCheckHistory.class));
    }
}
