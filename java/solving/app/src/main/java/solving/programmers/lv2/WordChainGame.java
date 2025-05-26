package app.src.main.java.solving.programmers.lv2;

public class WordChainGame {

    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0}; // 번호, 차레

        int index = getIndex(words);

        if (index == 0) {
            return answer;
        }

        answer[0] = (index + 1) % n == 0 ? n : (index + 1) % n; // 나머지 
        answer[1] = (index + 1) / n; // 몫

        return answer;
    }

    // 몇 번째인지 반환
    private int getIndex(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i+1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    return j;     
                }
            }
        }

        return 0;
    }
}