package estruturadados;

public class RandomList {
    private String[] items;
    private int size;

    public RandomList(String[] items) {
        this.items = items;
        this.size = items.length;
    }

    public String getRandomItem() {
        if (size == 0) {
            return null;
        }
        int randomIndex = (int) (Math.random() * size);
        return items[randomIndex];
    }
}
