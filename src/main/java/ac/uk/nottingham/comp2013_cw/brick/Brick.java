package ac.uk.nottingham.comp2013_cw.brick;

import ac.uk.nottingham.comp2013_cw.ball.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * @author  - Sahil Rai - modified
 * This class intiliases Brick generation
 */

abstract public class Brick {
    private static Random m_Rnd;

    private Color m_Border;
    private Color m_Inner;
    private int m_FullStrength;
    private int m_Strength;
    private boolean m_Broken;
    /** Initialise minimum crack */
    public static final int MIN_CRACK = 1;

    /** Initialise maximum crack */
    public static final int MAX_CRACK = 3;

    /** Initialise how big depth of crack will be */
    public static final int DEF_CRACK_DEPTH = 1;
    /** Initialise steps */
    public static final int DEF_STEPS = 35;

    /** Initialise up impact */
    public static final int UP_IMPACT = 100;
    /** Initialise down impact */
    public static final int DOWN_IMPACT = 200;
    /** Initialise left impact */
    public static final int LEFT_IMPACT = 300;
    /** Initialise right impact */
    public static final int RIGHT_IMPACT = 400;

    Shape brickFace;


    /**
     * This Constructor checks properties of brick
     * @param pos position of point brick
     * @param size size of brick
     * @param border border color of brick
     * @param inner border color of inner brick
     * @param strength strength of brick
     * */
    public Brick(Point pos, Dimension size, Color border, Color inner, int strength) {
        m_Rnd = new Random();
        m_Broken = false;
        brickFace = makeBrickFace(pos, size);
        this.m_Border = border;
        this.m_Inner = inner;
        this.m_FullStrength = this.m_Strength = strength;
    }

    /** Calls constructor */
    public Brick() {
        return;
    }

    /** This method states broken, used for brick
     * @return is broken
     * */
    public final boolean getIsBroken() {
        return m_Broken;
    }

    /** sets abstract method
     * @return gets brick
     * */
    public abstract Shape getBrick();

    /**
     * This method sets border colour of brick
     * @return gives border colour
     * */
    public Color getBorderColor() {
        return m_Border;
    }

    /**
     * This method sets inner colour of brick
     * @return gives inner colour
     * */
    public Color getInnerColor() {
        return m_Inner;
    }

    /**
     * Return rnd object
     * @return return object
     * */
    public static Random getM_Rnd(){
        return m_Rnd;
    }
    /**
     * This method regenerates brick back to full strength
     * */
    public void getRepair() {
        m_Broken = false;
        m_Strength = m_FullStrength;
    }

    /** This method sets strength on impact*/
    public void getImpact() {
        m_Strength--;
        m_Broken = (m_Strength == 0);
    }
    /** retrieves m_Rnd from this class to set it
     * @param m_Rnd retrieves random object
     * */
    public static void setRnd(Random m_Rnd) {
        Brick.m_Rnd = m_Rnd;
    }

    /**
     * retrieves m_border from this class to set it
     * @param m_Border retrieves border
     * */
    public void setBorder(Color m_Border) {
        this.m_Border = m_Border;
    }
    /**
     * retrieves m_Inner from this class to set it
     * @param m_Inner uses m_inner
     * */
    public void setInner(Color m_Inner) {
        this.m_Inner = m_Inner;
    }
    /**
     * @param m_Broken retrieves m_Broken from this class to set it
     * */
    public void setBroken(boolean m_Broken) {
        this.m_Broken = m_Broken;
    }

    /**
     * This method checks for broken or not on impact
     * @param point point at which crack will be set
     * @param dir direction towards point of where crack will be created
     * @return if brick is not already broken returns broken
     * */
    public boolean setImpact(Point2D point, int dir) {
        if (m_Broken)
            return false;
        getImpact();
        return m_Broken;
    }

    /**
     * This abstract generates brick
     * @param pos position of where brick will generate
     * @param size size of the brick
     * @return creates brick face
     * */
    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    /**
     * This method checks how much impact the ball makes to brick
     * @param b dictates where ball impacts leads to
     * @return output once impact
     * */
    public final int findImpact(Ball b) {
        if (m_Broken)
            return 0;
        int out = 0;
        if (brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if (brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if (brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if (brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }
}





