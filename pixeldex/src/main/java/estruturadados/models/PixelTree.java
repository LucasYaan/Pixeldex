package estruturadados.models;

import estruturadados.structures.BST;

public class PixelTree { // ?
    private BST pixels;
    
    public PixelTree() {
        this.pixels = new BST();
    }

    public BST getPixels() {
        return pixels;
    }

    public void insertPixelId(int id) {
        this.pixels.insert(id);
    }
}
