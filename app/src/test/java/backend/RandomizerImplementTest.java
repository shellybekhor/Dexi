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
    }

    @Test
    public void getWord() {
        Word s;
        for (int i = 0; i < 10; i++) {

            s = t.getWord();

            System.out.println(s);
        }
    }
}