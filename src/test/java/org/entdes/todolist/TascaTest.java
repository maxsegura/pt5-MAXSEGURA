package org.entdes.todolist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests per a la classe Tasca.
 * Es prova el correcte funcionament dels seus atributs i mètodes.
 */
class TascaTest {

    private Tasca tasca;

    /**
     * Configuració inicial abans de cada test.
     * Es crea una instància de Tasca amb una descripció predeterminada.
     */
    @BeforeEach
    void setUp() {
        tasca = new Tasca("Tasca de prova");
    }

    /**
     * Verifica que l'ID assignat a una tasca sigui un valor positiu.
     */
    @Test
    void testGetId() {
        int id = tasca.getId();
        assertTrue(id > 0, "L'ID hauria de ser superior a 0");
    }

    /**
     * Prova l'establiment i obtenció de la descripció d'una tasca.
     */
    @Test
    void testSetAndGetDescripcio() {
        tasca.setDescripcio("Tasca actualitzada");
        assertEquals("Tasca actualitzada", tasca.getDescripcio());
    }

    /**
     * Prova l'establiment i obtenció de l'estat de completada d'una tasca.
     */
    @Test
    void testSetAndGetCompletada() {
        tasca.setCompletada(true);
        assertTrue(tasca.isCompletada());
        tasca.setCompletada(false);
        assertFalse(tasca.isCompletada());
    }

    /**
     * Prova l'establiment i obtenció de la data d'inici d'una tasca.
     */
    @Test
    void testSetAndGetDataInici() {
        LocalDate date = LocalDate.of(2025, 2, 24);
        tasca.setDataInici(date);
        assertEquals(date, tasca.getDataInici());
    }

    /**
     * Prova l'establiment i obtenció de la data de fi prevista d'una tasca.
     */
    @Test
    void testSetAndGetDataFiPrevista() {
        LocalDate date = LocalDate.of(2025, 3, 1);
        tasca.setDataFiPrevista(date);
        assertEquals(date, tasca.getDataFiPrevista());
    }

    /**
     * Prova l'establiment i obtenció de la prioritat d'una tasca.
     */
    @Test
    void testSetAndGetPrioritat() {
        tasca.setPrioritat(5);
        assertEquals(5, tasca.getPrioritat());
    }

    /**
     * Prova l'establiment i obtenció de la data de fi real d'una tasca.
     */
    @Test
    void testSetAndGetDataFiReal() {
        LocalDate date = LocalDate.of(2025, 2, 28);
        tasca.setDataFiReal(date);
        assertEquals(date, tasca.getDataFiReal());
    }

    /**
     * Prova la representació en format de text d'una tasca completada.
     */
    @Test
    void testToStringCompletada() {
        tasca.setCompletada(true);
        tasca.setDescripcio("Tasca completada");
        assertEquals("Tasca completada: Completada", tasca.toString());
    }

    /**
     * Prova la representació en format de text d'una tasca pendent.
     */
    @Test
    void testToStringPendent() {
        tasca.setCompletada(false);
        tasca.setDescripcio("Tasca pendent");
        assertEquals("Tasca pendent: Pendent", tasca.toString());
    }

    /**
     * Verifica que cada nova instància de Tasca té un ID únic.
     */
    @Test
    void testConstructorSetsId() {
        Tasca tasca2 = new Tasca("Segona tasca");
        assertNotEquals(tasca.getId(), tasca2.getId(), "Cada tasca hauria de tenir un ID únic");
    }
}