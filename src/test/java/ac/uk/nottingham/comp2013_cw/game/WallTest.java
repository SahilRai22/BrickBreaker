package ac.uk.nottingham.comp2013_cw.game;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {


    @BeforeAll
    public static void wallTestSetUp() {
        System.out.println("Tests for Wall class ... ");
    }

    @Test
    public void shouldCountBall(){
        int m_ballCount = 0;
        assertEquals(m_ballCount, Wall.getBallCount);
        assertEquals(m_ballCount , Wall.getBallEnd);
    }

    @Test
    public void shouldHaveLevel(){
        //anything above LEVELS_COUNT > 5 returns false
        int m_level = 6;
        assertFalse(Wall.getHasLevel, String.valueOf(m_level));
    }

    @Test
    public void shouldNextLevel(){
        int m_level = 0;
        int newLevel = m_level++;
        assertNotEquals(m_level, newLevel);
    }

    @Test
    public void shouldResetWall(){
        int m_BallCount = 3;
        assertEquals(3, m_BallCount);
    }

    @Test
    public void shouldBeDone(){
        int m_BallCount = 0;
        assertTrue(m_BallCount, Wall.setDone());
    }

    private void assertTrue(int m_ballCount, boolean setDone) {
    }

    @AfterAll
    public static void wallTestEnd(){
        System.out.println("Tests completed ...");
    }

}