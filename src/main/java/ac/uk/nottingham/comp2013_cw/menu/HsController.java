package ac.uk.nottingham.comp2013_cw.menu;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author Sahil Rai
 * This method is attempt to print highscore.dat file onto javafx screen
 * NOTE: Not working
 * */
public class HsController {
/*
    @FXML
    TextArea highScoreData;
    *//**
     * Attempt to initialize highscore.dat file onto javafx scene
     * @param url not used
     * @param rb not used
     * *//*
    public void initialize(URL url, ResourceBundle rb){
        try{
            Scanner scan =
                    new Scanner(new File("src/highscore.txt")).useDelimiter
                    ("\\s+");
            while (scan.hasNext()){
                if (scan.hasNextInt()){
                    highScoreData.append(scan.nextInt() + "");
                }
                else{
                    highScoreData.append(scan.next() + "");
                }
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }*/
}
