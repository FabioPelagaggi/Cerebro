package com.infnet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryService historyService;

    @Test
    public void testRegisterHistory() {
        // Create a mock request
        HistoryRegistrationRequest request = new HistoryRegistrationRequest(
                1L, "Wolverine", "RegistrationType");

        // Call the method under test
        historyService.registerHistory(request);

        // Verify that the repository's save method was called with the correct HistoryModel
        verify(historyRepository, times(1)).save(any(HistoryModel.class));
    }
}