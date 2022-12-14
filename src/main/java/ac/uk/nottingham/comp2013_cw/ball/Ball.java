package ac.uk.nottingham.comp2013_cw.ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * @author - Sahil Rai - modified
 * <p>
 * This class handles responsibilities of a ball
 */

abstract public class Ball {
    /*Constants to remove magic numbers*/
    private final int ADJUST_SET_FRAME_W = 2;
    private final int ADJUST_SET_FRAME_Y = 2;
    private final int ADJUST_SET_FRAME_H = 2;
    private final int ADJUST_SETPOINTS_WIDTH = 2;
    private final int ADJUST_SETPOINTS_HEIGHT = 2;

    private int m_SpeedX;
    private int m_SpeedY;

    private final Color m_Border;
    private final Color m_Inner;
    private final Point2D m_Center;

    private Shape ballFace;
    /* Changed to public so it can allow packages to access */
    /** Point2D constant up */
    public Point2D up;
    /** Point2D constant down*/
    public Point2D down;
    /** Point2D constant left*/
    public Point2D left;
    /** Point2D constant right*/
    public Point2D right;

    /*Change magic number 2, to a constant where it's use is to divide a
    variable*/
    /**
     * constant to adjust radius
     * */
    public static final int ADJUST_RADIUS_A = 2;
    public static final int ADJUST_RADIUS_B= 2;

    /**
     * @param center center position in ball
     * @param radiusA radius of left and right section of ball
     * @param radiusB radius section of up and down section of ball
     * @param inner colour of inner bit of ball
     * @param border colour of border of ball
     * */

    public Ball(Point2D center, int radiusA, int radiusB, Color inner,
                Color border) {
        this.m_Center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),
                center.getY() - (radiusB / ADJUST_RADIUS_B));
        down.setLocation(center.getX(),
                center.getY() + (radiusB / ADJUST_RADIUS_B));

        left.setLocation(center.getX() - (radiusA / ADJUST_RADIUS_A),
                center.getY());
        right.setLocation(center.getX() + (radiusA / ADJUST_RADIUS_A),
                center.getY());


        ballFace = makeBall(center, radiusA, radiusB);
        this.m_Border = border;
        this.m_Inner = inner;
        m_SpeedX = 0;
        m_SpeedY = 0;
    }


    /**
     * This method returns the border colour
     * @return Border colour of ball
     * */
    public Color getBorderColor() {
        return m_Border;
    }

    /**
     * @return inner colour of ball
     * */
    public Color getInnerColor() {
        return m_Inner;
    }
    /**
     * @return position of ball
     * */
    public Point2D getPosition() {
        return m_Center;
    }

    /**
     * @return face of ball
     * */
    public Shape getBallFace() {
        return ballFace;
    }

    /**
     * @return speed of ball moving at X direction
     * */
    public int getSpeedX() {
        return m_SpeedX;
    }
    /**
     * @return speed of ball moving at Y direction
     * */
    public int getSpeedY() {
        return m_SpeedY;
    }

    /**
     * This method is used to have a speed constant
     * @param x speed at x direction
     * @param y speed at y direction
     * */
    public void setSpeed(int x, int y) {
        m_SpeedX = x;
        m_SpeedY = y;
    }
    /**
     * This method sets the speed constant s into SpeedX
     * @param s speed of speedX
     * */
    public void setXSpeed(int s) {
        m_SpeedX = s;
    }

    /**
     * This method sets the speed constant s into SpeedY
     * @param s speed of speedY
     * */
    public void setYSpeed(int s) {
        m_SpeedY = s;
    }


    /**
     * This method sets points using width and height
     * @param height which height measure is used to set location
     * @param width which width measure is used to set location
     * */
    public void setPoints(double width, double height) {
        up.setLocation(m_Center.getX(), m_Center.getY() - (height /
                ADJUST_SETPOINTS_HEIGHT));
        down.setLocation(m_Center.getX(), m_Center.getY() + (height /
                ADJUST_SETPOINTS_HEIGHT));

        left.setLocation(m_Center.getX() - (width / ADJUST_SETPOINTS_WIDTH),
                m_Center.getY());
        right.setLocation(m_Center.getX() + (width / ADJUST_SETPOINTS_WIDTH),
                m_Center.getY());
    }

    /**
     * This class generates the ball
     * @param center takes in variable from method Ball
     * @param radiusA takes in variable from method Ball
     * @param radiusB takes in variable from method Ball
     * @return returns a ball that is made
     * */
    protected abstract Shape makeBall(Point2D center, int radiusA, int radiusB);

    /**
     * this method is controls movement of ball
     * */
    public void move() {
        RectangularShape tmp = (RectangularShape) ballFace;
        m_Center.setLocation((m_Center.getX()+m_SpeedX),(m_Center.getY() +
                m_SpeedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((m_Center.getX() - (w / ADJUST_SET_FRAME_W)), (m_Center.getY() -
                (h / ADJUST_SET_FRAME_H)), w, h);
        setPoints(w, h);
        ballFace = tmp;
    }

    /**
     * This method directs movement to a specific point
     * @param p points to where location is set
     * */
    public void moveTo(Point p) {
        m_Center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();


        tmp.setFrame((m_Center.getX() - (w/ADJUST_SET_FRAME_W)),
                (m_Center.getY() - (h/ADJUST_SET_FRAME_Y)),
                w, h);
        ballFace = tmp;
    }

    /**
     * This method reverses speed in X direction
     * */
    public void reverseX() {
        m_SpeedX *= -1;
    }

    /**
     * This method reverses speed in Y direction
     * */
    public void reverseY() {
        m_SpeedY *= -1;
    }
}
