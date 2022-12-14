package ac.uk.nottingham.comp2013_cw.debug;

import ac.uk.nottingham.comp2013_cw.game.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author - Sahil Rai - modified
 * <p>
 * This class is panel which allows you to access admin features
 * i.e. skipping levels, changing ball speed and resetting balls.
 */

public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;
    /** defines skip level */
    private JButton m_SkipLevel;
    /** defines rest ball */
    private JButton m_ResetBalls;
    /** defines Ball X speed */
    private JSlider m_BallXSpeed;
    /** defines Ball Y speed */
    private JSlider m_BallYSpeed;
    /** defines wall */
    private Wall m_Wall;

    /**
     * This method calls in buttons for function and slider speed
     * @param wall calling in wall which is used for border
     * */
    public DebugPanel(Wall wall) {

        this.m_Wall = wall;

        initialize();

        m_SkipLevel = makeButton("Skip Level", e -> wall.getNextLevel());
        m_ResetBalls = makeButton("Reset Balls", e -> wall.setBallCount());

        m_BallXSpeed = makeSlider(-4, 4,
                e -> wall.setBallXSpeed(m_BallXSpeed.getValue()));
        m_BallYSpeed = makeSlider(-4, 4,
                e -> wall.setBallYSpeed(m_BallYSpeed.getValue()));

        this.add(m_SkipLevel);
        this.add(m_ResetBalls);

        this.add(m_BallXSpeed);
        this.add(m_BallYSpeed);

    }

    /**
     * this method sets values of ball speed
     * @param x speed of x
     * @param y speed of y
     * */
    public void setValues(int x, int y) {
        m_BallXSpeed.setValue(x);
        m_BallYSpeed.setValue(y);
    }

    /**
     * This method initialises background and grid layout
     * */
    public void initialize() {
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2, 2));
    }

    /** This method creates buttons
     * @param title button label
     * @param e listener
     * @return outputs the button
     * */
    public JButton makeButton(String title, ActionListener e) {
        JButton out = new JButton(title);
        out.addActionListener(e);
        return out;
    }

    /** This method creates slider
     * @param min limit
     * @param max limit
     * @param e listener
     * @return outputs slider
     * */
    public JSlider makeSlider(int min, int max, ChangeListener e) {
        JSlider out = new JSlider(min, max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

}
