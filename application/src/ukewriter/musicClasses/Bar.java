package ukewriter.musicClasses;

import ukewriter.musicClasses.chordSound.chordSound;
import ukewriter.musicClasses.chordSound.chordSoundFactory;

/** represents one bar of music */
public class Bar {
    private String lyrics = "";
    private String chordName;
    private chordSound chordSound;
    private chordSoundFactory csf = new chordSoundFactory();
    private boolean sixteenBeats;

    // each bar split into eighth notes
    private String b1 = "Ri";
    private String b2 = "Ri";
    private String b3 = "Ri";
    private String b4 = "Ri";
    private String b5 = "Ri";
    private String b6 = "Ri";
    private String b7 = "Ri";
    private String b8 = "Ri";

    // for level1 where bars are 2x the size of regular bar
    private String b9 = "Ri"; // set default as rest
    private String b10 = "Ri";
    private String b11 = "Ri";
    private String b12 = "Ri";
    private String b13 = "Ri";
    private String b14 = "Ri";
    private String b15 = "Ri";
    private String b16 = "Ri";

    public Bar(String chord, String lyrics, Boolean sixteenBeats) {
        this.chordSound = csf.getChordSound(chord);
        this.setChordName(chord);
        this.setLyrics(lyrics);
        this.setSixteenBeats(sixteenBeats);
    }

    public Bar(String chord, String lyrics) {
        this.chordSound = csf.getChordSound(chord);
        this.setChordName(chord);
        this.setLyrics(lyrics);
        this.setSixteenBeats(false);
    }

    public void setSixteenBeats(boolean sixteenBeats) {
        this.sixteenBeats = sixteenBeats;
    }

    public void setChordName(String chordName) {
        this.chordName = chordName;
        this.chordSound = csf.getChordSound(chordName);
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getMelody(){
        return b1 + " " + b2 + " " + b3 + " " + b4 + " " + b5 + " " + b6 + " " + b7 + " " + b8 + " ";
    }

    public String getLevel1Melody(){
        return b1 + " " + b2 + " " + b3 + " " + b4 + " " + b5 + " " + b6 + " " + b7 + " " + b8 + " " + b9 + " " + b10 + " " + b11 + " " + b12 + " " + b13 + " " + b14 + " " + b15 + " " + b16 + " ";
    }

    public chordSound getChordSound() {
        return chordSound;
    }

    public String getChordName() {
        return chordName;
    }

    public String getLyrics() {
        return lyrics;
    }

    public boolean isSixteenBeats() {
        return sixteenBeats;
    }

    public String getB1() {
        return b1;
    }

    public String getB2() {
        return b2;
    }

    public String getB3() {
        return b3;
    }

    public String getB4() {
        return b4;
    }

    public String getB5() {
        return b5;
    }

    public String getB6() {
        return b6;
    }

    public String getB7() {
        return b7;
    }

    public String getB8() {
        return b8;
    }

    public String getB9() {
        return b9;
    }

    public String getB10() {
        return b10;
    }

    public String getB11() {
        return b11;
    }

    public String getB12() {
        return b12;
    }

    public String getB13() {
        return b13;
    }

    public String getB14() {
        return b14;
    }

    public String getB15() {
        return b15;
    }

    public String getB16() {
        return b16;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public void setB4(String b4) {
        this.b4 = b4;
    }

    public void setB5(String b5) {
        this.b5 = b5;
    }

    public void setB6(String b6) {
        this.b6 = b6;
    }

    public void setB7(String b7) {
        this.b7 = b7;
    }

    public void setB8(String b8) {
        this.b8 = b8;
    }

    public void setB9(String b9) {
        this.b9 = b9;
    }

    public void setB10(String b10) {
        this.b10 = b10;
    }

    public void setB11(String b11) {
        this.b11 = b11;
    }

    public void setB12(String b12) {
        this.b12 = b12;
    }

    public void setB13(String b13) {
        this.b13 = b13;
    }

    public void setB14(String b14) {
        this.b14 = b14;
    }

    public void setB15(String b15) {
        this.b15 = b15;
    }

    public void setB16(String b16) {
        this.b16 = b16;
    }
}
