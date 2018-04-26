package beckend;
import java.util.*;

public class RandomizerImplement implements SequenceRandomizer {
    static Map<String, String> vowels;
    static Map<String, String> consonant;
    static List<String> vowelsKeys;
    static List<String> consonantKeys;

    public RandomizerImplement(){
        vowels = DBBuilder.getVowels();
        consonant = DBBuilder.getConsonant();
        vowelsKeys = new ArrayList<String>(vowels.keySet());
        consonantKeys = new ArrayList<String>(consonant.keySet());
    }
    @Override
    public String getNextSymbol() {

        return "בּ";
    }
}
