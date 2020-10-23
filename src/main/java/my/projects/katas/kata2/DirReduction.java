package my.projects.katas.kata2;

import java.util.Stack;

public class DirReduction {
    public String[] dirReduc(String[] arr) {
        Stack<String> result = new Stack<>();
        for (String current : arr) {
            if (!result.empty() && opposite(current, result.peek())) {
                result.pop();
                continue;
            }
            result.push(current);
        }
        return result.toArray(new String[0]);
    }

    private boolean opposite(String current, String previous) {
        return (current.equals("North") && previous.equals("South")) ||
                (current.equals("South") && previous.equals("North")) ||
                (current.equals("East") && previous.equals("West")) ||
                (current.equals("West") && previous.equals("East"));
    }
}
