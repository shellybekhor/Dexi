package beckend;
import java.util.*;

public class RandomizerImplement implements SequenceRandomizer {
    HashMap<String, String> vowels = new HashMap<>();

    @Override
    public String getNextSymbol() {
        return "e";
    }
}
