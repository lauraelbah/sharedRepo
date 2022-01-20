package ukewriter.musicClasses.chordSound;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class GSharpAbmajor implements chordSound {

    private final Player player = new Player();
    private final String s1 = "G#5w";
    private final String s2 = "D#5w";
    private final String s3 = "G#5w";
    private final String s4 = "C6w";

    @Override
    public void playNotes(){
        Pattern pattern = new Pattern("T300 I[Guitar] Ri G#5i D#5i G#5i C6w");
        player.play(pattern);
    }

    @Override
    public void playChord()
    {
        Pattern p1 = new Pattern("V0 I[Guitar] " + s1);
        Pattern p2 = new Pattern("V1 I[Guitar] " + s2);
        Pattern p3 = new Pattern("V2 I[Guitar] " + s3);
        Pattern p4 = new Pattern("V3 I[Guitar] " + s4);
        player.play(p1, p2, p3, p4);
    }

    public String getS1() { return s1; }

    public String getS2() { return s2; }

    public String getS3() { return s3; }

    public String getS4() { return s4; }

}
