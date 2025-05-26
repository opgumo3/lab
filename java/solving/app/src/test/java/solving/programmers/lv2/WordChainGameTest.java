package app.src.test.java.solving.programmers.lv2;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import app.src.main.java.solving.programmers.lv2.WordChainGame;

public class WordChainGameTest {

    WordChainGame wordChainGame = new WordChainGame();

    @Test
    public void testSolution() {
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] expected = {3, 3};

        int[] result = wordChainGame.solution(n, words);
        assertArrayEquals(expected, result);
    }
}
