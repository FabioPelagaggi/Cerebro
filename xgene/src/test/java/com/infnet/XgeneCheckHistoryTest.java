package com.infnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.infnet.model.XgeneCheckHistory;

public class XgeneCheckHistoryTest {

    @Test
    public void testXgeneCheckHistoryBuilder() {
        LocalDateTime now = LocalDateTime.now();
        XgeneCheckHistory history = XgeneCheckHistory.builder()
                .id(1L)
                .mutantId(12345L)
                .xgene(true)
                .checkedAt(now)
                .build();

        assertNotNull(history);
        assertEquals(1L, history.getId());
        assertEquals(12345L, history.getMutantId());
        assertEquals(true, history.isXgene());
        assertEquals(now, history.getCheckedAt());
    }

    @Test
    public void testXgeneCheckHistorySettersAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        XgeneCheckHistory history = new XgeneCheckHistory();
        history.setId(1L);
        history.setMutantId(12345L);
        history.setXgene(true);
        history.setCheckedAt(now);

        assertEquals(1L, history.getId());
        assertEquals(12345L, history.getMutantId());
        assertEquals(true, history.isXgene());
        assertEquals(now, history.getCheckedAt());
    }
}