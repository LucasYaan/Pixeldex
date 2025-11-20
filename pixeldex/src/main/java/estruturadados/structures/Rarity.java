package estruturadados.structures;

public class Rarity {
    private int level;
    private String description;

    public Rarity(int level, String description) {
        this.level = level;
        this.description = description;
    }
    
    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}
