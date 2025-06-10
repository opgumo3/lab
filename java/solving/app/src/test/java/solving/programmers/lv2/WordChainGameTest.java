package solving.programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import app.src.main.java.solving.programmers.lv2.WordChainGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WordChainGameTest {

    WordChainGame wordChainGame;

    @BeforeEach
	public void setUp() {
		wordChainGame = new WordChainGame();
	}

    @Test
    public void testSolution1() {
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] expected = {3, 3};

        int[] result = wordChainGame.solution(n, words);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSolution2() {
        int n = 5;
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        int[] expected = {0, 0};

        int[] result = wordChainGame.solution(n, words);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSolution3() {
        int n = 2;
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        int[] expected = {1, 3};

        int[] result = wordChainGame.solution(n, words);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSolution4() {
        int n = 1;
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        int[] expected = {1, 5};

        int[] result = wordChainGame.solution(n, words);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSolution5() {
        int n = 4;
        String[] words = {"hello", "one", "even", "never", "rrr", "rrr", "d"};
        int[] expected = {2, 2};

        int[] result = wordChainGame.solution(n, words);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSolution6() {
        int n = 3;
        String[] words = {"a", "b"};
        int[] expected = {2, 1};

        int[] result = wordChainGame.solution(n, words);
        assertArrayEquals(expected, result);
    }
}
