package my.projects.katas.kata6;


import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Comparator;

public enum Rank {
    PUSHOVER(9, "Pushover"),
    NOVICE(19, "Novice"),
    FIGHTER(29, "Fighter"),
    WARRIOR(39, "Warrior"),
    VETERAN(49, "Veteran"),
    SAGE(59, "Sage"),
    ELITE(69, "Elite"),
    CONQUEROR(79, "Conqueror"),
    CHAMPION(89, "Champion"),
    MASTER(99, "Master"),
    GREATEST(100, "Greatest");

    private int maxLevelValue;
    private String desc;

    Rank(int maxLevelValue, String desc) {

        this.maxLevelValue = maxLevelValue;
        this.desc = desc;
    }

    public String readable() {
        return desc;
    }

    public int getMaxLevelValue() {
        return maxLevelValue;
    }

    public static Rank fromDescription(String desc) {
        return Arrays.stream(Rank.values()).filter(i -> i.readable().equals(desc)).findFirst()
                .orElseThrow(() -> new InvalidParameterException("No valid Rank found for " + desc));
    }

    public static Rank fromLevel(int level) {
        return Arrays.stream(Rank.values()).filter(i -> level <= i.getMaxLevelValue())
                .min(Comparator.comparing(Rank::getMaxLevelValue))
                .orElseThrow(() -> new RuntimeException("Rank not found for level " + level));
    }
}
