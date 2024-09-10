package com.infnet;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MutantTest {

    @Test
    public void testMutantConstructorAndGetters() {
        String[] powers = {"Regeneration", "Claws"};
        MutantModel mutant = MutantModel.builder()
                .id(1L)
                .name("Wolverine")
                .realName("Logan")
                .level("Omega")
                .mutantPowers(powers)
                .description("A mutant with regenerative healing factor and retractable claws.")
                .image("wolverine.png")
                .build();

        assertEquals(1L, mutant.getId());
        assertEquals("Wolverine", mutant.getName());
        assertEquals("Logan", mutant.getRealName());
        assertEquals("Omega", mutant.getLevel());
        assertArrayEquals(powers, mutant.getMutantPowers());
        assertEquals("A mutant with regenerative healing factor and retractable claws.", mutant.getDescription());
        assertEquals("wolverine.png", mutant.getImage());
    }

    @Test
    public void testMutantSetters() {
        MutantModel mutant = new MutantModel();
        String[] powers = {"Magnetism"};

        mutant.setId(2L);
        mutant.setName("Magneto");
        mutant.setRealName("Erik Lehnsherr");
        mutant.setLevel("Alpha");
        mutant.setMutantPowers(powers);
        mutant.setDescription("A mutant with the ability to generate and control magnetic fields.");
        mutant.setImage("magneto.png");

        assertEquals(2L, mutant.getId());
        assertEquals("Magneto", mutant.getName());
        assertEquals("Erik Lehnsherr", mutant.getRealName());
        assertEquals("Alpha", mutant.getLevel());
        assertArrayEquals(powers, mutant.getMutantPowers());
        assertEquals("A mutant with the ability to generate and control magnetic fields.", mutant.getDescription());
        assertEquals("magneto.png", mutant.getImage());
    }
}