package ac.uk.nottingham.comp2013_cw.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * @author  - Sahil Rai - modified
 *
 * This class extends class Brick and is responsible for cement brick
 */

public class CementBrick extends Brick {

    /* Removed string NAME as it is never accessed */
    //private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private final CrackBrick m_Crack;
    private Shape m_BrickFace;

    /**
     * This constructor method is responsible for crack and steps of cement brick
     * @param size how big the cement brick is
     * @param point which point of cement brick it is
     * */
    public CementBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, CEMENT_STRENGTH);
        m_Crack = new CrackBrick(this,DEF_CRACK_DEPTH, DEF_STEPS) {
            @Override
            protected Shape makeBrickFace(Point pos, Dimension size) {
                return null;}

            @Override
            public Shape getBrick() {
                return null;
            }
        };
        m_BrickFace = super.brickFace;
    }
    /**
     * @return gives back crack
     * */
    public CrackBrick getCrack() {
        return m_Crack;
    }
    /**
     * @return gives back brickFace
     * */
    public Shape getBrick() {
        return m_BrickFace;
    }
    /**
     * sets values back to normal using reset
     * */
    public void getRepair() {
        super.getRepair();
        m_Crack.reset();
        m_BrickFace = super.brickFace;
    }

    private void updateBrick() {
        if (!super.getIsBroken()) {
            GeneralPath gp = m_Crack.draw();
            gp.append(super.brickFace, false);
            m_BrickFace = gp;
        }
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if (super.getIsBroken())
            return false;
        super.getImpact();
        if (!super.getIsBroken()) {
            m_Crack.makeCrack(point, dir);
            updateBrick();
            return false;
        }
        return true;
    }
}
