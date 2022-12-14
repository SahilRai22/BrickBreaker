package ac.uk.nottingham.comp2013_cw.brick;


import java.awt.*;
import java.awt.Point;

/**
 *
 * @author  - Sahil Rai - modified
 * This class extends onto Brick and is reponsible of clay bricks
 *
 */
public class ClayBrick extends Brick {
    /*String not used not needed*/
    //private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34,
            34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * This constructor method gets clay and its properties
     * @param point gets point of clay
     * @param size gets size of clay
     * */
    public ClayBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }
}
