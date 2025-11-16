package estruturadados;

import java.util.Random;

public class RandomPixelFactory {
    private RandomList nameListPrefix;
    private RandomList nameListMiddle;
    private RandomList nameListSuffix;
    private RandomList rarityList;
    
    public RandomPixelFactory() {
        String[] namePrefixes = {"ba", "be", "bi", "bo", "bu",
                                 "da", "de", "di", "do", "du",
                                 "ka", "ke", "ki", "ko", 
                                 "la", "le", "li", "lo", "lu",
                                 "ma", "me", "mi", "mo", "mu",
                                 "ra", "re", "ri", "ro", "ru",
                                 "sa", "se", "si", "so", "su",
                                 "ta", "te", "ti", "to", "tu",
                                 "za", "ze", "zi", "zo", "zu"};
        String[] nameMiddles = {"ba", "be", "bi", "bo", "bu",
                                 "da", "de", "di", "do", "du",
                                 "ka", "ke", "ki", "ko", 
                                 "la", "le", "li", "lo", "lu",
                                 "ma", "me", "mi", "mo", "mu",
                                 "ra", "re", "ri", "ro", "ru",
                                 "ssa", "sse", "ssi", "sso", "ssu",
                                 "ta", "te", "ti", "to", "tu",
                                 "za", "ze", "zi", "zo", "zu"};
        String[] nameSuffixes =
            { "tron", "ton", "ssauro", "zorde", "borg", "max", "byte", "dax", "mon", "trix", "lorde", "dor", "garg", "bellum", "vund"};
        String[] rarities = RarityTable.getRarityDescriptions();

        this.nameListPrefix = new RandomList(namePrefixes);
        this.nameListMiddle = new RandomList(nameMiddles);
        this.nameListSuffix = new RandomList(nameSuffixes);
        this.rarityList = new RandomList(rarities);
    }

    public Pixel createRandomPixel() {
        int id = new Random().nextInt(1000);
        String name = nameListPrefix.getRandomItem() + nameListMiddle.getRandomItem() + nameListSuffix.getRandomItem();
        String rarityDescription = rarityList.getRandomItem();
        Rarity rarity = RarityTable.getRarityByDescription(rarityDescription);
        int power = Math.toIntExact(Math.round(rarity.getLevel() * Math.random() * 100));
        return new Pixel(id, name, power, rarity);
    }
}
