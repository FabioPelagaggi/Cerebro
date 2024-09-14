package com.infnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.infnet.model.XgeneCheckHistory;
import com.infnet.repository.XgeneRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class XgeneRepositoryTest {

    @Autowired
    private XgeneRepository xgeneRepository;

    @Test
    public void testSaveAndFindById() {
        LocalDateTime now = LocalDateTime.now();
        XgeneCheckHistory history = XgeneCheckHistory.builder()
                .mutantId(12345L)
                .xgene(true)
                .checkedAt(now)
                .build();

        XgeneCheckHistory savedHistory = xgeneRepository.save(history);
        assertNotNull(savedHistory);
        assertNotNull(savedHistory.getId());

        Optional<XgeneCheckHistory> foundHistory = xgeneRepository.findById(savedHistory.getId());
        assertNotNull(foundHistory);
        assertEquals(12345L, foundHistory.get().getMutantId());
        assertEquals(true, foundHistory.get().isXgene());
        assertEquals(now, foundHistory.get().getCheckedAt());
    }

    @Test
    public void testDelete() {
        LocalDateTime now = LocalDateTime.now();
        XgeneCheckHistory history = XgeneCheckHistory.builder()
                .mutantId(12345L)
                .xgene(true)
                .checkedAt(now)
                .build();

        XgeneCheckHistory savedHistory = xgeneRepository.save(history);
        Long id = savedHistory.getId();
        assertNotNull(id);

        xgeneRepository.deleteById(id);
        Optional<XgeneCheckHistory> deletedHistory = xgeneRepository.findById(id);
        assertEquals(Optional.empty(), deletedHistory);
    }
}