package my.projects.kata3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KataTest {
    Kata kata;

    @BeforeEach
    public void init() {
        kata = new Kata();
    }

    @Test
    void workOnStrings() {
        assertThat(kata.workOnStrings("abc","cde")).isEqualTo("abCCde");
        assertThat(kata.workOnStrings("abcdeFgtrzw", "defgGgfhjkwqe")).isEqualTo("abcDeFGtrzWDEFGgGFhjkWqE");
        assertThat(kata.workOnStrings("abcdeFg", "defgG")).isEqualTo("abcDEfgDEFGg");
        assertThat(kata.workOnStrings("abab", "bababa")).isEqualTo("ABABbababa");
    }

    @Test
    void workOnStrings_2() {
        assertThat(kata.workOnStrings("abcdeFgtrzw", "defgGgfhjkwqe")).isEqualTo("abcDeFGtrzWDEFGgGFhjkWqE");
    }
}