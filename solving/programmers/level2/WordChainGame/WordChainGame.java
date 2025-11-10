package WordChainGame;

import java.util.HashSet;
import java.util.Set;

// 영어 끝말 잇기
public class WordChainGame {

    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0}; // 번호, 차레

        int index = getFailedIndex(words); 

        if (index == 0) {
            return answer;
        }

        answer[0] = index % n + 1;
        answer[1] = index / n + 1;

        return answer;
    }

    private int getFailedIndex(String[] words) {
        Set<String> wordSet = new HashSet<>();
        wordSet.add(words[0]);

        for (int i = 0; i < words.length - 1; i++) {
            int currentIndex = i;
            int nextIndex = i + 1;
            
            String currentWord = words[currentIndex];
            String nextWord = words[nextIndex];

            if (!isStartWithEndOfStartWord(currentWord, nextWord) || !wordSet.add(nextWord)) {
                return nextIndex;
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