package my.projects.katas.kata1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitCountingTest {

    private BitCounting bitCounting;

    @BeforeEach
    public void init() {
        this.bitCounting = new BitCountingStandard();
        //this.bitCounting = new BitCountingCustom();
    }

    @Test
    public void countBits_413() {
        assertEquals(6, bitCounting.countBits(413));
        System.out.println(bitCounting.getBinaryRepresentation());
    }

    @Test
    public void countBits_3() {
        assertEquals(2, bitCounting.countBits(3));
        System.out.println(bitCounting.getBinaryRepresentation());
    }

    @Test
    public void countBits_16() {
        assertEquals(1, bitCounting.countBits(16));
        System.out.println(bitCounting.getBinaryRepresentation());
    }
}