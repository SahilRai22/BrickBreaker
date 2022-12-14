package ac.uk.nottingham.comp2013_cw.game;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @BeforeAll
    public static void wallTestSetUp() {
        System.out.println("Tests for Score class ... ");
    }

    @Test
    public void shouldResetScore(){
        assertEquals(0, Score.scoreCount);
    }

    @Test
    public void defaultNameScore(){
        assertEquals("Null:0", Score.highScore);
    }

    @Test
    public void shouldCheckScore(){
        int scoreCount = 10000000;
        String highScore = "100";
        assertTrue(scoreCount > Integer.parseInt(Score.highScore.split(
                ":")[1]));
    }

    @AfterAll
    public static void scoreTestEnd(){
        System.out.println("Tests Completed");
    }

}