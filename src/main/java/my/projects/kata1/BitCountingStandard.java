package my.projects.kata1;

public class BitCountingStandard implements BitCounting {
    private int n;

    @Override
    public int countBits(int n) {
        this.n = n;
        return Integer.bitCount(n);
    }

    @Override
    public String getBinaryRepresentation() {
        return Integer.toBinaryString(n);
    }
}
