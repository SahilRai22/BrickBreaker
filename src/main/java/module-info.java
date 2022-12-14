/**
 * This is the module package with all the dependencies
 * */
module ac.uk.nottingham.comp2013_cw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.media;

    opens ac.uk.nottingham.comp2013_cw to javafx.fxml;
    exports ac.uk.nottingham.comp2013_cw;
    exports ac.uk.nottingham.comp2013_cw.ball;
    opens ac.uk.nottingham.comp2013_cw.ball to javafx.fxml;
    opens ac.uk.nottingham.comp2013_cw.brick to javafx.fxml;
    exports ac.uk.nottingham.comp2013_cw.brick;
    opens ac.uk.nottingham.comp2013_cw.debug to javafx.fxml;
    exports ac.uk.nottingham.comp2013_cw.debug;
    opens ac.uk.nottingham.comp2013_cw.game to javafx.fxml;
    exports ac.uk.nottingham.comp2013_cw.game;
    exports ac.uk.nottingham.comp2013_cw.menu;
    opens ac.uk.nottingham.comp2013_cw.menu to javafx.fxml;

}