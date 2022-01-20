package ukewriter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class welcome {

    public Button startBtn;

    /** start button loads up level library (in same scene) */
    public void startApp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levelLibrary.fxml"));
        startBtn.getScene().setRoot(root);

    }

}
