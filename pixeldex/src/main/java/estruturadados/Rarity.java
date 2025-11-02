package estruturadados;

public class Rarity {
    private int level;
    private String description;

    Rarity(int level, String description) {
        this.level = level;
        this.description = description;
    }
    
    public int getId() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}
