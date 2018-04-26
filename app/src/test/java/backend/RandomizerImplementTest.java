package backend;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RandomizerImplementTest {
    RandomizerImplement t = new RandomizerImplement();
    @Test
    public void getNextSymbol() {
        System.out.println(t.getNextSymbol());
    }

    @Test
    public void makeSyllable() {
        Syllable s;
        for (int i = 0; i < 100; i++) {

            s= t.makeSyllable(false,false);
                System.out.println(s.getKey());
        }
    }

    @Test
    public void getWord() {
        Word s;
        for (int i = 0; i < 10000; i++) {

            s= t.getWord();
            if (s.toString().contains("null"))
                System.out.println(s);
        }
    }
}