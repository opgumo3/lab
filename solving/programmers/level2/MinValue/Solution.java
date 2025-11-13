package MinValue;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{1,3,5}, new int[]{2,4,3});
    }

    public int solution(int []A, int []B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int result = 0;

        for (int i = 0; i < A.length; i++) {
            result += B[B.length - i - 1] * A[i];
        }

        return result;
    }
}
