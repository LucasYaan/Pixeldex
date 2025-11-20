package estruturadados.models;

import estruturadados.objects.Pixel;
import estruturadados.structures.LinkedList;

public class PixelCollection {
    private LinkedList<Pixel> pixels;
    private int size;

    public PixelCollection() {
        this.pixels = new LinkedList<>();
    }

    public boolean addPixel(Pixel pixel) {
        pixels.add(pixel);
        size++;
        return true;
    }

    public boolean removePixel(Pixel pixel) {
        try {
            pixels.remove(pixel);
            size--;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LinkedList<Pixel> getPixels() {
        return pixels;
    }

    public boolean addPixelAt(int index, Pixel pixel) {
        try {
            pixels.addAt(index, pixel);
            size++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getSize() {
        return size;
    }
}
