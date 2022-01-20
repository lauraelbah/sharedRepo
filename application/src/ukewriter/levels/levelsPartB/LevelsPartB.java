package ukewriter.levels.levelsPartB;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import ukewriter.levels.levelsPartA.LevelsPartA;
import ukewriter.musicClasses.*;
import ukewriter.musicClasses.chordSound.chordSound;
import ukewriter.musicClasses.chordSound.chordSoundFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Rather than having a controller for each section of each level, the controllers have
 * been written to work across levels 1-5 */
public class LevelsPartB implements Initializable {
    // back end required variables
    public String key;
    public String majorMinor;
    public static int tempo = 116;
    private Song song = new Song();
    private final MelodyAssistant melodyAssistant = new MelodyAssistant();
    public final chordSoundFactory chordSoundFactory = new chordSoundFactory();
    private String intervalSelected = "";
    private int pageNo = 1;
    private int noPages = 1;
    public Text pageLabel;
    private int chordsPerPage;
    public AnchorPane pane;
    FileChooser fileChooser = new FileChooser();

    // chord variables which save the sequence the user has finished the previous section with
    private String partAChord1;
    private String partAChord2;
    private String partAChord3;
    private String partAChord4;

    // chord name variables
    public String c1;
    public String c2;
    public String c3;
    public String c4;

    // app nav buttons
    public Button forward; // the same for all levels
    public Button back1;
    public Button back2;
    public Button back3;
    public Button back4;
    public Button back5;

    // song editing related variables
    public Button chordOne = new Button();
    public Button chordTwo = new Button();
    public Button chordThree = new Button();
    public Button chordFour = new Button();
    public TextField bpmInput;
    public Button play;
    public Button next;
    public Button previous;
    public TextField lyric1;
    public TextField lyric2;
    public TextField lyric3;
    public TextField lyric4;

    // melody buttons
    public Button q1b1;
    public Button q1b2;
    public Button q1b3;
    public Button q1b4;
    public Button q1b5;
    public Button q1b6;
    public Button q1b7;
    public Button q1b8;
    public Button q2b1;
    public Button q2b2;
    public Button q2b3;
    public Button q2b4;
    public Button q2b5;
    public Button q2b6;
    public Button q2b7;
    public Button q2b8;
    public Button q3b1;
    public Button q3b2;
    public Button q3b3;
    public Button q3b4;
    public Button q3b5;
    public Button q3b6;
    public Button q3b7;
    public Button q3b8;
    public Button q4b1;
    public Button q4b2;
    public Button q4b3;
    public Button q4b4;
    public Button q4b5;
    public Button q4b6;
    public Button q4b7;
    public Button q4b8;
    private Node currentMovingNote;

    // pop-up variables
    public Button go;
    public Button bottomQuestion;
    public Button finish;

    // chord buttons
    public Button I = new Button();
    public Button ISharp = new Button();
    public Button II = new Button();
    public Button IISharp = new Button();
    public Button III = new Button();
    public Button IV = new Button();
    public Button IVSharp = new Button();
    public Button V = new Button();
    public Button VSharp = new Button();
    public Button VI = new Button();
    public Button VISharp = new Button();
    public Button VII = new Button();

    // chord labels (same across levels)
    public Text one;
    public Text two;
    public Text three;
    public Text four;
    public Text five;
    public Text six;
    public Text seven;
    public Text o;

    /** forward button warns user to ensure progress is saved, takes user back to the level library
     * and displays level complete pop-up*/
    public void forward(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to continue?");
        alert.setContentText("Any unsaved changes will be lost.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            FXMLLoader fxmlLoader = new FXMLLoader();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../levelLibrary.fxml"));
            fxmlLoader.setLocation(getClass().getResource("popUps/finished.fxml"));
            stage.setTitle("Well done!");
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.show();
            forward.getScene().setRoot(root);
        }
        else {
            alert.close();
        }
    }

    /** back button takes user back to relevant Level Part A page (in same scene) */
    public void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to go back?");
        alert.setContentText("Any unsaved changes will be lost.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            Button buttonPressed = (Button) actionEvent.getSource();
            String whichLevel = buttonPressed.getId();
            Parent root = switch (whichLevel) {
                case "back1" -> FXMLLoader.load(getClass().getResource("../levelsPartA/level1a.fxml"));
                case "back2" -> FXMLLoader.load(getClass().getResource("../levelsPartA/level2a.fxml"));
                case "back3" -> FXMLLoader.load(getClass().getResource("../levelsPartA/level3a.fxml"));
                case "back4" -> FXMLLoader.load(getClass().getResource("../levelsPartA/level4a.fxml"));
                case "back5" -> FXMLLoader.load(getClass().getResource("../levelsPartA/level5a.fxml"));
                case "back6" -> FXMLLoader.load(getClass().getResource("../levelsPartA/rsg.fxml"));
                case "back7" -> FXMLLoader.load(getClass().getResource("../../levelLibrary.fxml"));
                default -> throw new IllegalStateException("Unexpected value: " + whichLevel);
            };
            buttonPressed.getScene().setRoot(root);
        }
        else{
            alert.close();
        }

    }

    /** close button for pop-ups */
    public void close(ActionEvent actionEvent) {
        go.getScene().getWindow().hide();
    }

    /** loads help pop-up (same across levels) */
    public void bottomQuestion(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("popUps/bottomQuestionB.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Help");
        stage.setScene(scene);
        stage.show();
    }

    /** plays user song */
    public void play() {
        saveLyrics();  // ensures lyrics on current page have been saved

        // displays first page
        pageNo = 1;
        pageLabel.setText("Page 1");
        updateChords();
        updateLyrics();
        updateNotes();

        // checks if tempo has been updated
        try{
            tempo = Integer.parseInt(bpmInput.getText());
        }
        catch (NumberFormatException ignored) {}

        // multithreading to allow changing page to occur at the same time as song playing
        Thread t1 = new Thread(() -> {  // plays audio
            song.setTempo(tempo);
            song.playSong();
        });

        Thread t2 = new Thread(() -> {  // cycles through song pages
            for(int i = 0; i < noPages - 1; i++){
                try {
                    TimeUnit.SECONDS.sleep((16 * 60 / tempo)); // length of bar in seconds

                    // offset as JFugue seems to play slightly slower
                    // TODO improve the accuracy of these values
                    int offset;
                    if(tempo < 100){
                        offset = 420;
                    }
                    else if(tempo < 250){
                        offset = 350;
                    }
                    else{
                        offset = 280;
                    }
                    TimeUnit.MILLISECONDS.sleep(offset);

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pageNo += 1;
                pageLabel.setText("Page " + pageNo);
                updateChords();
                updateLyrics();
                updateNotes();
            }
        });

        t2.start();
        t1.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            pane.setOnKeyPressed(evt -> moveButton(evt.getCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        song = new Song();
        currentMovingNote = q1b1;
        pageNo = 1;

        // checks which level we have come from
        String string = url.toString();
        string = string.substring(string.length() - 9);

        if(string.equals("load.fxml")){  // loads in user song from txt file
            chordsPerPage = 4;
            String[] chords = new String[0];
            String[] notes = new String[0];
            ArrayList<String> lyrics = new ArrayList<>();

            File file = fileChooser.showOpenDialog(null);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key = reader.readLine();
                majorMinor = reader.readLine();
                tempo = Integer.parseInt(reader.readLine());
                chords = reader.readLine().split(" ");
                notes = reader.readLine().split(" ");
                String read;
                while ((read = reader.readLine()) != null){
                    lyrics.add(read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            noPages = chords.length / 4;
            int noteIndex = 0;
            String lyric = "";
            for(int i = 0; i < chords.length; i++){
                if(i < lyrics.size()){
                    lyric = lyrics.get(i);
                }
                else{
                    lyric = "";
                }
                Bar bar = new Bar(chords[i], lyric);
                bar.setB1(notes[noteIndex]);
                noteIndex++;
                bar.setB2(notes[noteIndex]);
                noteIndex++;
                bar.setB3(notes[noteIndex]);
                noteIndex++;
                bar.setB4(notes[noteIndex]);
                noteIndex++;
                bar.setB5(notes[noteIndex]);
                noteIndex++;
                bar.setB6(notes[noteIndex]);
                noteIndex++;
                bar.setB7(notes[noteIndex]);
                noteIndex++;
                bar.setB8(notes[noteIndex]);
                noteIndex++;
                song.addBar(bar);
            }
            partAChord1 = chords[0];
            partAChord2 = chords[1];
            partAChord3 = chords[2];
            partAChord4 = chords[3];

            updateLyrics();
            updateChords();
            updateNotes();
        }
        else{
            majorMinor = LevelsPartA.majorMinor;
            key = LevelsPartA.key;
            tempo = 116;
            ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(I, ISharp, II, IISharp, III, IV, IVSharp, V, VSharp, VI, VISharp, VII));
            setMinor(buttons);
            changeKey();

            // chord constants stored from previous section
            partAChord1 = LevelsPartA.c1;
            partAChord2 = LevelsPartA.c2;
            partAChord3 = LevelsPartA.c3;

            // for level 2 where there are 3 chords set chord 4 to match chord 3
            if(LevelsPartA.c4.equals("") && !partAChord3.equals("")){
                partAChord4 = partAChord3;
            }
            else{
                partAChord4 = LevelsPartA.c4;
            }

            if(partAChord3.equals("")){
                chordsPerPage = 2;
            }
            else{
                chordsPerPage = 4;
            }

            resetChords();
            addBars();
        }
    }

    /** adds current bars to song */
    private void addBars() {
        if(c3.equals("")){
            song.addBar(new Bar(c1, "", true));
            song.addBar(new Bar(c2, "", true));
        }
        else{
            song.addBar(new Bar(c1, "", false));
            song.addBar(new Bar(c2, "", false));
            song.addBar(new Bar(c3, "", false));
            song.addBar(new Bar(c4, "", false));
        }
    }

    /** updates key centre of the scale along the bottom and transposes chord sequence */
    public void changeKey() {
        Key keyCentre = new Key(key, majorMinor);

        // updates button labels
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
    }

    /** sets minor key formatting for interval labels*/
    private void setMinor(ArrayList<Button> buttons) {
        if(majorMinor.equals("minor")){
            // sets button colours to highlight those that are not in the current key
            for(int i = 0; i < buttons.size(); i++){
                Button x = buttons.get(i);
                if(i == 0 || i == 2 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10){
                    x.setStyle("-fx-background-color: #FBB14E;");
                }
                else{
                    x.setStyle("-fx-background-color: #F08D76;");
                }
            }

            // updates the interval labels
            one.setText("i");
            AnchorPane.setRightAnchor(o, 659.99853515625);
            AnchorPane.setLeftAnchor(three, 308.0);
            three.setText("III");
            four.setText("iv");
            AnchorPane.setLeftAnchor(six, 607.0);
            AnchorPane.setRightAnchor(seven, 194.0);
            six.setText("VI");
            seven.setText("VII");
        }
    }

    /** updates chord one or if no chord change selected, plays chord */
    public void chordOne(ActionEvent actionEvent) {
        if(!intervalSelected.equals("")){
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);
            chordOne.setText(chordName);
            chordName = chordName.replaceAll("#/", "Sharp");
            c1 = chordName;
            chordSound cs = chordSoundFactory.getChordSound(chordName);
            cs.playChord();

            // saves new chord in the song
            int barNo;
            Bar newBar;
            if(chordsPerPage == 2){
                barNo = (pageNo * 2) - 2;
            }
            else{
                barNo = (pageNo * 4) - 4;
            }
            newBar = song.getBar(barNo);
            newBar.setChordName(c1);
            song.updateBar(barNo, newBar);
        }
        else{
            chordSound cs = chordSoundFactory.getChordSound(c1);
            cs.playChord();
        }

    }

    /** updates chord two or if no chord change selected, plays chord */
    public void chordTwo(ActionEvent actionEvent) {
        if(!intervalSelected.equals("")){
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);
            chordTwo.setText(chordName);
            chordName = chordName.replaceAll("#/", "Sharp");
            c2 = chordName;
            chordSound cs = chordSoundFactory.getChordSound(chordName);
            cs.playChord();

            int barNo;
            Bar newBar;
            if(chordsPerPage == 2){
                barNo = (pageNo * 2) - 1;
            }
            else{
                barNo = (pageNo * 4) - 3;
            }
            newBar = song.getBar(barNo);
            newBar.setChordName(c2);
            song.updateBar(barNo, newBar);

        }
        else{
            chordSound cs = chordSoundFactory.getChordSound(c2);
            cs.playChord();
        }
    }

    /** updates chord three or if no chord change selected, plays chord */
    public void chordThree(ActionEvent actionEvent) {
        if(!intervalSelected.equals("")){
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);
            chordThree.setText(chordName);
            chordName = chordName.replaceAll("#/", "Sharp");
            c3 = chordName;
            chordSound cs = chordSoundFactory.getChordSound(chordName);
            cs.playChord();

            int barNo = (pageNo * 4) - 2;
            Bar newBar = song.getBar(barNo);
            newBar.setChordName(c3);
            song.updateBar(barNo, newBar);
        }
        else{
            chordSound cs = chordSoundFactory.getChordSound(c3);
            cs.playChord();
        }
    }

    /** updates chord four or if no chord change selected, plays chord */
    public void chordFour(ActionEvent actionEvent) {
        if(!intervalSelected.equals("")){
            Key currentKey = new Key(key, majorMinor);
            String chordName = currentKey.getChord(intervalSelected);
            chordFour.setText(chordName);
            chordName = chordName.replaceAll("#/", "Sharp");
            c4 = chordName;
            chordSound cs = chordSoundFactory.getChordSound(chordName);
            cs.playChord();

            int barNo = (pageNo * 4) - 1;
            Bar newBar = song.getBar(barNo);
            newBar.setChordName(c4);
            song.updateBar(barNo, newBar);
        }
        else{
            chordSound cs = chordSoundFactory.getChordSound(c4);
            cs.playChord();
        }
    }

    /** updates intervalSelected variable used for changing chord */
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

    /** alerts user that chosen chord is not in the key they are writing in */
    public void notInKey() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.setTitle("Chord not in key!");
        alert.setHeaderText("This chord is not in your chosen key!");
        alert.setContentText("For now, stick to the chords in yellow which will fit best in your song.");
        alert.show();
    }

    /** Saves/updates current chords and lyrics in a Bar variable and adds this to the user's song if it is
     * not there already. If next bar is a new bar updates gui back to user's original chord sequence and
     * clears lyrics, if bar already exists, loads that bar */
    public void next(ActionEvent actionEvent) {
        saveLyrics();

        if(pageNo == noPages){ // if on the last page
            pageNo += 1;
            noPages += 1;
            pageLabel.setText("Page " + pageNo);

            lyric1.setText("");
            lyric2.setText("");

            if(!c3.equals("")){
                lyric3.setText("");
                lyric4.setText("");
            }

            resetChords();
            resetNotes();
            addBars();

        }
        else{ // need to navigate to next page
            pageNo += 1;
            pageLabel.setText("Page " + pageNo);
            updateChords();
            updateLyrics();
            updateNotes();
        }

    }

    /** saves lyrics to song */
    private void saveLyrics() {
        int barNo;
        if(chordsPerPage == 2){
            barNo = (pageNo * 2) - 2;
            Bar newBar1 = song.getBar(barNo);
            newBar1.setLyrics(lyric1.getText());
            song.updateBar(barNo, newBar1);

            barNo += 1;
            Bar newBar2 = song.getBar(barNo);
            newBar2.setLyrics(lyric2.getText());
            song.updateBar(barNo, newBar2);
        }
        else{
            barNo = (pageNo * 4) - 4;
            Bar newBar1 = song.getBar(barNo);
            newBar1.setLyrics(lyric1.getText());
            song.updateBar(barNo, newBar1);

            barNo += 1;
            Bar newBar2 = song.getBar(barNo);
            newBar2.setLyrics(lyric2.getText());
            song.updateBar(barNo, newBar2);

            barNo += 1;
            Bar newBar3 = song.getBar(barNo);
            newBar3.setLyrics(lyric3.getText());
            song.updateBar(barNo, newBar3);

            barNo += 1;
            Bar newBar4 = song.getBar(barNo);
            newBar4.setLyrics(lyric4.getText());
            song.updateBar(barNo, newBar4);
        }
    }

    /** clears notes when new page loaded */
    // TODO: fix ArrayList implementation of this?
    private void resetNotes() {
        AnchorPane.setBottomAnchor(q1b1, 89.0);
        q1b1.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q1b2, 89.0);
        q1b2.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q1b3, 89.0);
        q1b3.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q1b4, 89.0);
        q1b4.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q1b5, 89.0);
        q1b5.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q1b6, 89.0);
        q1b6.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q1b7, 89.0);
        q1b7.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q1b8, 89.0);
        q1b8.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b1, 89.0);
        q2b1.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b2, 89.0);
        q2b2.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b3, 89.0);
        q2b3.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b4, 89.0);
        q2b4.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b5, 89.0);
        q2b5.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b6, 89.0);
        q2b6.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b7, 89.0);
        q2b7.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q2b8, 89.0);
        q2b8.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b1, 89.0);
        q3b1.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b2, 89.0);
        q3b2.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b3, 89.0);
        q3b3.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b4, 89.0);
        q3b4.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b5, 89.0);
        q3b5.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b6, 89.0);
        q3b6.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b7, 89.0);
        q3b7.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q3b8, 89.0);
        q3b8.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b1, 89.0);
        q4b1.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b2, 89.0);
        q4b2.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b3, 89.0);
        q4b3.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b4, 89.0);
        q4b4.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b5, 89.0);
        q4b5.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b6, 89.0);
        q4b6.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b7, 89.0);
        q4b7.setStyle("-fx-background-color: #000000");

        AnchorPane.setBottomAnchor(q4b8, 89.0);
        q4b8.setStyle("-fx-background-color: #000000");
    }

    /** when new page loads, default chords loaded */
    private void resetChords() {
        c1 = partAChord1;
        c2 = partAChord2;
        c3 = partAChord3;
        c4 = partAChord4;

        chordOne.setText(c1.replaceAll("Sharp", "#/"));
        chordTwo.setText(c2.replaceAll("Sharp", "#/"));

        if(!c3.equals("")){ // if c3 exists c4 must also exist in this mode
            chordThree.setText(c3.replaceAll("Sharp", "#/"));
            chordFour.setText(c4.replaceAll("Sharp", "#/"));
        }
    }

    /** Updates notes when user moves between pages */
    private void updateNotes() {
        Key k = new Key(key, majorMinor);
        if(chordsPerPage == 2){
            Bar b1 = song.getBar((2 * pageNo) - 2);
            Bar b2 = song.getBar((2 * pageNo) - 1);

            String n = b1.getB1();
            double anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b1, anchor);
            String colour = melodyAssistant.getColour(k, n);
            q1b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB2();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB3();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB4();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB5();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB6();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB7();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB8();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b8.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB9();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b1, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB10();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB11();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB12();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB13();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB14();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB15();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB16();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b8.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB1();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b1, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB2();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB3();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB4();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB5();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB6();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB7();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB8();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b8.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB9();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b1, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB10();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB11();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB12();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB13();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB14();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB15();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB16();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b8.setStyle("-fx-background-color: #" + colour + ";");
        }
        else{
            Bar b1 = song.getBar((4 * pageNo) - 4);
            Bar b2 = song.getBar((4 * pageNo) - 3);
            Bar b3 = song.getBar((4 * pageNo) - 2);
            Bar b4 = song.getBar((4 * pageNo) - 1);

            String n = b1.getB1();
            double anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b1, anchor);
            String colour = melodyAssistant.getColour(k, n);
            q1b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB2();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB3();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB4();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB5();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB6();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB7();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b1.getB8();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q1b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q1b8.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB1();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b1, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB2();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB3();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB4();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB5();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB6();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB7();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b2.getB8();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q2b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q2b8.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB1();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b1, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB2();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB3();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB4();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB5();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB6();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB7();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b3.getB8();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q3b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q3b8.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB1();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b1, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b1.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB2();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b2, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b2.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB3();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b3, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b3.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB4();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b4, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b4.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB5();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b5, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b5.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB6();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b6, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b6.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB7();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b7, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b7.setStyle("-fx-background-color: #" + colour + ";");

            n = b4.getB8();
            anchor = melodyAssistant.getAnchor(k, n);
            AnchorPane.setBottomAnchor(q4b8, anchor);
            colour = melodyAssistant.getColour(k, n);
            q4b8.setStyle("-fx-background-color: #" + colour + ";");
        }
    }

    /** Updates chords on page change */
    private void updateChords() {
        if(chordsPerPage == 2){
            Bar b1 = song.getBar((2 * pageNo) - 2);
            Bar b2 = song.getBar((2 * pageNo) - 1);
            c1 = b1.getChordName();
            c2 = b2.getChordName();
        }
        else{
            Bar b1 = song.getBar((4 * pageNo) - 4);
            Bar b2 = song.getBar((4 * pageNo) - 3);
            Bar b3 = song.getBar((4 * pageNo) - 2);
            Bar b4 = song.getBar((4 * pageNo) - 1);
            c1 = b1.getChordName();
            c2 = b2.getChordName();
            c3 = b3.getChordName();
            c4 = b4.getChordName();
        }

        chordOne.setText(c1.replaceAll("Sharp", "#/"));
        chordTwo.setText(c2.replaceAll("Sharp", "#/"));

        if(!c3.equals("")){ // if c3 exists c4 must also exist in this mode
            chordThree.setText(c3.replaceAll("Sharp", "#/"));
            chordFour.setText(c4.replaceAll("Sharp", "#/"));
        }
    }

    /** Loads previous bar into GUI */
    public void previous(ActionEvent actionEvent) {
        saveLyrics();  // ensures current lyrics are saved
        if(pageNo != 1){  //cannot go to previous bar if there isn't one
            pageNo -= 1;
            pageLabel.setText("Page " + pageNo);
            updateChords();
            updateLyrics();
            updateNotes();
        }
    }

    /** updates lyrics on page change */
    public void updateLyrics() {
        if(chordsPerPage == 2){
            Bar b1 = song.getBar((2 * pageNo) - 2);
            Bar b2 = song.getBar((2 * pageNo) - 1);

            lyric1.setText(b1.getLyrics());
            lyric2.setText(b2.getLyrics());
        }
        else{
            Bar b1 = song.getBar((4 * pageNo) - 4);
            Bar b2 = song.getBar((4 * pageNo) - 3);
            Bar b3 = song.getBar((4 * pageNo) - 2);
            Bar b4 = song.getBar((4 * pageNo) - 1);

            lyric1.setText(b1.getLyrics());
            lyric2.setText(b2.getLyrics());
            lyric3.setText(b3.getLyrics());
            lyric4.setText(b4.getLyrics());
        }
    }

    /** moves button up to next position and updates colour */
    public void moveUp(Node button) throws NullPointerException{
        currentMovingNote = button;
        Button buttonPressed = (Button) button;
        Paint colour = buttonPressed.getBackground().getFills().get(0).getFill();
        String buttonColour = colour.toString();
        double newAnchor = melodyAssistant.getNextBottomAnchor(AnchorPane.getBottomAnchor(buttonPressed), buttonColour);
        AnchorPane.setBottomAnchor(buttonPressed, newAnchor);

        String newColour = melodyAssistant.getNextButtonColour(newAnchor, buttonColour);
        newColour = newColour.substring(2, 8);
        buttonPressed.setStyle("-fx-background-color: #" + newColour + ";");
        Note note = new Note(new Key(key, majorMinor), newAnchor);

        if(!newColour.equals("000000")){  // 000000 is black, which represents a rest
            note.playNote();
        }

        saveChanges(buttonPressed.getId(), note, newColour);  // saves new note in song
    }

    /** moves button down to next position and updates colour */
    public void moveDown(Node button) throws NullPointerException{
        currentMovingNote = button;
        Button buttonPressed = (Button) button;
        Paint colour = buttonPressed.getBackground().getFills().get(0).getFill();
        String buttonColour = colour.toString();
        double newAnchor = melodyAssistant.getNextBottomAnchorDown(AnchorPane.getBottomAnchor(buttonPressed), buttonColour);
        AnchorPane.setBottomAnchor(buttonPressed, newAnchor);

        String newColour = melodyAssistant.getNextButtonColour(newAnchor, buttonColour);
        newColour = newColour.substring(2, 8);
        buttonPressed.setStyle("-fx-background-color: #" + newColour + ";");
        Note note = new Note(new Key(key, majorMinor), newAnchor);

        if(!newColour.equals("000000")){  // 000000 is black, which represents a rest
            note.playNote();
        }

        saveChanges(buttonPressed.getId(), note, newColour);  // saves new note in song
    }

    /** once button (note) has been selected, allows user to use up and down arrows to change note */
    private void moveButton(KeyCode keyCode) {
        ArrayList<Button> notes = new ArrayList<>(Arrays.asList(q1b1, q1b2, q1b3, q1b4, q1b5, q1b6, q1b7, q1b8, q2b1, q2b2, q2b3, q2b4, q2b5, q2b6, q2b7, q2b8, q3b1, q3b2, q3b3, q3b4, q3b5, q3b6, q3b7, q3b8, q4b1, q4b2, q4b3, q4b4, q4b5, q4b6, q4b7, q4b8));
        int currentIndex = notes.indexOf(currentMovingNote);

        // moves pitch
        if (keyCode == KeyCode.UP) {
            moveUp(currentMovingNote);
        }
        else if (keyCode == KeyCode.DOWN) {
            moveDown(currentMovingNote);
        }

        // changes current moving note
        else if (keyCode == KeyCode.LEFT) {
            if(currentIndex == 0){  // if at the first note of first bar
                currentMovingNote = notes.get(notes.size() - 1);
            }
            else{
                currentMovingNote = notes.get(currentIndex - 1);
            }
            Note note = new Note(new Key(key, majorMinor), AnchorPane.getBottomAnchor(currentMovingNote));
            Button button = (Button) currentMovingNote;
            Paint colour = button.getBackground().getFills().get(0).getFill();
            String buttonColour = colour.toString().substring(2, 8);

            if(!buttonColour.equals("000000")){
                note.playNote();
            }
        }
        else if (keyCode == KeyCode.RIGHT) {
            if(currentIndex == notes.size() - 1){  // if at the last note of the last bar
                currentMovingNote = notes.get(0);
            }
            else{
                currentMovingNote = notes.get(currentIndex + 1);
            }
            Note note = new Note(new Key(key, majorMinor), AnchorPane.getBottomAnchor(currentMovingNote));
            Button button = (Button) currentMovingNote;
            Paint colour = button.getBackground().getFills().get(0).getFill();
            String buttonColour = colour.toString().substring(2, 8);

            if(!buttonColour.equals("000000")){
                note.playNote();
            }
        }
    }

    /** on button click sets currentMovingNote variable and refocuses pane */
    public void incrementNote(ActionEvent actionEvent) {
        currentMovingNote = (Node) actionEvent.getSource();
        pane.requestFocus();
    }

    /** saves note changes in song object */
    private void saveChanges(String buttonID, Note note, String colour) {
        int barNo = 0;
        String b = buttonID.substring(buttonID.length()-1);
        String n = note.getNote(colour);

        if(chordsPerPage == 2){
            if(buttonID.substring(0, 2).equals("q1")){
                barNo = (pageNo * 2) - 2;
                switch (b) {
                    case "1" -> song.getBar(barNo).setB1(n);
                    case "2" -> song.getBar(barNo).setB2(n);
                    case "3" -> song.getBar(barNo).setB3(n);
                    case "4" -> song.getBar(barNo).setB4(n);
                    case "5" -> song.getBar(barNo).setB5(n);
                    case "6" -> song.getBar(barNo).setB6(n);
                    case "7" -> song.getBar(barNo).setB7(n);
                    case "8" -> song.getBar(barNo).setB8(n);
                }
            }
            else if(buttonID.substring(0, 2).equals("q2")){
                barNo = (pageNo * 2) - 2;
                switch (b) {
                    case "1" -> song.getBar(barNo).setB9(n);
                    case "2" -> song.getBar(barNo).setB10(n);
                    case "3" -> song.getBar(barNo).setB11(n);
                    case "4" -> song.getBar(barNo).setB12(n);
                    case "5" -> song.getBar(barNo).setB13(n);
                    case "6" -> song.getBar(barNo).setB14(n);
                    case "7" -> song.getBar(barNo).setB15(n);
                    case "8" -> song.getBar(barNo).setB16(n);
                }
            }
            else if(buttonID.substring(0, 2).equals("q3")){
                barNo = (pageNo * 2) - 1;
                switch (b) {
                    case "1" -> song.getBar(barNo).setB1(n);
                    case "2" -> song.getBar(barNo).setB2(n);
                    case "3" -> song.getBar(barNo).setB3(n);
                    case "4" -> song.getBar(barNo).setB4(n);
                    case "5" -> song.getBar(barNo).setB5(n);
                    case "6" -> song.getBar(barNo).setB6(n);
                    case "7" -> song.getBar(barNo).setB7(n);
                    case "8" -> song.getBar(barNo).setB8(n);
                }
            }
            else if(buttonID.substring(0, 2).equals("q4")){
                barNo = (pageNo * 2) - 1;
                switch (b) {
                    case "1" -> song.getBar(barNo).setB9(n);
                    case "2" -> song.getBar(barNo).setB10(n);
                    case "3" -> song.getBar(barNo).setB11(n);
                    case "4" -> song.getBar(barNo).setB12(n);
                    case "5" -> song.getBar(barNo).setB13(n);
                    case "6" -> song.getBar(barNo).setB14(n);
                    case "7" -> song.getBar(barNo).setB15(n);
                    case "8" -> song.getBar(barNo).setB16(n);
                }
            }
        }
        else{
            if(buttonID.substring(0, 2).equals("q1")){
                barNo = (pageNo * 4) - 4;
            }
            else if(buttonID.substring(0, 2).equals("q2")){
                barNo = (pageNo * 4) - 3;
            }
            else if(buttonID.substring(0, 2).equals("q3")){
                barNo = (pageNo * 4) - 2;
            }
            else if(buttonID.substring(0, 2).equals("q4")){
                barNo = (pageNo * 4) - 1;
            }

            switch (b) {
                case "1" -> song.getBar(barNo).setB1(n);
                case "2" -> song.getBar(barNo).setB2(n);
                case "3" -> song.getBar(barNo).setB3(n);
                case "4" -> song.getBar(barNo).setB4(n);
                case "5" -> song.getBar(barNo).setB5(n);
                case "6" -> song.getBar(barNo).setB6(n);
                case "7" -> song.getBar(barNo).setB7(n);
                case "8" -> song.getBar(barNo).setB8(n);
            }
        }
    }

    /** allows the user to save their song in a txt file to load in later */
    public void finish(ActionEvent actionEvent) {
        saveLyrics();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Node source = (Node) actionEvent.getSource();
        Window stage = source.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        String s = key + "\n" + majorMinor + "\n" + tempo + "\n";
        if (file != null) {
            saveToFile(s + song.getSong(), file);
        }
    }

    /** writes song to file */
    private void saveToFile(String song, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.print(song);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
