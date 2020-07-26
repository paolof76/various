package my.projects.kata5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BalancedParenthesesTest {

    @Test
    public void test_0() {
        assertThat(BalancedParentheses.balancedParentheses(0))
        .containsExactlyInAnyOrder("");
    }

    @Test
    public void test_1() {
        assertThat(BalancedParentheses.balancedParentheses(1))
                .isEmpty();
    }

    @Test
    public void test_2() {
        assertThat(BalancedParentheses.balancedParentheses(2))
                .containsExactlyInAnyOrder("()()", "(())");
    }

    @Test
    public void test_3() {
        assertThat(BalancedParentheses.balancedParentheses(3))
                .containsExactlyInAnyOrder("()()()","(())()","()(())","((()))","(()())");
    }
}