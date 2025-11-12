package RightBrackets;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        assert solution.solution("()()");
        assert solution.solution("(())()");
        assert !solution.solution(")()(");
    }

    boolean solution(String s) {
        int stackCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stackCount++;
            } else {
                if (--stackCount < 0) {
                    return false;
                }
            }
        }

        return stackCount == 0;
    }
}
