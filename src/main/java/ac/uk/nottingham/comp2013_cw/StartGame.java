package ac.uk.nottingham.comp2013_cw;


import ac.uk.nottingham.comp2013_cw.menu.MainMenu;

/**
 *
 * @author  - Sahil Rai - modified
 *
 * This is the main class which extends from main menu.
 * Removed swing utilities from this class as it allows an on button effect
 * which initialises the game
 */
public class StartGame extends MainMenu {

    /** Main method to call the game
     * @param args takes in arguement
     * */
    public static void main(String[] args) {
        launch(args);
    }

    // use [space] to start/pause the game
    // use [←] to move the player left
    // use [→] to move the player right
    // use [esc] to enter/exit pause menu
    // use [alt+shift+f1] at any time to display debug panel
}




