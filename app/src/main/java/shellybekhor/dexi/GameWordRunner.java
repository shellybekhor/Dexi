package shellybekhor.dexi;

import android.view.View;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;

import backend.*;

public class GameWordRunner {
    private TextView showWord;
    private Button readWord;
    private Button success;
    private Button fail;
    private ImageView timerBar;
    private RelativeLayout timeUp;
    private SequenceRandomizer randomizer;
    Thread timerThread;
    AppCompatActivity app;
    int iteration = 0;
    int width = 10;

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
        timerBar.setVisibility(View.VISIBLE);
    }

    public void progress(){
        iteration++;
        if (iteration <= 10){
            width += 50;
            System.out.println(width);
            timerBar.getLayoutParams().width = width;
        }
        else {
            timerBar.getLayoutParams().width = 300;
            timeUp.setVisibility(View.VISIBLE);
            iteration = 0;
        }
    }

    private void endIteration(boolean isSuccess){
        timeUp.setVisibility(View.GONE);
        if (isSuccess) {

        } else {

        }
        startIteration();
    }

    public boolean getIteration(){
        return iteration < 10;
    }
}
