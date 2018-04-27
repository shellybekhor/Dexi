package backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Statistics implements Serializable {
    public static final String SYMBOL_GAME = "SYMBOL_GAME";
    public static final String WORD_GAME = "WORD_GAME";
    private Map<String, GameStatistics> statistics = new HashMap<>();

    public Statistics() {
        statistics.put(SYMBOL_GAME, new GameStatistics(SYMBOL_GAME));
        statistics.put(WORD_GAME, new GameStatistics(WORD_GAME));
    }

    public void statUp(String game) {
        statistics.get(game).statUp();
    }
}
