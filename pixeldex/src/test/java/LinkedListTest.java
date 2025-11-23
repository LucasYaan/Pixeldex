import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estruturadados.objects.Pixel;
import estruturadados.structures.LinkedList;
import estruturadados.structures.Rarity;

public class LinkedListTest {

    private LinkedList<Pixel> list;

    private Pixel p(int id) {
        return new Pixel(id, "P" + id, id * 10, new Rarity(1, "COMUM"));
    }

    @BeforeEach
    void setup() {
        list = new LinkedList<>();
    }

    // ---------- INSERÇÕES DIVERSAS ----------
    @Test
    void testInsercoesDiversas() {
        list.add(p(1));
        list.add(p(2));
        list.addFirst(p(0));

        assertEquals(3, list.getSize());
        assertEquals(0, list.getHead().getValue().getId());
    }

    // ---------- REVERSE ----------
    @Test
    void testReverseListaVazia() {
        assertDoesNotThrow(() -> list.reverse());
        assertEquals(0, list.getSize());
    }

    @Test
    void testReverseListaUmElemento() {
        list.add(p(1));
        list.reverse();
        assertEquals(1, list.getHead().getValue().getId());
    }

    @Test
    void testReverseListaVarios() {
        list.add(p(1));
        list.add(p(2));
        list.add(p(3));

        list.reverse();

        assertEquals(3, list.getHead().getValue().getId());
        assertEquals(1, list.getTail().getValue().getId());
    }

    // ---------- UNIQUE ----------
    @Test
    void testUniqueComDuplicatasIntercaladas() {
        list.add(p(1));
        list.add(p(2));
        list.add(p(1));
        list.add(p(3));
        list.add(p(2));

        list.unique();

        assertEquals(3, list.getSize());
        assertEquals(1, list.getHead().getValue().getId());
    }

    // ---------- MOVE ----------
    @Test
    void testMoveInicioParaFim() {
        list.add(p(1));
        list.add(p(2));
        list.add(p(3));

        list.move(0, 2);

        assertEquals(2, list.getHead().getValue().getId());
        assertEquals(1, list.getTail().getValue().getId());
    }

    @Test
    void testMoveFimParaInicio() {
        list.add(p(1));
        list.add(p(2));
        list.add(p(3));

        list.move(2, 0);

        assertEquals(3, list.getHead().getValue().getId());
    }

    // ---------- SLICE ----------
    @Test
    void testSliceLimiteInvalido() {
        list.add(p(1));
        list.add(p(2));

        assertDoesNotThrow(() -> list.slice(10, 20));
    }
}
