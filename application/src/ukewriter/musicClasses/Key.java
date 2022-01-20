package ukewriter.musicClasses;

import java.util.ArrayList;

/** This Class handles the relations between chords and key centres */

public class Key {
    public final String keyCentre;
    private int keyRelative; // semitones above C as this is where the cycle below starts
    public String majorMinor;

    public final String[] cycle1 = {"C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"};
    private final String[] cycle2 = {"I", "ISharp", "II", "IISharp", "III", "IV", "IVSharp", "V", "VSharp", "VI", "VISharp", "VII"};

    public Key(String keyCentre, String majorMinor) {
        this.keyCentre = keyCentre;
        this.majorMinor = majorMinor;
        for(int i = 0; i < cycle1.length; i++){
            if(cycle1[i].equals(keyCentre)){
                keyRelative = i;
            }
        }

    }

    public String getKeyCentre() {
        return keyCentre;
    }

    public int getKeyRelative() {
        return keyRelative;
    }

    /** Returns list of notes starting from key centre */
    public ArrayList<String> getScale(){
        ArrayList<String> scale = new ArrayList<>();
        for (int i = 0; i < cycle1.length; i++){
            int x = i + keyRelative;
            if (x > cycle1.length - 1){
                x -= cycle1.length;
            }
            scale.add(cycle1[x]);
        }
        return scale;
    }

    /** Returns the chord at the specified interval in this key */
    public String getChord(String interval){
        int x = 0;
        String majMinDim = "";

        // gets position of interval in cycle2 list
        for(int i = 0; i < cycle2.length; i++){
            if(cycle2[i].equals(interval)){
                x = i;
            }
        }

        // gets the interval relative to key rather than C
        x += keyRelative;

        // ensures value within bounds
        if (x > cycle1.length - 1){
            x -= 12;
        }

        // builds chord names
        if (majorMinor.equals("major")){
            if(interval.equals("I") || interval.equals("IV") || interval.equals("V")){
                majMinDim = "major";
            }
            else if(interval.equals("VII")){
                majMinDim = "dim";
            }
            else{
                majMinDim = "minor";
            }
        }
        else{
            if(interval.equals("IISharp") || interval.equals("V") || interval.equals("VSharp") || interval.equals("VISharp")){
                majMinDim = "major";
            }
            else if(interval.equals("II")){
                majMinDim = "dim";
            }
            else{
                majMinDim = "minor";
            }
        }

        // returns chord root + chord type
        return cycle1[x] + majMinDim;
    }

    /** Returns the interval of the chord in this key */
    public String getInterval(String chord){
        int x = 0;

        // get just root of chord
        if(chord.contains("dim")){
            chord = chord.substring(0, chord.length() - 3);
        }
        else{
            chord = chord.substring(0, chord.length() - 5);
        }
        chord = chord.replaceAll("Sharp", "#/");

        // gets position of root in cycle1 list
        for(int i = 0; i < cycle1.length; i++){
            if(cycle1[i].equals(chord)){
                x = i;
            }
        }

        // gets the interval relative to key rather than C
        x -= keyRelative;

        // ensures value within bounds
        if (x < 0){
            x += 12;
        }

        return cycle2[x];
    }

    /** switches major and minor intervals where relevant otherwise returns original string */
    public String switchInterval(String interval){
        return switch (interval) {
            case "IISharp" -> "III";
            case "III" -> "IISharp";
            case "VSharp" -> "VI";
            case "VI" -> "VSharp";
            case "VISharp" -> "VII";
            case "VII" -> "VISharp";
            default -> interval;
        };
    }

}
