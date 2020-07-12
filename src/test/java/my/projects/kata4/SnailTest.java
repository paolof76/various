package my.projects.kata4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SnailTest {

    @Test
    void snail_empty() {
        int[][] array = new int[][] {
                {}
        };
        int[] sequence = Snail.snail(array);

        assertThat(sequence).isEmpty();
    }

    @Test
    void snail_1() {
        int[][] array = new int[][] {
                {1}
        };
        int[] sequence = Snail.snail(array);

        assertThat(sequence).containsExactly(1);
    }

    @Test
    void snail_2() {
        int[][] array = new int[][] {
                {1,2},
                {3,4}
        };
        int[] sequence = Snail.snail(array);

        assertThat(sequence).containsExactly(1,2,4,3);
    }

    @Test
    void snail_3() {
        int[][] array = new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9},
        };
        int[] sequence = Snail.snail(array);

        assertThat(sequence).containsExactly(1,2,3,6,9,8,7,4,5);
    }
}