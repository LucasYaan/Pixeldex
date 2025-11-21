package estruturadados.objects;

import estruturadados.RarityTable;
import estruturadados.structures.Rarity;

public class Pixel {
    private int id;
    private String name;
    private int power;
    private Rarity rarity;


    public Pixel(int id, String name, int power, Rarity rarity) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.rarity = rarity;
    }



    Pixel(int id, String name, int power, String rarity) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.rarity = RarityTable.getRarityByDescription(rarity);
    }

    Pixel(int id, String name, int power, int rarity) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.rarity = RarityTable.getRarityByLevel(rarity);
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

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", power=" + power +
                ", rarity=" + rarity.getDescription() +
                '}';
    }

}