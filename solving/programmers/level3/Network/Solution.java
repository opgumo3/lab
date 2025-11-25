package Network;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}});
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                recurse(i, computers, visited);
            }
        }

        return answer;
    }

    private void recurse(int n, int[][] computers, boolean[] visited) {
        visited[n] = true;

        // search
        for (int i = 0; i < computers[n].length; i++) {
            if (computers[n][i] == 1 && !visited[i]) {
                recurse(i, computers, visited);
            }
        }
    }

    void printArray(boolean[] arr) {
        for (boolean b : arr) {
            System.out.print(b + " ");
        }
        System.out.println();
    }
}