package shellybekhor.dexi;

import android.view.View;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import backend.*;

public class GameWordRunner {
    private TextView showWord;
    private ImageView readWord;
    private ImageView success;
    private ImageView fail;
    private ImageView timerBar;
    private RelativeLayout timeUp;
    private SequenceRandomizer randomizer;
    Thread timerThread;
    AppCompatActivity app;
    int iteration = 0;
    int width = 10;
    boolean wait = true;

    public GameWordRunner(AppCompatActivity app, Thread timerThread){
        showWord = app.findViewById(R.id.showWord);
        readWord = app.findViewById(R.id.readWord);
        success = app.findViewById(R.id.success);
        fail = app.findViewById(R.id.fail);
        timerBar = app.findViewById(R.id.timerBar);
        timeUp = app.findViewById(R.id.timeUp);
        randomizer = new RandomizerImplement();
        this.timerThread = timerThread;
        this.app = app;

    }

    public void startIteration() {
        wait = true;
        final Word newWord = randomizer.getWord();
        readWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newWord.play(app);
            }
        });
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endIteration(true);
            }
        });
        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endIteration(false);
            }
        });
        showWord.setText(newWord.toString());
//        timeUp.setVisibility(View.VISIBLE);
//        timerBar.setVisibility(View.GONE);
    }

    private void setTimer(){
        try {
            Thread.sleep(1000);
            timerBar.setVisibility(View.GONE);
            timeUp.setVisibility(View.VISIBLE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void endIteration(boolean isSuccess){
//        timeUp.setVisibility(View.GONE);
        if (isSuccess) {

        } else {

        }
        startIteration();
    }

    public boolean waitToPlayer(){
        return wait;
    }

    public boolean getIteration(){
        return iteration < 10;
    }

    public void progress(){
        iteration++;
        if (iteration <= 10){
            width += 50;
            System.out.println(width);
            timerBar.getLayoutParams().width = width;
        }
        else {
            timerBar.setVisibility(View.GONE);
            timeUp.setVisibility(View.VISIBLE);
            iteration = 0;
        }
    }
}
