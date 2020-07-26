package my.projects.kata6;

public class Experience {
    int value;
    private Rank rank;
    private int level;

    public Experience() {
        this.value = 100;
        this.level = 1;
        this.rank = Rank.PUSHOVER;
    }

    /**
     * For testing
     *
     * @param value acquired experience value
     * @param level acquired
     * @param rank  the Rank of the warrior
     */
    public Experience(int value, int level, Rank rank) {
        this.value = value;
        this.level = level;
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    public Rank getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public void increaseInBattle(int enemyLevel) {
        if (enemyLevel > level) {
            int levelDiff = enemyLevel - level;
            int formula = 20 * levelDiff * levelDiff;
            increaseValue(formula);
        } else if (enemyLevel == level) {
            increaseValue(10);
        } else if (enemyLevel == level - 1) {
            increaseValue(5);
        }
    }

    public void increaseValue(int value) {
        if (this.value + value <= 10000) {
            this.value += value;
        } else {
            this.value = 10000;
        }
        increaseLevelIfNecessary();
        increaseRankIfNecessary();
    }

    private void increaseLevelIfNecessary() {
        int levelFromExperience = value / 100;
        if (levelFromExperience > this.level) {
            this.level = levelFromExperience;
        }
    }

    private void increaseRankIfNecessary() {
        Rank calculatedRank = Rank.fromLevel(this.level);
        if (calculatedRank != this.rank) {
            this.rank = calculatedRank;
        }
    }
}
