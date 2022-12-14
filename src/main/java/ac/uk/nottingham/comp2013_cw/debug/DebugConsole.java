package ac.uk.nottingham.comp2013_cw.debug;

import ac.uk.nottingham.comp2013_cw.game.GameBoard;
import ac.uk.nottingham.comp2013_cw.game.Wall;
import ac.uk.nottingham.comp2013_cw.ball.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author - Sahil Rai - modified
 * This bug
 */

public class DebugConsole extends JDialog implements WindowListener {
    private final int ADJUST_WIDTH = 2;
    private final int ADJUST_HEIGHT = 2;
    private static final String TITLE = "Debug Console";
    /** defines owner */
    private JFrame m_Owner;
    /** defines debug panel */
    private DebugPanel m_DebugPanel;
    /** defines game board*/
    private GameBoard m_GameBoard;
    /** defines wall */
    private Wall m_Wall;
    /**
     * This constructor method initialises debug console
     * @param owner initialise owner
     * @param wall initialise wall and set border layout to center
     * @param gameBoard initialise game board
     * */
    public DebugConsole(JFrame owner, Wall wall, GameBoard gameBoard) {
        this.m_Wall = wall;
        this.m_Owner = owner;
        this.m_GameBoard = gameBoard;
        initialize();
        m_DebugPanel = new DebugPanel(wall);
        this.add(m_DebugPanel, BorderLayout.CENTER);
        this.pack();
    }


    /** This method is used to get owner location*/

    public void setLocation() {
        int x = ((m_Owner.getWidth() - this.getWidth()) / ADJUST_WIDTH) + m_Owner.getX();
        int y = ((m_Owner.getHeight() - this.getHeight()) / ADJUST_HEIGHT) + m_Owner.getY();
        this.setLocation(x, y);
    }
    /**
     * set owner object to variable
     * @param m_Owner object
     * */
    public void setOwner(JFrame m_Owner) {
        this.m_Owner = m_Owner;
    }
    /**
     * set debug panel object to variable
     * @param m_DebugPanel object
     * */
    public void setDebugPanel(DebugPanel m_DebugPanel) {
        this.m_DebugPanel = m_DebugPanel;
    }
    /**
     * set game board object to variable
     * @param m_GameBoard object
     * */
    public void setGameBoard(GameBoard m_GameBoard) {
        this.m_GameBoard = m_GameBoard;
    }
    /**
     * set wall object to variable
     * @param m_Wall object
     * */
    public void setWall(Wall m_Wall) {
        this.m_Wall = m_Wall;
    }

    /** Initialises console */
    public void initialize() {
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        m_GameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = m_Wall.ball;
        m_DebugPanel.setValues(b.getSpeedX(), b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
