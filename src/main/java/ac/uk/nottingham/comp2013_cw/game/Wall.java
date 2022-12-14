package ac.uk.nottingham.comp2013_cw.game;

import ac.uk.nottingham.comp2013_cw.ball.Ball;
import ac.uk.nottingham.comp2013_cw.ball.RubberBall;
import ac.uk.nottingham.comp2013_cw.brick.*;
import javafx.scene.media.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

/**
 * @author - Sahil Rai - modified
 * <p>
 * This class is used to set the wall preface when generated including
 * everthying that is designed to be inside.
 */

public class Wall {
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int SCORE = 100;
    private static final int ADJUST_MAKEBALL_SPEEDX = 2;
    private static final int  BALL_RESET_SPEEDX =2;
    private static final int ADJUST_BRICKCNT = 2;

    private Brick[][] m_Levels;
    private final Point m_StartPoint;
    private final Random m_Rnd;
    private final Rectangle m_Area;

    private int m_Level;
    private int m_BallCount;
    private boolean m_BallLost;

    /** This method is the counter for the amount of levels*/
    public static final int LEVELS_COUNT = 5;
    /* Changed to public so it can allow packages to access */
    /**Calls brick object bricks*/
    public Brick[] bricks;
    /** Calls Ball object ball */
    public Ball ball;
    /** Calls paddle object player */
    public Paddle player;
    /** Calls class object score*/
    public Score score;
    /** Calls object getBallEnd*/
    public static int getBallEnd;
    /** Calls object getBallCount*/
    public static int getBallCount;
    /** Calls object getHasLevel*/
    public static boolean getHasLevel;
    /** Calls object m_BrickCount*/
    private static int m_BrickCount;

    /**
     * This method generates the wall
     * @param drawArea generates area
     * @param brickCount amount of brick in level
     * @param lineCount lines in level
     * @param brickDimensionRatio brick dimension ratio in level
     * @param ballPos ball position in level
     * */
    public Wall(Rectangle drawArea, int brickCount, int lineCount,
                double brickDimensionRatio, Point ballPos) {
        this.m_StartPoint = new Point(ballPos);
        m_Levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        m_Level = 0;
        m_BallCount = 3;
        m_BallLost = false;
        m_Rnd = new Random();
        makeBall(ballPos);
        int speedX, speedY;
        do {
            speedX = m_Rnd.nextInt(5) - ADJUST_MAKEBALL_SPEEDX;
        } while (speedX == 0);
        do {
            speedY = -m_Rnd.nextInt(3);
        } while (speedY == 0);

        ball.setSpeed(speedX, speedY);
        player = new Paddle((Point) ballPos.clone(), 150, 10, drawArea);
        m_Area = drawArea;
    }
    /**@return randoms object*/
    public Random getRnd() {
        return m_Rnd;
    }
    /**@return returns area */
    public Rectangle getArea() {
        return m_Area;
    }
    /** @return checks for lost ball*/
    public boolean getBallLost() {
        return m_BallLost;
    }
    
    /** @return  amount of brick */
    public int getBrickCount() {
        return m_BrickCount;
    }
    /** @return amount of balls */
    public int getBallCount() {
        return m_BallCount;
    }
    /** @return end ball which initialises to 0*/
    public boolean getBallEnd() {
        return m_BallCount == 0;
    }
    /**@return returns level*/
    public int getLevel() {
        return m_Level;
    }

    /** This method is used to proceed to next level*/
    public void getNextLevel() {
        bricks = m_Levels[m_Level++];
        this.m_BrickCount = bricks.length;
    }

    /** @return levels left over */
    public boolean getHasLevel() {
        return m_Level < m_Levels.length;
    }

    /** This reset ball count
     * @return sets ballcount back to original
     * */
    public int setBallCount() {
        return m_BallCount;
    }
    /**
     * This method sets ball speed for X
     * @param s uses it to use speed
     * */
    public void setBallXSpeed(int s) {
        ball.setXSpeed(s);
    }
    /**
     * This method sets ball speed for Y
     * @param s uses it to use speed
     * */
    public void setBallYSpeed(int s) {
        ball.setYSpeed(s);
    }
    /** @return this method returns when it is finished and brick count = 0 */
    public static boolean setDone() {
        return m_BrickCount == 0;
    }

    /** This method initialises movement of player and ball */
    public void Move() {
        player.move();
        ball.move();
    }


    /**
     * This method makes single type brick level
     * @param drawArea generates the area
     * @param brickCnt uses brick count
     * @param lineCnt uses line count
     * @param brickSizeRatio size ratio
     * @param type type of brick utilised
     * @return tmp chessboard level
     * */
    public Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt,
                        int lineCnt, double brickSizeRatio, int type) {
        /*
        *if brickCount is not divisible by line count,brickCount is adjusted to
        *the biggest multiple of lineCount smaller then brickCount
        * */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;
        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;



        brickCnt += lineCnt / ADJUST_BRICKCNT;
        Brick[] tmp = new Brick[brickCnt];
        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, type);
        }
        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new ClayBrick(p, brickSize);
        }
        return tmp;
    }

    /**
     * This method makes multiple brick type level
     * @param drawArea generates the area
     * @param brickCnt uses brick count
     * @param lineCnt uses line count
     * @param brickSizeRatio size ratio
     * @param typeA type of brick
     * @param typeB second type of brick
     * @return tmp chessboard level
     * */
    public Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt,
                int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        /*
        * if brickCount is not divisible by line count, brickCount is adjusted
        * to the biggest multiple of lineCount smaller then brickCount
        * */
        brickCnt -= brickCnt % lineCnt;
        int brickOnLine = brickCnt / lineCnt;
        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;
        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;
        Brick[] tmp = new Brick[brickCnt];
        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % ADJUST_BRICKCNT == 0) ? x : (x - (brickLen / ADJUST_BRICKCNT));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % ADJUST_BRICKCNT == 0 && i % ADJUST_BRICKCNT == 0)
                    || (line % ADJUST_BRICKCNT != 0
                    && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize,
                    typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += ADJUST_BRICKCNT * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / ADJUST_BRICKCNT);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    /**
     * creates ball
     * @param ballPos position of ball
     * */
    public void makeBall(Point2D ballPos) {
        ball = new RubberBall(ballPos);
    }

    /**
     * creates levels
     * @param drawArea generates the area making level
     * @param brickCount amount of bricks making level
     * @param lineCount amount of lines for making level
     * @param brickDimensionRatio size ratio for making level
     * @return tmp of making level
     * Changed mehtod to alter levels and new additional level
     * */
    public Brick[][] makeLevels(Rectangle drawArea, int brickCount,
                                int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount,
                brickDimensionRatio, CLAY);
        tmp[1] = makeChessboardLevel(drawArea, brickCount, lineCount,
                brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(drawArea, brickCount, lineCount,
                brickDimensionRatio, CEMENT, CEMENT);
        tmp[3] = makeChessboardLevel(drawArea, brickCount, lineCount,
                brickDimensionRatio, CEMENT, STEEL);
        tmp[4] = makeChessboardLevel(drawArea, brickCount, lineCount,
                brickDimensionRatio, STEEL, STEEL);

        return tmp;
    }

    /**
     * This method locates impact of ball and allows back to bounce back
     * @throws MalformedURLException If there is error
     * */
    public void findImpacts() throws MalformedURLException {
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*
            * for efficiency reverse is done into method impactWall because for
            * every brick program checks for horizontal and vertical impacts
            * */
            m_BrickCount--;
            /* Access Score for score count*/
            Score.scoreCount += SCORE;
            playSound();
        } else if (impactBorder()) {
            ball.reverseX();
        } else if (ball.getPosition().getY() < m_Area.getY()) {
            ball.reverseY();
        } else if (ball.getPosition().getY() > m_Area.getY() + m_Area.getHeight()) {
            m_BallCount--;
            m_BallLost = true;
        }
    }
    /**
     * This method is generates sound when brick breacks
     * @throws MalformedURLException If there is error
     * */
    public void playSound() throws MalformedURLException {
        String path = "src/main/resources/ac/uk/nottingham/comp2013_cw/music/break.mp3";
        Media media = new Media(new File(path).toURI().toURL().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    /**
     * This method returns on impact of wall from ball to brick
     * @return impact when hit up, down, right, and left
     *
     * */
    public boolean impactWall() {
        for (Brick b : bricks) {
            switch (b.findImpact(ball)) {
                //Vertical Impact
                case CrackBrick.UP_IMPACT -> {
                    ball.reverseY();
                    return b.setImpact(ball.down, CrackBrick.UP);
                }
                case CrackBrick.DOWN_IMPACT -> {
                    ball.reverseY();
                    return b.setImpact(ball.up, CrackBrick.DOWN);
                }

                //Horizontal Impact
                case CrackBrick.LEFT_IMPACT -> {
                    ball.reverseX();
                    return b.setImpact(ball.right, CrackBrick.RIGHT);
                }
                case CrackBrick.RIGHT_IMPACT -> {
                    ball.reverseX();
                    return b.setImpact(ball.left, CrackBrick.LEFT);
                }
            }
        }
        return false;
    }

    /**
     * This method is the impact within border
     * @return when ball gets hit at position returns bounce
     * */
    public boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < m_Area.getX()) || (p.getX() > (m_Area.getX()
                + m_Area.getWidth())));
    }

    /** This method resets ball to start point */
    public void ballReset() {
        player.moveTo(m_StartPoint);
        ball.moveTo(m_StartPoint);
        int speedX, speedY;
        do {
            speedX = m_Rnd.nextInt(5) - BALL_RESET_SPEEDX;
        } while (speedX == 0);
        do {
            speedY = -m_Rnd.nextInt(3);
        } while (speedY == 0);

        ball.setSpeed(speedX, speedY);
        m_BallLost = false;
    }

    /**
     * This method resets wall
     * */
    public void wallReset() {
        for (Brick b : bricks)
            b.getRepair();
        m_BrickCount = bricks.length;
        m_BallCount = 3;
    }

    /**
     * This method generates brick
     * @param point point of brick
     * @param size size of brick
     * @param type type of brick
     * @return outputs break
     * */
    public Brick makeBrick(Point point, Dimension size, int type) {
        return switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
    }
}
