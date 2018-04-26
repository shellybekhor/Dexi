package beckend;
import java.util.*;
import java.util.Random;

public class RandomizerImplement implements SequenceRandomizer {

    static Map<String, String> vowels;
    static Map<String, String> consonant;
    static Map<String, String> standaloneVowels;
    static Map<String, String> endConsonant;
    static Map<String, String> hasEndConsonant;
    static List<String> vowelsKeys;
    static List<String> consonantKeys;
    static int vowelsKeysSize;
    static int consonantKeysSize;
    Random randomMaker;
    static String EMPTY = "⬚";
    static String SHVA = "ְ";


    public RandomizerImplement(){
        vowels = DBBuilder.getVowels();
        consonant = DBBuilder.getConsonant();
        standaloneVowels = DBBuilder.getStandaloneVowels();
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
     * returns a consonant or a vowel randomly in probability 2:1
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
            if(standaloneVowels.containsKey(randomKey))
                return vowels.get(randomKey);
            return vowels.get(randomKey)+EMPTY;
        }
    }
    // returns only regular consonants (not end of word consonant)
    private String getNotEndConsonant(String notToRepeat){
        String letterKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        // looking for a regular letter (not terminal letter) that not just have been written
        while (consonant.get(letterKey)==notToRepeat||endConsonant.containsKey(letterKey)) {
            letterKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        }
        return consonant.get(letterKey);
    }

    private String getEndConsonant(String notToRepeat){
        String letterKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        //if the letter has an end-of-word version, return that
        if (hasEndConsonant.containsKey(letterKey)) {
            return hasEndConsonant.get(letterKey);
        }
        return consonant.get(letterKey);
    }

    private String makeSyllable(boolean endWord, boolean consonantAtEnd){
        String syllable = getNotEndConsonant("");
        String notToRepeat = syllable;
        syllable = syllable.concat(vowels.get(vowelsKeys.get(randomMaker.nextInt(vowelsKeysSize))));
        if (endWord)
            syllable = syllable.concat(getEndConsonant(notToRepeat));
        else if (consonantAtEnd) {
            syllable = syllable.concat(getNotEndConsonant(notToRepeat)+SHVA);
        }
        return syllable;
    }

    @Override
    public String getWord() {
        boolean incluseShva = randomMaker.nextBoolean();
        String word;
        if (incluseShva){
            word = makeSyllable(false, true);
        } else {
            word = makeSyllable(false, false);
            word = word.concat(makeSyllable(false, false));
        }
        word = word.concat(makeSyllable(true, true));
        return word;
    }
}
