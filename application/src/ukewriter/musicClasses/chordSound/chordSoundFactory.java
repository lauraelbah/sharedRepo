package ukewriter.musicClasses.chordSound;

public class chordSoundFactory {
    public static chordSound getChordSound(String chordName){
        if(chordName.equals("Adim")){
            return new Adim();
        }
        else if(chordName.equals("Amajor")){
            return new Amajor();
        }
        else if(chordName.equals("Aminor")){
            return new Aminor();
        }
        else if(chordName.equals("ASharpBbdim")){
            return new ASharpBbdim();
        }
        else if(chordName.equals("ASharpBbmajor")){
            return new ASharpBbmajor();
        }
        else if(chordName.equals("ASharpBbminor")){
            return new ASharpBbminor();
        }
        else if(chordName.equals("Bdim")){
            return new Bdim();
        }
        else if(chordName.equals("Bmajor")){
            return new Bmajor();
        }
        else if(chordName.equals("Bminor")){
            return new Bminor();
        }
        else if(chordName.equals("Cdim")){
            return new Cdim();
        }
        else if(chordName.equals("Cmajor")){
            return new Cmajor();
        }
        else if(chordName.equals("Cminor")){
            return new Cminor();
        }
        else if(chordName.equals("CSharpDbdim")){
            return new CSharpDbdim();
        }
        else if(chordName.equals("CSharpDbmajor")){
            return new CSharpDbmajor();
        }
        else if(chordName.equals("CSharpDbminor")){
            return new CSharpDbminor();
        }
        else if(chordName.equals("Ddim")){
            return new Ddim();
        }
        else if(chordName.equals("Dmajor")){
            return new Dmajor();
        }
        else if(chordName.equals("Dminor")){
            return new Dminor();
        }
        else if(chordName.equals("DSharpEbdim")){
            return new DSharpEbdim();
        }
        else if(chordName.equals("DSharpEbmajor")){
            return new DSharpEbmajor();
        }
        else if(chordName.equals("DSharpEbminor")){
            return new DSharpEbminor();
        }
        else if(chordName.equals("Edim")){
            return new Edim();
        }
        else if(chordName.equals("Emajor")){
            return new Emajor();
        }
        else if(chordName.equals("Eminor")){
            return new Eminor();
        }
        else if(chordName.equals("Fdim")){
            return new Fdim();
        }
        else if(chordName.equals("Fmajor")){
            return new Fmajor();
        }
        else if(chordName.equals("Fminor")){
            return new Fminor();
        }
        else if(chordName.equals("FSharpGbdim")){
            return new FSharpGbdim();
        }
        else if(chordName.equals("FSharpGbmajor")){
            return new FSharpGbmajor();
        }
        else if(chordName.equals("FSharpGbminor")){
            return new FSharpGbminor();
        }
        else if(chordName.equals("Gdim")){
            return new Gdim();
        }
        else if(chordName.equals("Gmajor")){
            return new Gmajor();
        }
        else if(chordName.equals("Gminor")){
            return new Gminor();
        }
        else if(chordName.equals("GSharpAbdim")){
            return new GSharpAbdim();
        }
        else if(chordName.equals("GSharpAbmajor")){
            return new GSharpAbmajor();
        }
        else if(chordName.equals("GSharpAbminor")){
            return new GSharpAbminor();
        }
        return new Cmajor();
    }
}
