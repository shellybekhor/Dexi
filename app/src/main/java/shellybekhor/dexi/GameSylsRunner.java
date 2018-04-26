package shellybekhor.dexi;

import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import backend.RandomizerImplement;
import backend.SequenceRandomizer;

public class GameSylsRunner {
    private static final int DEFAULT_SPEED = 1000;
    private static final int MAX_SPEED = 2000;
    private static final int MIN_SPEED = 500;

    private TextView prev;
    private TextView cur;
    private TextView next;
    private SequenceRandomizer randomizer;
    private boolean pause = false;
    private int speed = DEFAULT_SPEED;

    public GameSylsRunner(AppCompatActivity app){
        prev = app.findViewById(R.id.prevConst);
        cur = app.findViewById(R.id.curConst);
        next = app.findViewById(R.id.nextConst);
        randomizer = new RandomizerImplement();
    }

    public void setText(TextView view, String text){
        view.setText(text);
    }

    public void start(){
        setText(this.prev, " ");
        setText(this.cur, randomizer.getNextSymbol());
        setText(this.next, randomizer.getNextSymbol());
    }

    public void progress(){
        System.out.println("progress");
        String prev = cur.getText().toString();
        String cur = next.getText().toString();
        String next = randomizer.getNextSymbol();
        setText(this.prev, prev);
        setText(this.cur, cur);
        setText(this.next, next);
    }

    public void stop(){

    }

    public boolean isPause() {
        return pause;
    }

    public void setPause() {
        if (pause){
            pause = false;
            return;
        }
        pause = true;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
