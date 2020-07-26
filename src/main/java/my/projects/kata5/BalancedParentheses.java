package my.projects.kata5;

import java.util.*;

public class BalancedParentheses {
    public static List<String> balancedParentheses (int n) {
        return calculate(n);
    }

    public static List<String> calculate(int n) {
        if (n == 0) {
            return Collections.singletonList("");
        } else if(n == 1) {
            return Collections.singletonList("()");
        }

        List<String> results = calculate(n - 1);

        Set<String> newResults = new HashSet<>();
        for (String item : results) {
            for (int i = 0; i < item.length(); i++) {
                String newResult = item.substring(0, i) + "()" + item.substring(i);
                newResults.add(newResult);
            }
        }
        return  new ArrayList<>(newResults);
    }
}
