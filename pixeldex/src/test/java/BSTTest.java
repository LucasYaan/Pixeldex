import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estruturadados.objects.Pixel;
import estruturadados.structures.BST;
import estruturadados.structures.Rarity;

public class BSTTest {

    private BST<Integer, Pixel> tree;
    private Comparator<Integer> cmp = Comparator.naturalOrder();

    private Pixel p(int id) {
        return new Pixel(id, "P" + id, id * 10, new Rarity(1, "COMUM"));
    }

    @BeforeEach
    void setup() {
        tree = new BST<>(cmp);
    }

    // ---------- INSERÇÃO ----------
    @Test
    void testInsercaoCrescente() {
        tree.insert(1, p(1));
        tree.insert(2, p(2));
        tree.insert(3, p(3));

        assertEquals(2, tree.height());
    }

    @Test
    void testInsercaoInversa() {
        tree.insert(3, p(3));
        tree.insert(2, p(2));
        tree.insert(1, p(1));

        assertEquals(2, tree.height());
    }

    // ---------- SEARCH ----------
    @Test
    void testSearchExistente() {
        tree.insert(1, p(1));
        assertNotNull(tree.search(1));
    }

    @Test
    void testSearchAusente() {
        assertNull(tree.search(99));
    }

    // ---------- REMOVE ----------
    @Test
    void testRemoveFolha() {
        tree.insert(2, p(2));
        tree.insert(1, p(1));
        tree.remove(1);

        assertNull(tree.search(1));
    }

    @Test
    void testRemoveUmFilho() {
        tree.insert(2, p(2));
        tree.insert(1, p(1));
        tree.insert(0, p(0));

        tree.remove(1);
        assertNull(tree.search(1));
    }

    @Test
    void testRemoveDoisFilhos() {
        tree.insert(2, p(2));
        tree.insert(1, p(1));
        tree.insert(3, p(3));

        tree.remove(2);
        assertNull(tree.search(2));
    }

    // ---------- RANGE ----------
    @Test
    void testRangeVazio() {
        List<Pixel> result = tree.range(10, 20);
        assertTrue(result.isEmpty());
    }

    @Test
    void testRangeNaoVazio() {
        tree.insert(1, p(1));
        tree.insert(5, p(5));
        tree.insert(10, p(10));

        List<Pixel> result = tree.range(2, 9);

        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getId());
    }

    // ---------- HEIGHT ----------
    @Test
    void testHeightEsperado() {
        assertEquals(-1, tree.height());

        tree.insert(1, p(1));
        assertEquals(0, tree.height());

        tree.insert(2, p(2));
        assertEquals(1, tree.height());
    }
}
