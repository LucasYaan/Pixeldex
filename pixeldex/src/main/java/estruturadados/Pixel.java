package estruturadados;

public class Pixel {
    private int id;
    private String name;
    private int power;
    private Rarity rarity;
    private Pixel evolution;

    Pixel(int id, String name, int power, Rarity rarity) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.rarity = rarity;
    }

    Pixel(int id, String name, int power, Rarity rarity, Pixel evolution) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.rarity = rarity;
        this.evolution = evolution;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Pixel getEvolution() {
        return evolution;
    }

}