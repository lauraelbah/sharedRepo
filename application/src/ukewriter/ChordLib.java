package ukewriter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ukewriter.musicClasses.chordSound.chordSound;
import ukewriter.musicClasses.chordSound.chordSoundFactory;

import java.io.IOException;

public class ChordLib {
    public Button back;
    private String chord = "Cmajor";
    public ChoiceBox<String> rootChoice;
    public ChoiceBox<String> typeChoice;
    public ImageView image;
    public Button go;

    /** loads image of requested chord and plays the notes of the chord */
    public void loadChord() {
        String root = rootChoice.getValue();
        String type = typeChoice.getValue();
        if(root == null || type == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.setTitle("No chord selected!");
            alert.setHeaderText("Please select a root and type of chord from the dropdown menus");
            alert.setContentText("then click the go! button.");
            alert.show();
        }
        else{
            String chordName = root + type;
            chordName = chordName.replaceAll("#/", "Sharp");
            if(chordName.contains("diminished")){
                chordName = chordName.substring(0, chordName.length() - 7);
            }
            String imageURL = "file:src/resources/chordDiagrams/" + chordName + ".png";
            Image chordImage = new Image(imageURL);
            image.setImage(chordImage);
            chordSound chordSound = chordSoundFactory.getChordSound(chordName);
            chordSound.playNotes();
            chord = chordName;
        }
    }

    /** plays the chord */
    public void play() {
        chordSound chordOne = chordSoundFactory.getChordSound(chord);
        chordOne.playChord();
    }

    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levelLibrary.fxml"));
        back.getScene().setRoot(root);
    }
}
