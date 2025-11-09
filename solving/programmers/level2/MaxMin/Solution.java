package MaxMin;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.solution("1 2 3 4 -4 -2 -3 -1 0 5 -3 5");
        solution.solution("-1 -2 -3 -4");
        solution.solution("1 -1");
    }

    public String solution(String s) {
        // 1. compareTo (사전 순서로 4, 3, 2, 1, -4, -3)
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String string : s.split(" ")) {
            int parseInt = Integer.parseInt(string);
            if (parseInt < min) min = parseInt;
            if (parseInt > max) max = parseInt;
        }

        return min + " " + max;
    }

    public String solutionByIntArray(String s) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int[] intArray = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i : intArray) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }

        return min + " " + max;
    }
}