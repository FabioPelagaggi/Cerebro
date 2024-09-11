package com.infnet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HistoryRepositoryTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a new HistoryModel instance with X-Men character data
        HistoryModel history = new HistoryModel();
        history.setId(1L);
        history.setName("Wolverine");

        // Save the entity
        historyRepository.save(history);

        // Retrieve the entity
        HistoryModel foundHistory = historyRepository.findById(1L).orElse(null);

        // Verify the entity
        assertThat(foundHistory).isNotNull();
        assertThat(foundHistory.getName()).isEqualTo("Wolverine");
    }

    @Test
    public void testDelete() {
        // Create a new HistoryModel instance with X-Men character data
        HistoryModel history = new HistoryModel();
        history.setId(2L);
        history.setName("Cyclops");

        // Save the entity
        historyRepository.save(history);

        // Delete the entity
        historyRepository.deleteById(2L);

        // Verify the entity is deleted
        HistoryModel foundHistory = historyRepository.findById(2L).orElse(null);
        assertThat(foundHistory).isNull();
    }
}