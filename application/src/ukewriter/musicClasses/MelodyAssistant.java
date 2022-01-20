package ukewriter.musicClasses;

import ukewriter.musicClasses.chordSound.chordSoundFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** This class handles the melody implementation */
public class MelodyAssistant {
    private static Integer[] anchors = {40, 43, 47, 50, 54, 61, 64, 68, 71, 75, 78, 82, 89, 92, 96, 99, 103, 110, 113, 117, 120, 124, 127, 131, 138, 141, 145, 148, 152, 159, 162, 166, 169, 173, 176, 180};
    private final chordSoundFactory csf = new chordSoundFactory();

    public MelodyAssistant() {
    }

    /** returns the new anchor for the melody button going up */
    public double getNextBottomAnchor(double currentBottomAnchor, String currentColour){
        // rest point
        if(currentBottomAnchor == 89){
            if(currentColour.equals("0x000000ff")){
                return currentBottomAnchor;
            }
            else{
                return currentBottomAnchor + 3;
            }
        }
        // if interval III or VII there is no semitone between this and next note
        else if(currentBottomAnchor == 54 || currentBottomAnchor == 82 || currentBottomAnchor == 103 || currentBottomAnchor == 131 || currentBottomAnchor == 152){
            return currentBottomAnchor + 7;
        }
        // if reached top of score
        else if(currentBottomAnchor == 180){
            return 40;
        }
        // all other whole notes
        else if((currentBottomAnchor + 2) % 7 == 0){
            return currentBottomAnchor + 3;
        }
        // remaining semitones
        else{
            return currentBottomAnchor + 4;
        }
    }

    /** returns the new anchor for the melody button going down */
    public double getNextBottomAnchorDown(double currentBottomAnchor, String currentColour){
        // rest point
        if(currentBottomAnchor == 89){
            if(currentColour.equals("0x000000ff")){
                return currentBottomAnchor - 7;
            }
            else{
                return currentBottomAnchor;
            }
        }
        // if interval I or IV there is no semitone between this and prev note
        else if(currentBottomAnchor == 61 || currentBottomAnchor == 110 || currentBottomAnchor == 138 || currentBottomAnchor == 159){
            return currentBottomAnchor - 7;
        }
        // if reached bottom of score
        else if(currentBottomAnchor == 40){
            return 180;
        }
        // all other whole notes
        else if((currentBottomAnchor + 2) % 7 == 0){
            return currentBottomAnchor - 4;
        }
        // remaining semitones
        else{
            return currentBottomAnchor - 3;
        }
    }

    /** returns String with colour hexcode for button */
    public String getNextButtonColour(double newAnchor, String currentColour){
        // rest point
        if(newAnchor == 89){
            if(currentColour.equals("0x000000ff") || currentColour.equals("0xc4523bff")){
                return "0xfbb14eff";
            }
            else{
                return "0x000000ff";
            }
        }
        // if whole note
        if((newAnchor + 2) % 7 == 0){
            return "0xfbb14eff";
        }
        else{
            return "0xc4523bff";
        }
    }

    /** returns note and pitch for jfugue player */
    public String getNoteName(Key key, double anchor) {
        int index = Arrays.asList(anchors).indexOf(((int) anchor));
        int octave;
        if(index < 12){
            octave = 1;
        }
        else if(index < 24){
            octave = 2;
        }
        else{
            octave = 3;
        }
        return getNote(key, octave, index);
    }

    /** helper method for above */
    private String getNote(Key key, int octave, int anchorIndex){
        String root = key.getKeyCentre();
        anchorIndex -= (octave - 1) * 12;
        octave += 3;
        ArrayList<String> scale = key.getScale();
        String pitch = scale.get(anchorIndex);

        ArrayList<String> low = new ArrayList<>(7);
        low.add("F");
        low.add("F#/Gb");
        low.add("G");
        low.add("G#/Ab");
        low.add("A");
        low.add("A#/Bb");
        low.add("B");

        // lowers octave where needed to match cycle
        if(low.contains(root)){
            int index = low.indexOf(root);
            List list = low.subList(index, 7);
            if(list.contains(pitch)){
                octave -= 1;
            }
        }

        ArrayList<String> high = new ArrayList<>(4);
        high.add("C#/Db");
        high.add("D");
        high.add("D#/Eb");
        high.add("E");

        ArrayList<String> high1 = new ArrayList<>(4);
        high1.add("C");
        high1.add("C#/Db");
        high1.add("D");
        high1.add("D#/Eb");

        // raises octave where needed to match cycle
        if(high.contains(root)){
            int index = 3;
            if(high1.contains(root)){
                index = high1.indexOf(root);
            }
            List list = high1.subList(0, index);
            if(list.contains(pitch)){
                octave += 1;
            }
        }

        if(pitch.length() > 1){  // for notes like "C#/Db" we just need the "C#"
            pitch = pitch.substring(0, 2);
        }

        return pitch + octave;
    }

    /** returns anchor for given note in given key */
    public double getAnchor(Key key, String note){
        if(note.equals("Ri")){
            return 89.0;
        }

        String pitch = note.substring(0, note.length() - 2);
        ArrayList<String> scale = key.getScale();

        int index = 0;
        for(int i = 0; i < scale.size(); i++){
            if (scale.get(i).contains(pitch)){
                if(!scale.get(i).contains(pitch + "#") && !scale.get(i).contains(pitch + "b")){
                    index = i; // value between 0 and 11
                    break;
                }
            }
        }

        // the octave is included in the note string e.g. C5 is at octave 5
        int octave = Integer.parseInt(note.substring(note.length() - 2, note.length() - 1));

        // octaves used are 4, 5 and 6 but we would like this in the format 1, 2, 3
        octave -= 3;

        String[] low = {"F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"};
        String root = key.getKeyCentre();

        // low octave
        if(Arrays.asList(low).contains(root)){
            for(String s : low){
                if(s.contains(pitch) && !s.contains(pitch + "#")){
                    octave += 1;
                }
            }
        }

        index += ((octave-1) * 12);
        return (double) anchors[index];

    }

    /** returns button colour for given note in given key */
    public String getColour(Key key, String note){
        if(note.equals("Ri")){
            return "000000"; // black for rest
        }
        String pitch = note.substring(0, note.length() - 2);
        ArrayList<String> scale = key.getScale();
        int index = 0;
        for(int i = 0; i < scale.size(); i++){
            if (scale.get(i).contains(pitch) && !scale.get(i).contains(pitch + "#") && !scale.get(i).contains(pitch + "b")){
                index = i;
            }
        }
        if(key.majorMinor.equals("major") && (index == 0 || index == 2 || index == 4 || index == 5 || index == 7 || index == 9 || index == 11)){
            return "fbb14e";
        }
        else if (key.majorMinor.equals("major")){
            return "c4523b";
        }
        else{
            if(index == 0 || index == 2 || index == 3 || index == 5 || index == 7 || index == 9 || index == 11){
                return "fbb14e";
            }
            else {
                return "c4523b";
            }
        }
    }

}
