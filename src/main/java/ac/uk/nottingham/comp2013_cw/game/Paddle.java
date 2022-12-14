package ac.uk.nottingham.comp2013_cw.game;

import ac.uk.nottingham.comp2013_cw.ball.Ball;

import java.awt.*;

/**
 * @author - Sahil Rai - modified
 * <p>
 * This class has responsibilities of the paddle
 */

public class Paddle {
    private static final int HALF = 2;
    private final Rectangle paddleFace;
    private final Point ballPoint;
    private final int min;
    private final int max;
    private int moveAmount;

    /** This sets the border colour of the paddle */
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    /** This sets the inner colour of the paddle */
    public static final Color INNER_COLOR = Color.GREEN;
    /** This initialises how much space the paddle can move */
    public static final int DEF_MOVE_AMOUNT = 5;

    /**
     * This method customises the paddle
     * @param width width of paddle
     * @param height height of paddle
     * @param container minimum container of paddle
     * @param ballPoint where paddle will get impact on
     * */
    public Paddle(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        paddleFace = makeRectangle(width, height);
        min = container.x + (width / HALF);
        max = min + container.width - width;
    }

    /**
     * @return gets min
     * */
    public int getMin() {
        return min;
    }
    /**
     * @return gets max
     * */
    public int getMax() {
        return max;
    }

    /** @return gets paddle face */
    public Shape getPaddleFace() {
        return paddleFace;
    }

    /** This method is inverse of move right */
    public void moveLeft() {
        moveAmount = -DEF_MOVE_AMOUNT;
    }
    /** This method is to move paddle to right */
    public void movRight() {
        moveAmount = DEF_MOVE_AMOUNT;
    }
    /** This method causes a stop to the paddle*/
    public void stop() {
        moveAmount = 0;
    }

    /** This directs where the ball moves to
     * @param p  location of where the ball will move to
     * */
    public void moveTo(Point p) {
        ballPoint.setLocation(p);
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth() / HALF,
                ballPoint.y);
    }


    /** This method allows movement of the paddle and location of it*/
    public void move() {
        double x = ballPoint.getX() + moveAmount;
        if (x < min || x > max)
            return;
        ballPoint.setLocation(x, ballPoint.getY());
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth() / HALF,
                ballPoint.y);
    }

    /**
     * This method gets impact of ball onto paddle
     * @param b where ball gets impacted
     * @return impact of ball from paddle
     * */
    public boolean impact(Ball b) {
        return paddleFace.contains(b.getPosition()) && paddleFace.contains(b.down);
    }

    /**
     * This creates the shape of the paddle
     * @param width taking in measurements
     * @param height taking in measurements
     * @return a shape which can be used for paddle face
     * */
    public Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (ballPoint.getX() - (width / HALF)),
                (int) ballPoint.getY());
        return new Rectangle(p, new Dimension(width, height));
    }

}
