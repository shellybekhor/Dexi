package backend;

import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class GameStatistics implements Serializable {
    private static final long serialVersionUID = 1L;
    private long lastPlayed;
    private int counter = 0;

    public void setPlayedToday() {
        Date today = Calendar.getInstance().getTime();
        lastPlayed = today.getTime();
    }

    public boolean playedToday() {
        return DateUtils.isToday(lastPlayed);
    }

}
