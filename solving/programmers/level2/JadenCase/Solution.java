package JadenCase;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("3people unFollowed me");
        solution.solution("gG");
    }

    public String solution(String s) {
        char[] charArray = s.toCharArray();
        boolean isToUpperCase = true;

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                isToUpperCase = true;
            } else if (isToUpperCase) {
                charArray[i] = Character.toUpperCase(charArray[i]);
                isToUpperCase = false;
            } else {
                charArray[i] = Character.toLowerCase(charArray[i]);
            }
        }

        return String.valueOf(charArray);
    }
}
