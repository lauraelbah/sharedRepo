package ukewriter.levels.levelsPartA;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ukewriter.musicClasses.Key;
import ukewriter.musicClasses.chordSound.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Random;


/** Rather than having a controller for each section of each level, the controllers have
 * been written to work across levels 1-5 */
public class LevelsPartA implements Initializable {
    // chord builder variables
    public static String key = "C";
    public static String majorMinor = "major";
    private String intervalSelected = "";
    private String chosenKey = "";
    public static String c1 = "";
    public static String c2 = "";
    public static String c3 = "";
    public static String c4 = "";
    private boolean init = false;
    private final chordSoundFactory chordSoundFactory = new chordSoundFactory();

    // app nav buttons (specific to each level)
    public Button forward1;
    public Button forward2;
    public Button forward3;
    public Button forward4;
    public Button forward5;
    public Button forward6;
    public Button back1;
    public Button back2;
    public Button back3;
    public Button back4;
    public Button back5;
    public Button backRSG;
    public Button tuner;

    // function buttons (same across levels)
    public Button go;
    public Button bottomQuestion;
    public Button cc1;
    public Button cc2;
    public Button cc3;
    public Button cc4;
    public Button randomSequence;
    public Button loop;
    public ChoiceBox<String> keyChoice;
    public Button setKey;
    public Button majMin;

    // chord buttons (same across levels)
    public Button I;
    public Button ISharp;
    public Button II;
    public Button IISharp;
    public Button III;
    public Button IV;
    public Button IVSharp;
    public Button V;
    public Button VSharp;
    public Button VI;
    public Button VISharp;
    public Button VII;
    public Button currentKeyVis;

    // chord labels (same across levels)
    public Text two;
    public Text three;
    public Text four;
    public Text five;
    public Text six;
    public Text seven;
    public Text o;

    // objects holding chord images (same across levels)
    public ImageView c1img;
    public ImageView c2img;
    public ImageView c3img;
    public ImageView c4img;

    /** forward button takes user to next stage of level (in same scene) */
    public void forward(ActionEvent actionEvent) throws IOException {
        Button buttonPressed = (Button) actionEvent.getSource();
        String whichLevel = buttonPressed.getId();
        Parent root = switch (whichLevel) {
            case "forward1" -> FXMLLoader.load(getClass().getResource("../levelsPartB/level1b.fxml"));
            case "forward2" -> FXMLLoader.load(getClass().getResource("../levelsPartB/level2b.fxml"));
            case "forward3" -> FXMLLoader.load(getClass().getResource("../levelsPartB/level3b.fxml"));
            case "forward4" -> FXMLLoader.load(getClass().getResource("../levelsPartB/level4b.fxml"));
            case "forward5" -> FXMLLoader.load(getClass().getResource("../levelsPartB/level5b.fxml"));
            case "forward6" -> FXMLLoader.load(getClass().getResource("../levelsPartB/rsgb.fxml"));
            default -> throw new IllegalStateException("Unexpected value: " + whichLevel);
        };
        buttonPressed.getScene().setRoot(root);
    }

    /** back button takes user back to relevant level landing page (in same scene) */
    public void back(ActionEvent actionEvent) throws IOException {
        // alerts user that their progression will not be saved
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to go back?");
        alert.setContentText("Your chord sequence will be lost.");
        Optional<ButtonType> result = alert.showAndWait();

        // if user clicks okay they are taken back to the relevant level landing page
        if (result.isPresent() && result.get() == ButtonType.OK){
            Button buttonPressed = (Button) actionEvent.getSource();
            String whichLevel = buttonPressed.getId();
            Parent root = switch (whichLevel) {
                case "back1" -> FXMLLoader.load(getClass().getResource("../levelsLandingPages/level1.fxml"));
                case "back2" -> FXMLLoader.load(getClass().getResource("../levelsLandingPages/level2.fxml"));
                case "back3" -> FXMLLoader.load(getClass().getResource("../levelsLandingPages/level3.fxml"));
                case "back4" -> FXMLLoader.load(getClass().getResource("../levelsLandingPages/level4.fxml"));
                case "back5" -> FXMLLoader.load(getClass().getResource("../levelsLandingPages/level5.fxml"));
                case "backRSG" -> FXMLLoader.load(getClass().getResource("../../levelLibrary.fxml"));
                default -> throw new IllegalStateException("Unexpected value: " + whichLevel);
            };
            buttonPressed.getScene().setRoot(root);
        }

        // if user clicks cancel or x, closes alert without changing the page
        else {
            alert.close();
        }
    }

    /** opens tuner pop-up in new scene */
    public void tuner() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("popUps/tunerPopUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Tuner");
        stage.setScene(scene);
        stage.show();
    }

    /** loads help pop-up in new scene */
    public void bottomQuestion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("popUps/bottomQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Help");
        stage.setScene(scene);
        stage.show();
    }

    /** close button (for pup-ups) */
    public void close() {
        go.getScene().getWindow().hide();
    }

    /** changes the chord in first position */
    public void changeChord1(){
        // if user has not chosen a new chord
        if(intervalSelected.equals("")){
            noChordSelected();
        }
        else{
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);  // gets the chord at the chosen interval in the current key
            chordName = chordName.replaceAll("#/", "Sharp");
            String imageURL = "file:src/resources/chordDiagrams/" + chordName + ".png";
            Image chordImage = new Image(imageURL);
            c1img.setImage(chordImage);
            if(!init){  // the init variable is true when the programme is initializing a new random sequence on opening of rsg
                chordSound chordSound = chordSoundFactory.getChordSound(chordName);
                chordSound.playNotes();
            }
            c1 = chordName;
        }
    }

    /** changes the chord in second position */
    public void changeChord2(){
        if(intervalSelected.equals("")){
            noChordSelected();
        }
        else{
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);
            chordName = chordName.replaceAll("#/", "Sharp");
            String imageURL = "file:src/resources/chordDiagrams/" + chordName + ".png";
            Image chordImage = new Image(imageURL);
            c2img.setImage(chordImage);
            if(!init){
                chordSound chordSound = chordSoundFactory.getChordSound(chordName);
                chordSound.playNotes();
            }
            c2 = chordName;
        }
    }

    /** changes the chord in third position */
    public void changeChord3(){
        if(intervalSelected.equals("")){
            noChordSelected();
        }
        else{
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);
            chordName = chordName.replaceAll("#/", "Sharp");
            String imageURL = "file:src/resources/chordDiagrams/" + chordName + ".png";
            Image chordImage = new Image(imageURL);
            c3img.setImage(chordImage);
            if(!init){
                chordSound chordSound = chordSoundFactory.getChordSound(chordName);
                chordSound.playNotes();
            }
            c3 = chordName;
        }
    }

    /** changes the chord in fourth position */
    public void changeChord4(){
        if(intervalSelected.equals("")){
            noChordSelected();
        }
        else{
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);
            chordName = chordName.replaceAll("#/", "Sharp");
            String imageURL = "file:src/resources/chordDiagrams/" + chordName + ".png";
            Image chordImage = new Image(imageURL);
            c4img.setImage(chordImage);
            if(!init){
                chordSound chordSound = chordSoundFactory.getChordSound(chordName);
                chordSound.playNotes();
            }
            c4 = chordName;
        }
    }

    /** alerts user if they try to change a chord but has not selected an interval */
    public void noChordSelected(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.setTitle("No chord selected!");
        alert.setHeaderText("Choose a chord along the bottom of the screen");
        alert.setContentText("then click the Change Chord button to update your sequence.");
        alert.show();
    }

    /** alerts user if they try to choose a chord that is not in the current key */
    public void notInKey() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.setTitle("Chord not in key!");
        alert.setHeaderText("This chord is not in your chosen key!");
        alert.setContentText("For now, stick to the chords in yellow which will fit best in your sequence.");
        alert.show();
    }

    /** checks chosen chord is in key then updates intervalSelected variable */
    public void intervalSelected(ActionEvent actionEvent) {
        Button buttonClicked = (Button) actionEvent.getSource();
        String buttonID = buttonClicked.getId();
        if(majorMinor.equals("major")){
            if (buttonID.equals("I") || buttonID.equals("II") || buttonID.equals("III") || buttonID.equals("IV") || buttonID.equals("V") || buttonID.equals("VI") || buttonID.equals("VII")){
                intervalSelected = buttonID;
            }
            else{
                notInKey();
            }
        }
        else{
            if (buttonID.equals("I") || buttonID.equals("II") || buttonID.equals("IISharp") || buttonID.equals("IV") || buttonID.equals("V") || buttonID.equals("VSharp") || buttonID.equals("VISharp")){
                intervalSelected = buttonID;
            }
            else{
                notInKey();
            }
        }
    }

    /** updates key centre of the scale on the buttons along the bottom and transposes chord sequence */
    public void changeKey() {
        if (chosenKey.equals("")){
            chosenKey = keyChoice.getValue();
        }
        if(chosenKey != null){
            Key keyCentre = new Key(chosenKey, majorMinor);

            // update button text
            ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(I, ISharp, II, IISharp, III, IV, IVSharp, V, VSharp, VI, VISharp, VII));
            ArrayList<String> scale = keyCentre.getScale();
            Font font1 = new Font("Courier Bold", 16);
            Font font2 = new Font("Courier Bold", 32);
            for(int i = 0; i < buttons.size(); i++){
                Button x = buttons.get(i);
                x.setText(scale.get(i));
                if(scale.get(i).length() > 1){
                    x.setFont(font1);
                }
                else{
                    x.setFont(font2);
                }
            }
            Key originalKey = new Key (key, majorMinor);
            key = chosenKey;

            if(!init){
                intervalSelected = originalKey.getInterval(c1); //getting interval of chords in original key
                changeChord1();

                intervalSelected = originalKey.getInterval(c2);
                changeChord2();

                if(!c3.equals("")){
                    intervalSelected = originalKey.getInterval(c3);
                    changeChord3();

                }
                if(!c4.equals("")){
                    intervalSelected = originalKey.getInterval(c4);
                    changeChord4();
                }
            }

            chosenKey = "";
            currentKeyVis.setText("Current key: " + key + " " + majorMinor);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.setTitle("No key centre selected!");
            alert.setHeaderText("Please select a key centre from the dropdown menu");
            alert.setContentText("then click the I button to change the key of your progression.");
            alert.show();
        }
    }

    /** updates key (maj/min) of the scale along the bottom and transposes chord sequence */
    public void majMin() {
        // updates button labels from major to minor or vice versa
        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(I, ISharp, II, IISharp, III, IV, IVSharp, V, VSharp, VI, VISharp, VII));
        Key originalKey = new Key (key, majorMinor);
        if(majorMinor.equals("major")){
            setMinor(buttons);
        }
        else{
            setMajor(buttons);
        }

        if(!init){  // key has been changed by user
            intervalSelected = originalKey.getInterval(c1); //getting interval of chords in original key (only need root without maj/min/dim)
            intervalSelected = originalKey.switchInterval(intervalSelected); // switching to maj/min interval where relevant
            changeChord1();


            intervalSelected = originalKey.getInterval(c2);
            intervalSelected = originalKey.switchInterval(intervalSelected);
            changeChord2();

            if(!c3.equals("")){
                intervalSelected = originalKey.getInterval(c3);
                intervalSelected = originalKey.switchInterval(intervalSelected);
                changeChord3();

            }
            if(!c4.equals("")){
                intervalSelected = originalKey.getInterval(c4);
                intervalSelected = originalKey.switchInterval(intervalSelected);
                changeChord4();
            }
        }

        currentKeyVis.setText("Current key: " + key + " " + majorMinor);

    }

    /** sets major key formatting */
    private void setMajor(ArrayList<Button> buttons) {
        majorMinor = "major";

        // recolours buttons to highlight notes not in key
        for(int i = 0; i < buttons.size(); i++){
            Button x = buttons.get(i);
            if(i == 0 || i == 2 || i == 4 || i == 5 || i == 7 || i == 9 || i == 11){
                x.setStyle("-fx-background-color: #FBB14E;");
            }
            else{
                x.setStyle("-fx-background-color: #F08D76;");
            }
        }

        // changes and repositions interval labels
        AnchorPane.setRightAnchor(o, 123.99609375);
        AnchorPane.setLeftAnchor(three, 367.0);
        three.setText("iii");
        four.setText("IV");
        AnchorPane.setLeftAnchor(six, 666.0);
        AnchorPane.setRightAnchor(seven, 134.0);
        six.setText("vi");
        seven.setText("vii");
    }

    /** sets minor key formatting */
    private void setMinor(ArrayList<Button> buttons) {
        majorMinor = "minor";

        // recolours buttons to highlight notes not in key
        for(int i = 0; i < buttons.size(); i++){
            Button x = buttons.get(i);
            if(i == 0 || i == 2 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10){
                x.setStyle("-fx-background-color: #FBB14E;");
            }
            else{
                x.setStyle("-fx-background-color: #F08D76;");
            }
        }

        // changes and repositions interval labels
        AnchorPane.setRightAnchor(o, 659.99853515625);
        AnchorPane.setLeftAnchor(three, 308.0);
        three.setText("III");
        four.setText("iv");
        AnchorPane.setLeftAnchor(six, 607.0);
        AnchorPane.setRightAnchor(seven, 194.0);
        six.setText("VI");
        seven.setText("VII");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // checks which level landing page the user is coming from and sets chord variable defaults accordingly
        String string = url.toString();
        string = string.substring(string.length() - 12);
        key = "Cmajor";
        switch (string) {
            case "level1a.fxml" -> {
                c1 = "Cmajor";
                c2 = "Gmajor";
                c3 = "";
                c4 = "";
            }
            case "level2a.fxml" -> {
                c1 = "Cmajor";
                c2 = "Fmajor";
                c3 = "Gmajor";
                c4 = "";
            }
            case "level3a.fxml" -> {
                c1 = "Cmajor";
                c2 = "Aminor";
                c3 = "Fmajor";
                c4 = "Gmajor";
            }
            case "level4a.fxml" -> {
                c1 = "Cmajor";
                c2 = "Gmajor";
                c3 = "Aminor";
                c4 = "Fmajor";
            }
            case "level5a.fxml" -> {
                c1 = "Aminor";
                c2 = "Dminor";
                c3 = "Gmajor";
                c4 = "Cmajor";
            }
            case "rtA/rsg.fxml" -> {
                c1 = "Aminor";
                c2 = "Dminor";
                c3 = "Gmajor";
                c4 = "Cmajor";
                randomInit();  // initialises a new random sequence
            }
        }
    }

    /** generates random key and sequence for random sequence generator */
    private void randomInit() {
        init = true;
        randomKey();  // sets a new random key
        randomSequence();  // sets a new random sequence
        init = false;
    }

    /** generates a random key and updates GUI */
    public void randomKey(){
        Key k = new Key("C", "major"); // creating a key variable for use of its methods, so key centre is arbitrary
        String[] possKeys = k.cycle1;
        Random r = new Random();
        int r1 = r.nextInt(2);
        if(r1 == 0){
            majorMinor = "major";
        }
        else{
            majorMinor = "minor";
        }

        majMin();

        r1 = r.nextInt(possKeys.length);
        chosenKey = possKeys[r1];
        changeKey();
    }

    /** generates a random chord sequence and updates GUI */
    public void randomSequence() {
        String[] possChords;
        Random r = new Random();
        Key k = new Key(key, majorMinor);

        // generating four random int values to use as index values to get random intervals for chord sequence
        int r1 = r.nextInt(7);
        int r2 = r.nextInt(7);
        int r3 = r.nextInt(7);
        int r4 = r.nextInt(7);

        // making sure no chords will be duplicates
        while(r1 == r2){
            r2 = r.nextInt(7);
        }
        while(r2 == r3){
            r3 = r.nextInt(7);
        }
        while(r3 == r4){
            r4 = r.nextInt(7);
        }


        if (majorMinor.equals("major")){
            possChords = new String[]{"I", "II", "III", "IV", "V", "VI", "VII"};
        }
        else{
            possChords = new String[]{"I", "II", "IISharp", "IV", "V", "VSharp", "VISharp"};
        }

        c1 = k.getChord(possChords[r1]);
        String chord = c1.replaceAll("#/", "Sharp");
        String imageURL = "file:src/resources/chordDiagrams/" + chord + ".png";
        Image chordImage = new Image(imageURL);
        c1img.setImage(chordImage);

        c2 = k.getChord(possChords[r2]);
        chord = c2.replaceAll("#/", "Sharp");
        imageURL = "file:src/resources/chordDiagrams/" + chord + ".png";
        chordImage = new Image(imageURL);
        c2img.setImage(chordImage);

        if(!c3.equals("")){
            c3 = k.getChord(possChords[r3]);
            chord = c3.replaceAll("#/", "Sharp");
            imageURL = "file:src/resources/chordDiagrams/" + chord + ".png";
            chordImage = new Image(imageURL);
            c3img.setImage(chordImage);
        }
        if(!c4.equals("")){
            c4 = k.getChord(possChords[r4]);
            chord = c4.replaceAll("#/", "Sharp");
            imageURL = "file:src/resources/chordDiagrams/" + chord + ".png";
            chordImage = new Image(imageURL);
            c4img.setImage(chordImage);
        }

    }

    /** plays chord sequence */
    public void play() {
        chordSound chordOne = chordSoundFactory.getChordSound(c1);
        chordSound chordTwo = chordSoundFactory.getChordSound(c2);
        if(c3.equals("")){
            chordOne.playChord();
            chordTwo.playChord();
        }
        else if (c4.equals("")){
            chordSound chordThree = chordSoundFactory.getChordSound(c3);
            chordOne.playChord();
            chordTwo.playChord();
            chordThree.playChord();
        }
        else{
            chordSound chordThree = chordSoundFactory.getChordSound(c3);
            chordSound chordFour = chordSoundFactory.getChordSound(c4);
            chordOne.playChord();
            chordTwo.playChord();
            chordThree.playChord();
            chordFour.playChord();
        }
    }
}
