package ac.uk.nottingham.comp2013_cw.ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;


/**
 * @author - Sahil Rai - modified
 * <p>
 * This class extends to ball and sets a ball with all constants
 */
public class RubberBall extends Ball {
    /* Removed string NAME as it is never accessed */
    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255,219,88);
    private static final Color DEF_BORDER_COLOR =DEF_INNER_COLOR.darker().darker();

    /**
     * This method creates a ball with its featueres
     * @param center center of ball
     * */
    public RubberBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_RADIUS, DEF_INNER_COLOR, DEF_BORDER_COLOR);
    }


    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA / ADJUST_RADIUS_A);
        double y = center.getY() - (radiusB / ADJUST_RADIUS_B);

        return new Ellipse2D.Double(x, y, radiusA, radiusB);
    }
}
