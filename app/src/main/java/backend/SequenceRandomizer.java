package backend;

public interface SequenceRandomizer {
    //returns a symbol of a vowel or a consonant
    String getNextSymbol();
    //returns a word
    Word getWord();
}
