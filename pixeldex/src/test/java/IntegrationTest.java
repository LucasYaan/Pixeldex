import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estruturadados.models.PixelCollection;
import estruturadados.objects.Pixel;
import estruturadados.structures.Rarity;

public class IntegrationTest {

    private PixelCollection collection;

    private Pixel p(int id) {
        return new Pixel(id, "Pixel" + id, id * 10, new Rarity(1, "COMUM"));
    }

    @BeforeEach
    void setup() {
        collection = new PixelCollection();
    }

    // ---------------- ADD ----------------
    @Test
    void testAddPixelIntegrado() {
        Pixel pixel = p(1);

        boolean result = collection.addPixel(pixel);

        assertTrue(result);
        assertEquals(1, collection.getSize());
        assertNotNull(collection.getPixels().getHead());
        assertEquals(1, collection.getPixels().getHead().getValue().getId());
    }

    // ---------------- REMOVE ----------------
    @Test
    void testRemovePixelIntegrado() {
        Pixel pixel = p(2);

        collection.addPixel(pixel);
        boolean result = collection.removePixel(pixel);

        assertTrue(result);
        assertEquals(0, collection.getSize());
        assertTrue(collection.getPixels().isEmpty());
    }

    // ---------------- ADD AT INDEX ----------------
    @Test
    void testAddPixelAtIndex() {
        collection.addPixel(p(1));
        collection.addPixel(p(3));

        boolean result = collection.addPixelAt(1, p(2));

        assertTrue(result);
        assertEquals(3, collection.getSize());
        assertEquals(2, collection.getPixels().getHead().getNext().getValue().getId());
    }

    // ---------------- STRING OUTPUT ----------------
    @Test
    void testToStringNaoVazio() {
        collection.addPixel(p(1));
        collection.addPixel(p(2));

        String output = collection.toString();

        assertTrue(output.contains("Pixel1"));
        assertTrue(output.contains("Pixel2"));
    }

    // ---------------- CONSISTÊNCIA DE TAMANHO ----------------
    @Test
    void testSizeConsistenteAposVariasOperacoes() {
        collection.addPixel(p(1));
        collection.addPixel(p(2));
        collection.removePixel(p(1));
        collection.addPixel(p(3));

        assertEquals(2, collection.getSize());
    }
}
