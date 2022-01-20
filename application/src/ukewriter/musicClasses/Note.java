package ukewriter.musicClasses;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import ukewriter.musicClasses.chordSound.chordSoundFactory;

/** represents the notes of the melody displayed as buttons in the interface */
public class Note {
    private final MelodyAssistant melodyAssistant = new MelodyAssistant();
    private final String note;
    private final Player player = new Player();

    public Note(Key key, double anchor) {
        note = melodyAssistant.getNoteName(key, anchor) + "i";
    }

    /** returns the note represented by a particular button */
    public String getNote(String colour) {
        if(!colour.equals("000000")) {
            return note;
        }
        else{
            return "Ri";
        }
    }

    /** plays the note represented by a particular button */
    public void playNote(){
        Pattern pattern = new Pattern("V0 I[VOICE] " + note);
        player.play(pattern);
    }
}
