package backend;

import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class GameStatistics implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private long lastPlayed;
    private int counter = 0;

    public GameStatistics(String name) {
        this.name = name;
    }

    private void setPlayedToday() {
        Date today = Calendar.getInstance().getTime();
        lastPlayed = today.getTime();
    }

    private void countUp() {
        counter++;
    }

    public boolean playedToday() {
        return DateUtils.isToday(lastPlayed);
    }

    public void statUp() {
        countUp();
        setPlayedToday();
    }

    public int numPlayed() {
        return counter;
    }
}
