package com.infnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.infnet.model.MutantModel;
import com.infnet.repository.MutantRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MutantRepositoryTest {

    @Autowired
    private MutantRepository mutantRepository;

    private MutantModel mutant;

    @BeforeEach
    public void setUp() {
        mutant = MutantModel.builder()
                .name("Wolverine")
                .realName("Logan")
                .level("Omega")
                .mutantPowers(new String[] { "Regeneration", "Claws" })
                .description("A mutant with regenerative healing factor and retractable claws.")
                .image("wolverine.png")
                .build();
        mutantRepository.save(mutant);
    }

    @Test
    public void testFindById() {
        Optional<MutantModel> foundMutant = mutantRepository.findById(mutant.getId());
        assertTrue(foundMutant.isPresent());
        assertEquals(mutant.getName(), foundMutant.get().getName());
    }

    @Test
    public void testFindAll() {
        List<MutantModel> mutants = mutantRepository.findAll();
        assertFalse(mutants.isEmpty());
    }

    @Test
    public void testSave() {
        MutantModel newMutant = MutantModel.builder()
                .name("Magneto")
                .realName("Erik Lehnsherr")
                .level("Alpha")
                .mutantPowers(new String[] { "Magnetism" })
                .description("A mutant with the ability to generate and control magnetic fields.")
                .image("magneto.png")
                .build();
        MutantModel savedMutant = mutantRepository.save(newMutant);
        assertNotNull(savedMutant.getId());
        assertEquals("Magneto", savedMutant.getName());
    }

    @Test
    public void testDelete() {
        mutantRepository.delete(mutant);
        Optional<MutantModel> deletedMutant = mutantRepository.findById(mutant.getId());
        assertFalse(deletedMutant.isPresent());
    }
}