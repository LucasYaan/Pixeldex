package estruturadados;

import estruturadados.structures.Rarity;

public abstract class RarityTable {
    static Rarity[] rarities = {
        new Rarity(1, "Comum"),
        new Rarity(2, "Incomum"),
        new Rarity(3, "Raro"),
        new Rarity(4, "Épico"),
        new Rarity(5, "Lendário")
    };

    public static Rarity getRarityByLevel(int level) {
        for (Rarity rarity : rarities) {
            if (rarity.getLevel() == level) {
                return rarity;
            }
        }
        return null;
    }

    public static Rarity getRarityByDescription(String description) {
        for (Rarity rarity : rarities) {
            if (rarity.getDescription().equalsIgnoreCase(description)) {
                return rarity;
            }
        }
        return null;
    }

    public static Rarity[] getAllRarities() {
        return rarities;
    }

    public static String[] getRarityDescriptions() {
        String[] descriptions = new String[rarities.length];
        for (int i = 0; i < rarities.length; i++) {
            descriptions[i] = rarities[i].getDescription();
        }
        return descriptions;
    }
}
