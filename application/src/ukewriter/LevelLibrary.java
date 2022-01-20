package ukewriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import java.io.IOException;

public class LevelLibrary {
    public Button level1;
    public Button level2;
    public Button level3;
    public Button level4;
    public Button level5;
    public Button back;
    public Button ukeTuner;
    public Button rsg;
    public Button load;

    /** level 1 button loads level 1 landing page (in same scene) */
    public void level1() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels/levelsLandingPages/level1.fxml"));
        level1.getScene().setRoot(root);
    }

    /** level 2 button loads level 2 landing page (in same scene) */
    public void level2() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels/levelsLandingPages/level2.fxml"));
        level2.getScene().setRoot(root);
    }

    /** level 3 button loads level 3 landing page (in same scene) */
    public void level3() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels/levelsLandingPages/level3.fxml"));
        level3.getScene().setRoot(root);
    }

    /** level 4 button loads level 4 landing page (in same scene) */
    public void level4() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels/levelsLandingPages/level4.fxml"));
        level4.getScene().setRoot(root);
    }

    /** level 5 button loads level 5 landing page (in same scene) */
    public void level5() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels/levelsLandingPages/level5.fxml"));
        level5.getScene().setRoot(root);
    }

    /** back button takes user back to start page (in same scene) */
    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        back.getScene().setRoot(root);
    }

    /** chord library button takes user to chord library page (in same scene) */
    public void chordLib() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chordLib.fxml"));
        back.getScene().setRoot(root);
    }

    /** ukulele tuner button takes user to ukulele tuner page (in same scene) */
    public void ukeTuner() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ukeTuner.fxml"));
        back.getScene().setRoot(root);
    }

    /** random sequence generator button takes user to random sequence generator page (in same scene) */
    public void rsg() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels/levelsPartA/rsg.fxml"));
        level5.getScene().setRoot(root);
    }

    public void load() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels/levelsPartB/load.fxml"));
        level5.getScene().setRoot(root);
    }
}
