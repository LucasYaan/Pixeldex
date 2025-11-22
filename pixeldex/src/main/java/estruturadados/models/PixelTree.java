package estruturadados.models;

import java.util.Comparator;

import estruturadados.objects.Pixel;
import estruturadados.structures.BST;

public class PixelTree {

    private BST<Integer, Pixel> tree;
    Comparator<Integer> cmp = Comparator.naturalOrder();
    private int size;

    public PixelTree() {
        this.tree = new BST<>(cmp);
    }

    public boolean addPixel(Pixel pixel) {
        tree.insert(pixel.getId(), pixel);
        size++;
        return true;
    }

    public boolean removePixel(Pixel pixel) {
        try {
            tree.remove(pixel.getId());
            size--;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Pixel findPixelById(int id) {
        return tree.search(id);
    }

    public void printPixelsInOrder() {
        tree.inOrder();
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return tree.height();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PixelTree com ").append(size).append(" pixels\n");
        return sb.toString();
    }
}
