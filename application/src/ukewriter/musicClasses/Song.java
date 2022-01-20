package ukewriter.musicClasses;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import ukewriter.levels.levelsPartB.LevelsPartB;
import ukewriter.musicClasses.chordSound.chordSound;

import java.util.ArrayList;

public class Song {
    private ArrayList<Bar> bars = new ArrayList<>();
    private int tempo;

    public Song(){
        this.tempo = LevelsPartB.tempo;
    }

    // adds a bar to the song
    public void addBar(Bar bar){
        bars.add(bar);
    }

    // updates preexisting bar in the song
    public void updateBar(int index, Bar bar){
        bars.set(index, bar);
    }

    public Bar getBar(int index){
        return bars.get(index);
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void playSong(){
        StringBuilder s1 = new StringBuilder("V0 I[Guitar]");
        StringBuilder s2 = new StringBuilder("V1 I[Guitar]");
        StringBuilder s3 = new StringBuilder("V2 I[Guitar]");
        StringBuilder s4 = new StringBuilder("V3 I[Guitar]");
        StringBuilder s5 = new StringBuilder("V4 I[Voice] ");

        for(Bar bar : bars){
            int x;
            String melody;
            if (bar.isSixteenBeats()){ // chord plays twice in level 1
                x = 2;
                melody = bar.getLevel1Melody();
            }
            else{
                x = 1;
                melody = bar.getMelody();
            }

            s5.append(melody);

            chordSound chord = bar.getChordSound();
            for(int i = 0; i < x; i++){
                s1.append(" ").append(chord.getS1());
                s2.append(" ").append(chord.getS2());
                s3.append(" ").append(chord.getS3());
                s4.append(" ").append(chord.getS4());
            }

        }

        Pattern p1 = new Pattern(s1.toString());
        Pattern p2 = new Pattern(s2.toString());
        Pattern p3 = new Pattern(s3.toString());
        Pattern p4 = new Pattern(s4.toString());
        Pattern p5 = new Pattern(s5.toString());

        p1.setTempo(tempo);
        p2.setTempo(tempo);
        p3.setTempo(tempo);
        p4.setTempo(tempo);
        p5.setTempo(tempo);

        Player player = new Player();
        player.play(p1, p2, p3, p4, p5);
    }

    /** returns string of chords, melody and lyrics to be saved in text file */
    public String getSong() {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        StringBuilder s3 = new StringBuilder();

        for(Bar bar : bars){
            int x;
            String melody;
            if (bar.isSixteenBeats()){ // chord plays twice in level 1
                x = 2;
                melody = bar.getLevel1Melody();
            }
            else{
                x = 1;
                melody = bar.getMelody();
            }
            s2.append(melody);

            for(int i = 0; i < x; i++){
                s1.append(bar.getChordName()).append(" ");
            }

            s3.append(bar.getLyrics()).append(System.getProperty("line.separator"));
        }
        s1.append(System.getProperty("line.separator")).append(s2).append(System.getProperty("line.separator")).append(s3);
        return s1.toString();
    }
}
