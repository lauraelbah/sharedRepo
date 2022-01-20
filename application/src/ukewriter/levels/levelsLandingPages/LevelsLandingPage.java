package ukewriter.levels.levelsLandingPages;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Rather than having a controller for each section of each level, the controllers have
 * been written to work across levels 1-5 */

public class LevelsLandingPage implements Initializable {
    public Button forward1;
    public Button forward2;
    public Button forward3;
    public Button forward4;
    public Button forward5;
    public Button back;
    public WebView vid1;
    public String vid1URL = "https://www.youtube.com/embed/byQIPdHMpjc";
    public String vid2URL = "https://www.youtube.com/embed/QJO3ROT-A4E";
    public String vid3URL = "https://www.youtube.com/embed/tbU3zdAgiX8";
    public String vid4URL = "https://www.youtube.com/embed/atxUuldUcfI";
    public String vid5URL = "https://www.youtube.com/embed/ARt9HV9T0w8";

    /** back button takes user back to level library page (in same scene) */
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../levelLibrary.fxml"));
        back.getScene().setRoot(root);
    }

    /** forward button takes user to next stage of level (in same scene) and opens the
     * level specific pop-up in a new scene */
    public void forward(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Button buttonPressed = (Button) actionEvent.getSource();
            String whichLevel = buttonPressed.getId();
            Stage stage = new Stage();
            Parent root;
            switch (whichLevel) {
                case "forward1":
                    fxmlLoader.setLocation(getClass().getResource("../levelsPartA/popUps/popUp1a.fxml"));
                    stage.setTitle("Writing a Song with Only Two Chords!");
                    root = FXMLLoader.load(getClass().getResource("../levelsPartA/level1a.fxml"));
                    break;

                case "forward2":
                    fxmlLoader.setLocation(getClass().getResource("../levelsPartA/popUps/popUp2a.fxml"));
                    stage.setTitle("The Power of Three!");
                    root = FXMLLoader.load(getClass().getResource("../levelsPartA/level2a.fxml"));
                    break;

                case "forward3":
                    fxmlLoader.setLocation(getClass().getResource("../levelsPartA/popUps/popUp3a.fxml"));
                    stage.setTitle("The 50's Doo-Wop Progression!");
                    root = FXMLLoader.load(getClass().getResource("../levelsPartA/level3a.fxml"));
                    break;

                case "forward4":
                    fxmlLoader.setLocation(getClass().getResource("../levelsPartA/popUps/popUp4a.fxml"));
                    stage.setTitle("The Singer/Songwriter Progression!");
                    root = FXMLLoader.load(getClass().getResource("../levelsPartA/level4a.fxml"));
                    break;

                case "forward5":
                    fxmlLoader.setLocation(getClass().getResource("../levelsPartA/popUps/popUp5a.fxml"));
                    stage.setTitle("The Circle of Fifths Progression!");
                    root = FXMLLoader.load(getClass().getResource("../levelsPartA/level5a.fxml"));
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + whichLevel);
            }

            vid1.getEngine().load(null);
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.show();
            buttonPressed.getScene().setRoot(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Loads YouTube video to the scene when the relevant page is loaded */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final WebEngine web1 = vid1.getEngine();
        String string = url.getFile();
        String level = string.substring(string.length() - 6, string.length() - 5);
        switch (level) {
            case "1" -> web1.load(vid1URL);
            case "2" -> web1.load(vid2URL);
            case "3" -> web1.load(vid3URL);
            case "4" -> web1.load(vid4URL);
            case "5" -> web1.load(vid5URL);
        }
    }
}
