package estruturadados;

public class PixelCollection {
    private LinkedList<Pixel> pixels;
    private int size;

    public PixelCollection() {
        this.pixels = new LinkedList<>();
    }

    public boolean addPixel(Pixel pixel) {
        pixels.add(pixel);
        return true;
    }

    public boolean removePixel(Pixel pixel) {
        try {
            pixels.remove(pixel);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public LinkedList<Pixel> getPixels() {
        return pixels;
    }

    public boolean addPixelAt(int index, Pixel pixel) {
        try {
            pixels.addAt(index, pixel);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
