package ac.uk.nottingham.comp2013_cw.brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *
 * @author  - Sahil Rai - modified
 * This class extends brick and is properties of Steel brick
 *
 */

public class SteelBrick extends Brick {

    /* Removed string NAME as it is never accessed */
    //private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private final Random m_Rnd;
    private final Shape m_BrickFace;

    /**
     * This constructor gets properties of Steel Brick
     * @param point where steel brick is
     * @param size how big steel brick is
     * */
    public SteelBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, STEEL_STRENGTH);
        m_Rnd = new Random();
        m_BrickFace = super.brickFace;
    }
    public Shape getBrick() {
        return m_BrickFace;
    }

    /**
     * @return retrieves random object
     * */
    public Random getRnd() {
        return m_Rnd;
    }

    public boolean setImpact(Point2D point, int dir) {
        if (super.getIsBroken())
            return false;
        getImpact();
        return super.getIsBroken();
    }

    public void getImpact() {
        if (m_Rnd.nextDouble() < STEEL_PROBABILITY) {
            super.getImpact();
        }
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

}
