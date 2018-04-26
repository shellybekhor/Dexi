package backend;
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
    static final String EMPTY = "⬚";
    static final String SHVA = "ְ";
    private static final String SEPERATOR = "x";


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
        while (consonant.get(letterKey).equals(notToRepeat)||endConsonant.containsKey(letterKey)) {
            letterKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        }
        return letterKey;
    }

    private String getEndConsonant(String notToRepeat){
        String letterKey = consonantKeys.get(randomMaker.nextInt(consonantKeysSize));
        //if the letter has an end-of-word version, return that
        if (hasEndConsonant.containsKey(letterKey)) {
            return letterKey+"_SOFIT";
        }
        return letterKey;
    }
    //private method that makes syllable to compose a word
    private Syllable makeSyllable(boolean endWord, boolean consonantAtEnd){
        //get a letter
        String key = getNotEndConsonant("");
        String rep = consonant.get(key);
        String notToRepeat = rep;
        key = key.concat(SEPERATOR);
        String tmpKey = vowelsKeys.get(randomMaker.nextInt(vowelsKeysSize));
        key += tmpKey;
        rep += vowels.get(tmpKey);
        tmpKey = "";
        //add a last consonant if we are at the end of the word
        if (endWord) {
            tmpKey = getEndConsonant(notToRepeat);
            rep+=consonant.get(tmpKey);
            key += SEPERATOR+tmpKey;
        }
        //add a consonant with SHVA if we are the middle of the word and we want to
        else if (consonantAtEnd) {
            tmpKey = getNotEndConsonant(notToRepeat);
            rep+=consonant.get(tmpKey) + SHVA;
            key += SEPERATOR+tmpKey;
        }
        return new Syllable(key,rep);
    }

    @Override
    //Create a random word
    public Word getWord(){
        List<Syllable> syllables = new ArrayList<>();
        //the first syllable with consonant at the end
        if (randomMaker.nextBoolean()){
            syllables.add(makeSyllable(false, true));
        }
        //the first syllable without a consonant at the end
        else {
            syllables.add(makeSyllable(false, false));
        }
        //the last syllable in the word
        syllables.add(makeSyllable(true, false));
        return new Word(syllables);
    }
    //Create a random syllable
    @Override
    public Syllable getSyllable() {
        // add a letter
        String key = getNotEndConsonant("");
        String rep = consonant.get(key);
        key = key.concat(SEPERATOR);
        // add a vowel to the letter
        String tmpKey = vowelsKeys.get(randomMaker.nextInt(vowelsKeysSize));
        key += tmpKey;
        rep += vowels.get(tmpKey);
        tmpKey = "";
        // add a terminal letter (sometimes happens)
        if (randomMaker.nextBoolean()) {
            tmpKey = getEndConsonant("");
            rep+=consonant.get(tmpKey);
        }
        key += SEPERATOR+tmpKey;
        return new Syllable(key,rep);
    }

}
