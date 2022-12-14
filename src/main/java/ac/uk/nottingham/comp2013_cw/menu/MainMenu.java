package ac.uk.nottingham.comp2013_cw.menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.Objects;

/**
 * @author Sahil Rai
 * This class implements game menu giving features to user
 * */
public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("menu.fxml")));
        primaryStage.setTitle("Breakout");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }



}

