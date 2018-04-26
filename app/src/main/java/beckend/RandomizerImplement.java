package beckend;
import java.util.*;
import java.util.Random;

public class RandomizerImplement implements SequenceRandomizer {

    static Map<String, String> vowels;
    static Map<String, String> consonant;
    static Map<String, String> standaloneVwels;
    static Map<String, String> endConsonant;
    static Map<String, String> hasEndConsonant;
    static List<String> vowelsKeys;
    static List<String> consonantKeys;
    static int vowelsKeysSize;
    static int consonantKeysSize;
    Random randomMaker;
    static String EMPTY = "⬚";


    public RandomizerImplement(){
        vowels = DBBuilder.getVowels();
        consonant = DBBuilder.getConsonant();
        standaloneVwels = DBBuilder.getStandaloneVowels();
        endConsonant = DBBuilder.getEndConsonant();
        hasEndConsonant = DBBuilder.getHasEndConsonant();
        vowelsKeys = new ArrayList<String>(vowels.keySet());
        consonantKeys = new ArrayList<String>(consonant.keySet());
        vowelsKeysSize = vowelsKeys.size();
        consonantKeysSize = consonantKeys.size();
        randomMaker = new Random();
    }
    @Override
    /**
     * returns a consonant or a vowel randomly in ratio 2:1
     */
    public String getNextSymbol() {
        int randomNum = randomMaker.nextInt(3);
        String randomKey;
        // return a consonant
        if(randomNum > 0){
            randomKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
            return consonant.get(randomKey);
        }
        // returns a vowel
        else{
            randomKey = vowelsKeys.get(randomMaker.nextInt(vowelsKeysSize));
            return vowels.get(randomKey)+EMPTY;
        }
    }

    private String getNotEndConsonant(){
        String phoneKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        while (endConsonant.containsKey(phoneKey)) {
            phoneKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        }
        return consonant.get(phoneKey);
    }

    private String getEndConsonant(){
        String phoneKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        if (hasEndConsonant.containsKey(phoneKey)) {
            return hasEndConsonant.get(phoneKey);
        }
        return consonant.get(phoneKey);
    }

    private String makePhone(boolean endWord){
        String phone = getNotEndConsonant();
        phone.concat(vowels.get(vowelsKeys.get(randomMaker.nextInt(vowelsKeysSize))));
        if (randomMaker.nextBoolean()) {
            if (endWord)
                phone.concat(getNotEndConsonant());
            else
                phone.concat(getEndConsonant());
        }
        return phone;
    }

    @Override
    public String getWord() {
        String word = makePhone(false);
        if (randomMaker.nextBoolean()) {
            word.concat(makePhone(false));
        }
        word.concat(makePhone(true));
        return word;
    }
}
