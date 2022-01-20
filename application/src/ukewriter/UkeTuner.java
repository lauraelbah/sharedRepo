package ukewriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.IOException;

public class UkeTuner {
    public Button back;
    public Button go;
    public ImageView image;
    public Button g;
    public Button c;
    public Button e;
    public Button a;
    public Button playAll;
    private final Player player = new Player();

    /** back button takes user back to start page (in same scene) */
    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levelLibrary.fxml"));
        back.getScene().setRoot(root);
    }

    /** play all button plays all four strings one after the other 3 times */
    public void playAll() {
        Pattern pattern = new Pattern("T200 I[Guitar] Ri G5h C5h E5h A5w");
        Pattern quickPattern = new Pattern("T300 I[Guitar] Ri G5i C5i E5i A5w");
        player.play(pattern);
        player.play(quickPattern);
    }

    /** G button plays pitch */
    public void g() {
        Pattern pattern = new Pattern("I[Guitar] Ri G5w");
        player.play(pattern);
    }

    /** C button plays pitch */
    public void c() {
        Pattern pattern = new Pattern("I[Guitar] Ri C5w");
        player.play(pattern);
    }

    /** E button plays pitch */
    public void e() {
        Pattern pattern = new Pattern("I[Guitar] Ri E5w");
        player.play(pattern);
    }

    /** A button plays pitch */
    public void a() {
        Pattern pattern = new Pattern("I[Guitar] Ri A5w");
        player.play(pattern);
    }

    /** close button for pop-ups */
    public void close() {
        go.getScene().getWindow().hide();
    }

}
