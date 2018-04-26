package beckend;

import java.util.List;

public class Word {
    List<Syllable> syllables;
    public Word(List<Syllable> syllables){
        this.syllables = syllables;
    }
    public String toString(){
        String str = "";
        for(Syllable s: syllables){
            str = str.concat(s.toString());
        }
        return str;
    }
}
