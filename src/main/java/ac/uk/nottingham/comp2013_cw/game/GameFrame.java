package ac.uk.nottingham.comp2013_cw.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * @author - Sahil Rai - modified
 * <p>
 * This class generates the game board and implements listener focus where if
 * focus is lost with no activity the user is notified.
 */

public class GameFrame extends JFrame implements WindowFocusListener {

    private static final int HALF = 2;
    /** This method initialises strings for the title */
    private static final String DEF_TITLE = "START";
    /** defines gameboard */
    private GameBoard gameBoard;
    /** defines gaming */
    private boolean gaming;

    /** This method sets up the game frame*/
    public GameFrame() {
        super();
        gaming = false;
        this.setLayout(new BorderLayout());
        gameBoard = new GameBoard(this);
        this.add(gameBoard, BorderLayout.CENTER);
        initialize();
        this.addWindowFocusListener(this);
    }
    /**
     * @return gets gameboard
     * */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    /**
     * Set game board object to variable
     * @param gameBoard object
     * */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

   /** This method initialises the game frame to load up properties in the class*/
    public void initialize() {
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /** This method sets automatically location using x y values */
    public void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / HALF;
        int y = (size.height - this.getHeight()) / HALF;
        this.setLocation(x, y);
    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        // the first time the frame loses focus is because it has been disposed to install the GameBoard, so went it regains the focus it's ready to play. of course calling a method such as 'onLostFocus' is useful only if the GameBoard as been displayed at least once
        gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (gaming)
            gameBoard.onLostFocus();

    }
}
