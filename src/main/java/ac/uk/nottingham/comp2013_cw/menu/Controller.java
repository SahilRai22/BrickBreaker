package ac.uk.nottingham.comp2013_cw.menu;
import ac.uk.nottingham.comp2013_cw.game.GameBoard;
import ac.uk.nottingham.comp2013_cw.game.GameFrame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
/**
 * @author Sahil Rai
 * This class is the controller class which is uses to cotrol mainmenu screen
 * utilising javafx
 * */
public class Controller {

    @FXML
    private Button btnHelp, btnHighscore, btnExit, btnBack; //btnStart
    @FXML
    private ColorPicker colourOption;

    Media media;
    {
        try {
            media = new Media(new File("src/main/resources/ac/uk/nottingham/comp2013_cw/music/arcade.mp3")
                    .toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    MediaPlayer musicPlayer = new MediaPlayer(media);


    /**This method intialises game screen*/
    @FXML
    public void switchStart() {
        //Stage stage = (Stage) btnStart.getScene().getWindow();
        SwingUtilities.invokeLater(() -> new GameFrame().initialize());
        //stage.hide();
        // bug: Apple AWT Internal Exception:
        // A fatal error has been detected
        // by the Java Runtime Environment:
    }

    /**
     * This method allows the colour of game screen to be changed
     * */
    @FXML
    public void switchColour(){
        Color color = new Color(
                (float) colourOption.getValue().getBlue(),
                (float) colourOption.getValue().getRed(),
                (float) colourOption.getValue().getGreen(),
                (float) colourOption.getValue().getOpacity());
        GameBoard.BG_COLOR = color;
    }
    /**
     * This method goes to help screen
     * @throws IOException If there is error
     * */
   @FXML
    public void switchHelp() throws IOException{
       Stage stage = (Stage) btnHelp.getScene().getWindow();
       URL url = new File("src/main/resources/ac/uk/nottingham/" +
               "comp2013_cw/help.fxml").toURI().toURL();
       Parent subRoot = FXMLLoader.load(url);
       stage.setScene(new Scene(subRoot));
   }
    /**
     * This method goes back to main screen
     * @throws IOException if error
     * */
    @FXML
    public void switchMain() throws IOException{
        Stage stage = (Stage) btnBack.getScene().getWindow();
        URL url = new File("src/main/resources/ac/uk/nottingham/" +
                "comp2013_cw/menu.fxml").toURI().toURL();
        Parent subRoot = FXMLLoader.load(url);
        stage.setScene(new Scene(subRoot));
    }
    /**
     * When pressed this method switches to high score screen
     * @throws IOException If there is error
     * */
    @FXML
    public void switchHighScore() throws IOException{
        Stage stage = (Stage) btnHighscore.getScene().getWindow();
        URL url = new File("src/main/resources/ac/uk/nottingham/" +
                "comp2013_cw/highscore.fxml").toURI().toURL();
        Parent subRoot = FXMLLoader.load(url);
        stage.setScene(new Scene(subRoot));
    }
    /**
     *When pressed this method closes screen
     * */
    @FXML
    public void switchExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    /**
     * When pressed this method plays music in menu
     * @throws IOException If there is error
     * */
    @FXML
    private void menuMusic(){
        musicPlayer.play();
    }

    @FXML
    private void stopMusic() {
        musicPlayer.stop();
    }


}
