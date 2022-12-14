package ac.uk.nottingham.comp2013_cw.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import java.awt.Point;

/**
 * @author Sahil Rai
 * This class is extended from Brick class is repsonsible for
 * Cracks which result in breaking the brick.
 * */
public abstract class CrackBrick extends Brick{
    private final Brick m_Brick;
    private final GeneralPath m_Crack;
    private final int m_CrackDepth;
    private final int m_Steps;
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    /** initialise left in crack */
    public static final int LEFT = 10;
    /** initialise right in crack */
    public static final int RIGHT = 20;
    /** initialise up in crack */
    public static final int UP = 30;
    /** initialise down in crack */
    public static final int DOWN = 40;
    /** initialise vertical in crack */
    public static final int VERTICAL = 100;
    /** initialise horizontal in crack */
    public static final int HORIZONTAL = 200;

    /**
     * This constructor method how much depth and steps the crack has
     * @param crackDepth amount of crack depth
     * @param steps steps in path
     * @param brick retrieves brick constructor
     * */
    public CrackBrick(Brick brick, int crackDepth, int steps) {
        super();
        // use auto generated solution to fix : remove comment after
        this.m_Brick = brick;
        m_Crack = new GeneralPath();

        this.m_CrackDepth = crackDepth;
        this.m_Steps = steps;
    }
    /**
     * @return gets step objects
     * */
    public int getSteps() {
        return m_Steps;
    }
    /**
     * @return crack for genral path
     * */
    public GeneralPath getCrack() {
        return m_Crack;
    }

    /**
     * @return general path that results into crack
     * */
    public GeneralPath draw() {
        return m_Crack;
    }

    /**This method resets the crack */
    public void reset() {
        m_Crack.reset();
    }

    /**
     * This method is used to create cracks
     * @param point where the crack will be
     * @param direction directs to where point of crack will be
     * */
    protected void makeCrack(Point2D point, int direction) {
        Rectangle bounds = m_Brick.brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();


        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y
                        + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y
                        + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;
        }
    }

    /**
     * This method is used to set starting and end points of crack
     * @param start beginning point of crack
     * @param end final point of crack
     * */
    protected void makeCrack(Point start, Point end) {
        GeneralPath path = new GeneralPath();
        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) m_Steps;
        double h = (end.y - start.y) / (double) m_Steps;
        int bound = m_CrackDepth;
        int jump = bound * 5;
        double x, y;

        for (int i = 1; i < m_Steps; i++) {
            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);
            if (inMiddle(i, CRACK_SECTIONS, m_Steps))
                y += jumps(jump, JUMP_PROBABILITY);
            path.lineTo(x, y);
        }

        path.lineTo(end.x, end.y);
        m_Crack.append(path, true);
    }
    /**
     * This method is used to generate random in bounds
     * @param bound which bound it will go to
     * @return generates random in bound
     * */
    public int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return getM_Rnd().nextInt(n) - bound;
    }

    /**
     * this method is used when ball hit middles
     * @param i to check if is in middle
     * @param steps steps utilised
     * @param divisions amount of divisions
     * @return middle between up and low limit
     * */
    public boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    /**
     * this method is used to show jump from brick
     * @param bound used to return random bounds
     * @param probability used to compare to random probability
     * @return gets random bounds
     * */
    public int jumps(int bound, double probability) {

        if (getM_Rnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    /**
     * this method generates random points
     * @param from where random point starts
     * @param to where random point gets to
     * @param direction the way it will path to
     * @return random out from random point
     * */
    public Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL -> {
                pos = getM_Rnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
            }
            case VERTICAL -> {
                pos = getM_Rnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
            }
        }
        return out;
    }
}