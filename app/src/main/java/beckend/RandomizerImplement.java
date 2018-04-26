package beckend;
import java.util.*;
import java.util.Random;

public class RandomizerImplement implements SequenceRandomizer {

    static Map<String, String> vowels;
    static Map<String, String> consonant;
    static List<String> vowelsKeys;
    static List<String> consonantKeys;
    static int vowelsKeysSize;
    static int consonantKeysSize;
    static String EMPTY = "⬚";


    public RandomizerImplement(){
        vowels = DBBuilder.getVowels();
        consonant = DBBuilder.getConsonant();
        vowelsKeys = new ArrayList<String>(vowels.keySet());
        consonantKeys = new ArrayList<String>(consonant.keySet());
        vowelsKeysSize = vowelsKeys.size();
        consonantKeysSize = consonantKeys.size();
    }
    @Override
    /**
     * returns a consonant or a vowel randomly in ratio 2:1
     */
    public String getNextSymbol() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        String randomKey;
        // return a consonant
        if(randomNum > 0){
            randomKey = consonantKeys.get(rand.nextInt(consonantKeysSize));
            return consonant.get(randomKey);
        }
        // returns a vowel
        else{
            randomKey = vowelsKeys.get(rand.nextInt(vowelsKeysSize));
            return vowels.get(randomKey)+EMPTY;
        }
    }
}
