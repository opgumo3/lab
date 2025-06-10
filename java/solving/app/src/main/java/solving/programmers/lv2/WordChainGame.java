package app.src.main.java.solving.programmers.lv2;

public class WordChainGame {

    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0}; // 번호, 차레

        int index = getFailedIndex(words); 

        if (index == 0) {
            return answer;
        }

        answer[0] = index % n + 1;
        answer[1] = (int) Math.ceil((index + 1) / (double)n);

        return answer;
    }

    // 몇 번째인지 반환
    private int getFailedIndex(String[] words) {
        for (int i = 1; i < words.length; i++) {
            int currentIndex = i;
            int beforeIndex = i - 1;
            
            String currentWord = words[currentIndex];
            String beforeWord = words[beforeIndex];

            if (!isStartWithEndOfStartWord(beforeWord, currentWord)) {
                return currentIndex;
            }

            for (int j = 0; j < currentIndex; j++) {
                if (currentWord.equals(words[j])) {
                    return currentIndex;
                }
            }
        }

        return 0;
    }

    private boolean isStartWithEndOfStartWord(String firstWord, String secodWord) {
        char endOfFirstWord = firstWord.charAt(firstWord.length() -1);
        char firstOfSecondWord = secodWord.charAt(0);

        return endOfFirstWord == firstOfSecondWord;
    }
}