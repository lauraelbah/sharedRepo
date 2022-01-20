package tests;

import ukewriter.musicClasses.Key;
import org.junit.Test;
import ukewriter.musicClasses.MelodyAssistant;

import static org.junit.jupiter.api.Assertions.*;

public class MelodyAssistantTests {
    private MelodyAssistant melodyAssistant = new MelodyAssistant();

    @Test
    public void getAnchorTest1(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "Ri";
        assertEquals(melodyAssistant.getAnchor(x, note), 89);
    }

    @Test
    public void getAnchorTest2(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "C5i";
        assertEquals(melodyAssistant.getAnchor(x, note), 89);
    }

    @Test
    public void getAnchorTest3(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "D5i";
        assertEquals(melodyAssistant.getAnchor(x, note), 96);
    }

    @Test
    public void getAnchorTest4(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "D#5i";
        assertEquals(melodyAssistant.getAnchor(x, note), 99);
    }

    @Test
    public void getAnchorTest5(){ // testing keyRelative assigned the correct value
        Key x = new Key("F", "major");
        String note = "F5i";
        assertEquals(melodyAssistant.getAnchor(x, note), 89);
    }

    @Test
    public void getAnchorTest6(){ // testing keyRelative assigned the correct value
        Key x = new Key("F", "major");
        String note = "F#5i";
        assertEquals(melodyAssistant.getAnchor(x, note), 92);
    }

    @Test
    public void getAnchorTest7(){ // testing keyRelative assigned the correct value
        Key x = new Key("F#/Gb", "major");
        String note = "F#5i";
        assertEquals(melodyAssistant.getAnchor(x, note), 138);
    }

    // black: 000000
    // yellow: fbb14e
    // red: c4523b

    @Test
    public void getColourTest1(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "C5i";
        assertEquals(melodyAssistant.getColour(x, note), "fbb14e");
    }

    @Test
    public void getColourTest2(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "G5i";
        assertEquals(melodyAssistant.getColour(x, note), "fbb14e");
    }

    @Test
    public void getColourTest3(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "G#5i";
        assertEquals(melodyAssistant.getColour(x, note), "c4523b");
    }

    @Test
    public void getColourTest4(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "Ri";
        assertEquals(melodyAssistant.getColour(x, note), "000000");
    }

    @Test
    public void getColourTest5(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "major");
        String note = "Eb5i";
        assertEquals(melodyAssistant.getColour(x, note), "c4523b");
    }

    @Test
    public void getColourTest6(){ // testing keyRelative assigned the correct value
        Key x = new Key("C", "minor");
        String note = "Eb5i";
        assertEquals(melodyAssistant.getColour(x, note), "fbb14e");
    }

}
