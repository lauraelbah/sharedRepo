package tests;

import ukewriter.musicClasses.Key;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class KeyTests {
    @Test
    public void keyRelativeTest1(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        assertEquals(x.getKeyRelative(), 0);
    }

    @Test
    public void keyRelativeTest2(){ // testing keyRelative assigned the correct value
        Key x = new Key("F#/Gb", "major");
        assertEquals(x.getKeyRelative(), 6);
    }

    @Test
    public void keyRelativeTest3(){ // testing keyRelative assigned the correct value
        Key x = new Key("A", "major");
        assertEquals(x.getKeyRelative(), 9);
    }

    @Test
    public void keyRelativeTest4(){ // testing keyRelative assigned the correct value
        Key x = new Key("D#/Eb", "minor");
        assertEquals(x.getKeyRelative(), 3);
    }

    @Test
    public void keyRelativeTest5(){ // testing keyRelative assigned the correct value
        Key x = new Key("F", "minor");
        assertEquals(x.getKeyRelative(), 5);
    }

    @Test
    public void getScaleTest1(){ // testing getScale function
        Key x = new Key("C", "minor");
        ArrayList<String> scale = new ArrayList<>(Arrays.asList("C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"));
        assertEquals(scale, x.getScale());
    }

    @Test
    public void getScaleTest2(){ // testing getScale function
        Key x = new Key("F", "minor");
        ArrayList<String> scale = new ArrayList<>(Arrays.asList("F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E"));
        assertEquals(scale, x.getScale());
    }

    @Test
    public void getScaleTest3(){ // testing getScale function
        Key x = new Key("A#/Bb", "minor");
        ArrayList<String> scale = new ArrayList<>(Arrays.asList("A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A"));
        assertEquals(scale, x.getScale());
    }

    @Test
    public void getScaleTest4(){ // testing getScale function
        Key x = new Key("C#/Db", "major");
        ArrayList<String> scale = new ArrayList<>(Arrays.asList("C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C"));
        assertEquals(scale, x.getScale());
    }

    @Test
    public void getScaleTest5(){ // testing getScale function
        Key x = new Key("A", "major");
        ArrayList<String> scale = new ArrayList<>(Arrays.asList("A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab"));
        assertEquals(scale, x.getScale());
    }

    @Test
    public void getChordTest1(){ // testing getChord function
        Key x = new Key("F", "major");
        assertEquals(x.getChord("V"), "Cmajor");
    }

    @Test
    public void getChordTest2(){ // testing getChord function
        Key x = new Key("C", "major");
        assertEquals(x.getChord("VII"), "Bdim");
    }

    @Test
    public void getChordTest3(){ // testing getChord function
        Key x = new Key("A", "minor");
        assertEquals(x.getChord("II"), "Bdim");
    }

    @Test
    public void getChordTest4(){ // testing getChord function
        Key x = new Key("D#/Eb", "major");
        assertEquals(x.getChord("III"), "Gminor");
    }

    @Test
    public void getChordTest5(){ // testing getChord function
        Key x = new Key("A", "major");
        assertEquals(x.getChord("III"), "C#/Dbminor");
    }

    @Test
    public void getIntervalTest1(){ // testing getInterval function
        Key x = new Key("C", "major");
        assertEquals(x.getInterval("Cmajor"), "I");
    }

    @Test
    public void getIntervalTest2(){ // testing getInterval function
        Key x = new Key("C", "major");
        assertEquals(x.getInterval("Bdim"), "VII");
    }

    @Test
    public void getIntervalTest3(){ // testing getInterval function
        Key x = new Key("A", "minor");
        assertEquals(x.getInterval("Bminor"), "II");
    }

    @Test
    public void getIntervalTest4(){ // testing getInterval function
        Key x = new Key("A#/Bb", "major");
        assertEquals(x.getInterval("Bmajor"), "ISharp");
    }

    @Test
    public void getIntervalTest5(){ // testing getInterval function
        Key x = new Key("F#/Gb", "major");
        assertEquals(x.getInterval("Fdim"), "VII");
    }
}
