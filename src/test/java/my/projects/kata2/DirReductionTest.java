package my.projects.kata2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirReductionTest {

    private DirReduction dirReduction;

    @BeforeEach
    public void init() {
        dirReduction = new DirReduction();
    }

    @Test
    void dirReduc_empty() {
        String[] expected = new String[0];
        String[] result = dirReduction.dirReduc(expected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void dirReduc() {
        String[] northSouth = new String[] {"North", "South"};
        String[] expected = new String[0];
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void dirReduc_2() {
        String[] northSouth = new String[] {"South", "North"};
        String[] expected = new String[0];
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void dirReduc_3() {
        String[] northSouth = new String[] {"East", "West"};
        String[] expected = new String[0];
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void dirReduc_4() {
        String[] northSouth = new String[] {"West", "East"};
        String[] expected = new String[0];
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void dirReduc_not_trivial() {
        String[] northSouth = new String[] {"North", "East"};
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(northSouth);
    }

    @Test
    void dirReduc_not_trivial_2() {
        String[] northSouth = new String[] {"North", "North"};
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(northSouth);
    }

    @Test
    void dirReduc_not_trivial_3() {
        String[] northSouth = new String[] {"North", "East", "West"};
        String[] expected = new String[] {"North"};
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void dirReduc_not_trivial_4() {
        String[] northSouth = new String[] {"North", "East", "West", "North", "East", "South", "West", "East", "West"};
        String[] expected = new String[] {"North", "North", "East", "South", "West"};
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void dirReduc_not_trivial_5() {
        String[] northSouth = new String[] {"North", "South", "South", "East", "West", "North", "West"};
        String[] expected = new String[] {"West"};
        String[] result = dirReduction.dirReduc(northSouth);

        assertThat(result).isEqualTo(expected);
    }
}