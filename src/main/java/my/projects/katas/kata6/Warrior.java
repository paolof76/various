package my.projects.katas.kata6;

import java.util.ArrayList;
import java.util.List;

public class Warrior {
    private Experience experience;
    private List<String> achievements = new ArrayList<>();

    public Warrior() {
        this.experience = new Experience();
    }

    public List<String> achievements() {
        return achievements;
    }

    public String rank() {
        return experience.getRank().readable();
    }

    public int experience() {
        return experience.getValue();
    }

    public int level() {
        return experience.getLevel();
    }

    /**
     * For testing
     *
     * @param experience the acquired experience
     * @param level      the warrior level
     * @param rank       the warrior rank
     */
    void setExperience(int experience, int level, Rank rank) {
        this.experience = new Experience(experience, level, rank);
    }

    public String training(String achievement, int experience, int minimumLevelRequirement) {
        if (this.experience.getLevel() >= minimumLevelRequirement) {
            this.achievements.add(achievement);
            this.experience.increaseValue(experience);
            return achievement;
        } else {
            return "Not strong enough";
        }
    }

    public String battle(int level) {
        if (level < 1 || level > 100) {
            return "Invalid level";
        }

        //if one rank lower and at least 5 levels lower: defeated
        if (level > experience.getLevel() &&
                Rank.fromLevel(level).getMaxLevelValue() > experience.getRank().getMaxLevelValue() &&
                level - experience.getLevel() >= 5) {
            return "You've been defeated";
        }
        String result = calculateTypeOfVictoryMessage(level);
        experience.increaseInBattle(level);
        return result;
    }

    /*
    Every successful battle will also return one of three responses:
    "Easy fight", "A good fight", "An intense fight".
    Return "Easy fight" if your warrior is 2 or more levels higher than your enemy's level.
    Return "A good fight" if your warrior is either 1 level higher or equal to your enemy's level.
    Return "An intense fight" if your warrior's level is lower than the enemy's level.
     */
    private String calculateTypeOfVictoryMessage(int level) {
        if (experience.getLevel() >= level + 2) {
            return "Easy fight";
        } else if (experience.getLevel() == level || experience.getLevel() - 1 == level) {
            return "A good fight";
        } else if (experience.getLevel() < level) {
            return "An intense fight";
        }
        throw new IllegalArgumentException("Unexpected level, not possible to generate a victory message!");
    }

}
