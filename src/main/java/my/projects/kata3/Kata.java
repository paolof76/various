package my.projects.kata3;

public class Kata {
    public String workOnStrings(String first, String second) {
        char[] firstArr = first.toCharArray();
        char[] secondArr = second.toCharArray();

        changeCases(firstArr, secondArr);
        changeCases(secondArr, firstArr);

        return new String(firstArr) + new String(secondArr);
    }

    private void changeCases(char[] firstArr, char[] secondArr) {
        for (char f : firstArr) {
            String cString = Character.toString(f);
            for (int i = 0; i < secondArr.length; i++) {
                String s = Character.toString(secondArr[i]);
                if (s.equalsIgnoreCase(cString)) {
                    if (Character.isLowerCase(s.charAt(0)))
                        s = s.toUpperCase();
                    else s = s.toLowerCase();
                    secondArr[i] = s.charAt(0);
                }
            }
        }
    }
}
