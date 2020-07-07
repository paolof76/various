package my.projects.kata1;

public class BitCountingCustom implements BitCounting {

    private String binaryRepresentation;

    @Override
    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    @Override
    public int countBits(int n){
        String temporaryBinaryRepresentation = "";

        int count = 0;
        int rest = n;
        while(rest > 0) {
            boolean isOne = rest % 2 != 0;
            temporaryBinaryRepresentation = isOne ? "1" + temporaryBinaryRepresentation : "0" + temporaryBinaryRepresentation;
            if(isOne)
                count++;
            rest = rest / 2;
        }
        return count;


    }
}