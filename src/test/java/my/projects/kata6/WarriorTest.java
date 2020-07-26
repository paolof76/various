package my.projects.kata6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static my.projects.kata6.Rank.GREATEST;
import static my.projects.kata6.Rank.NOVICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WarriorTest {

    private Warrior warrior;

    @BeforeEach
    public void init() {
        warrior = new Warrior();
    }

    @Test
    public void training() {
        String achievement = "The great white warrior defeated";
        String result =
                warrior.training(achievement, 10, 1);

        assertThat(result).isEqualTo(achievement);
        assertThat(warrior.experience()).isEqualTo(110);
    }

    @Test
    public void training_2() {
        warrior.setExperience(472, 20, Rank.PUSHOVER);
        String achievement = "Do the Hookie Pookie";
        String result =
                warrior.training(achievement, 10000, 20);

        assertThat(result).isEqualTo(achievement);
        assertThat(warrior.experience()).isEqualTo(10000);
    }

    /*
    If a warrior level 1 fights an enemy level 1, they will receive 10 experience points.
     */
    @Test
    public void testExperienceExample1() {
        assertThat(warrior.experience()).isEqualTo(100);
        warrior.battle(1);
        assertThat(warrior.experience()).isEqualTo(110);
    }

    /*
    If a warrior level 1 fights an enemy level 3, they will receive 80 experience points.
    */
    @Test
    public void testExperienceExample2() {
        assertThat(warrior.experience()).isEqualTo(100);
        warrior.battle(3);
        assertThat(warrior.experience()).isEqualTo(180);
    }

    /*
     If a warrior level 5 fights an enemy level 4, they will receive 5 experience points.
     If a warrior level 3 fights an enemy level 9, they will receive 720 experience points,
     resulting in the warrior rising up by at least 7 levels.
     If a warrior level 8 fights an enemy level 13, they will receive 0 experience points and return
     "You've been defeated". (Remember, difference in rank & enemy level being 5 levels higher or more
     must be established for this.)
     If a warrior level 6 fights an enemy level 2, they will receive 0 experience points.
      */
    @Test
    public void testExperienceExample3() {
        warrior.setExperience(100, 5, Rank.PUSHOVER);
        assertThat(warrior.experience()).isEqualTo(100);
        warrior.battle(4);
        assertThat(warrior.experience()).isEqualTo(105);
    }

    /*
     If a warrior level 3 fights an enemy level 9, they will receive 720 experience points,
     resulting in the warrior rising up by at least 7 levels.
  */
    @Test
    public void testExperienceExample4() {
        warrior.setExperience(100, 3, Rank.PUSHOVER);
        assertThat(warrior.experience()).isEqualTo(100);
        warrior.battle(9);
        assertThat(warrior.experience()).isEqualTo(820);
    }


    /*
     If a warrior level 8 fights an enemy level 13, they will receive 0 experience points and return
     "You've been defeated". (Remember, difference in rank & enemy level being 5 levels higher or more
     must be established for this.)
     */
    @Test
    public void testExperienceExample5() {
        warrior.setExperience(100, 8, Rank.PUSHOVER);
        assertThat(warrior.experience()).isEqualTo(100);
        String result = warrior.battle(13);
        assertThat(warrior.experience()).isEqualTo(100);
        assertThat(result).isEqualTo("You've been defeated");
    }

    /*
     If a warrior level 6 fights an enemy level 2, they will receive 0 experience points.
     */
    @Test
    public void testExperienceExample6() {
        warrior.setExperience(100, 6, Rank.PUSHOVER);
        assertThat(warrior.experience()).isEqualTo(100);
        warrior.battle(2);
        assertThat(warrior.experience()).isEqualTo(100);
    }

    @Test
    void battle_invalidLevel() {
        String result = warrior.battle(0);
        assertThat(result).isEqualTo("Invalid level");

        result = warrior.battle(101);
        assertThat(result).isEqualTo("Invalid level");

        result = warrior.battle(200);
        assertThat(result).isEqualTo("Invalid level");
    }

    @Test
    void battle_validLevel() {
        String result = warrior.battle(1);
        assertThat(result).isNotEqualTo("Invalid level");

        result = warrior.battle(50);
        assertThat(result).isNotEqualTo("Invalid level");

        result = warrior.battle(100);
        assertThat(result).isNotEqualTo("Invalid level");
    }

    //Completing a battle against an enemy with the same level as your warrior will be worth 10 experience points.
    @Test
    public void battle_sameLevel() {
        String result = warrior.battle(1);
        assertThat(result).isEqualTo("A good fight");
        assertThat(warrior.experience()).isEqualTo(110);
    }

    @Test
    public void battle_lowerLevel() {
        String result = warrior.battle(1);
        assertThat(result).isEqualTo("A good fight");
        assertThat(warrior.experience()).isEqualTo(110);
    }

    @Test
    public void increaseLevel() {
        warrior.setExperience(990, 9, Rank.PUSHOVER);
        warrior.battle(9);
        assertThat(warrior.experience()).isEqualTo(1000);
        assertThat(warrior.level()).isEqualTo(10);
    }

    @Test
    public void testMaxLevel() {
        warrior.setExperience(10000, 100, Rank.PUSHOVER);
        String message = warrior.battle(100);
        assertThat(warrior.experience()).isEqualTo(10000);
        assertThat(warrior.level()).isEqualTo(100);
        assertThat(message).isEqualTo("A good fight");
    }

    @Test
    public void testMessage() {
        warrior.setExperience(493, 5, Rank.PUSHOVER);
        String message = warrior.battle(6);
        assertThat(warrior.experience()).isEqualTo(513);
        assertThat(warrior.level()).isEqualTo(5);
        assertThat(message).isEqualTo("An intense fight");
    }

    @Test
    public void increaseRank() {
        warrior.setExperience(990, 9, Rank.PUSHOVER);
        warrior.battle(9);
        assertThat(warrior.rank()).isEqualTo(NOVICE.readable());
        assertThat(warrior.level()).isEqualTo(10);
    }

    @Test
    public void increaseRank_to_greatest() {
        warrior.setExperience(9990, 99, Rank.MASTER);
        warrior.battle(99);
        assertThat(warrior.rank()).isEqualTo(GREATEST.readable());
        assertThat(warrior.level()).isEqualTo(100);

        warrior.battle(100);
        assertThat(warrior.experience()).isEqualTo(10000); //unchanged
        assertThat(warrior.level()).isEqualTo(100);
        assertThat(warrior.rank()).isEqualTo(GREATEST.readable());
    }

    @Test
    public void sampleTest2() {

        Warrior bruce_lee = new Warrior();

        assertEquals(1, bruce_lee.level());
        assertEquals(100, bruce_lee.experience());
        assertEquals("Pushover", bruce_lee.rank());
        assertEquals(new ArrayList<String>(), bruce_lee.achievements());
        assertEquals("Defeated Chuck Norris", bruce_lee.training("Defeated Chuck Norris", 9000, 1));
        assertEquals(9100, bruce_lee.experience());
        assertEquals(91, bruce_lee.level());
        assertEquals("Master", bruce_lee.rank());
        assertEquals("A good fight", bruce_lee.battle(90));
        assertEquals(9105, bruce_lee.experience());
        assertEquals(Collections.singletonList("Defeated Chuck Norris"), bruce_lee.achievements());
    }
}