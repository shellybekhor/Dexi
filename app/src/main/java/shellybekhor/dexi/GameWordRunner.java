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
    private ImageView timer;
    private RelativeLayout timeUp;
    private SequenceRandomizer randomizer;

    public GameWordRunner(AppCompatActivity app){
        showWord = app.findViewById(R.id.showWord);
        readWord = app.findViewById(R.id.readWord);
        success = app.findViewById(R.id.success);
        fail = app.findViewById(R.id.fail);
        timer = app.findViewById(R.id.timer);
        timeUp = app.findViewById(R.id.timeUp);
        randomizer = new RandomizerImplement();
    }

    public void runIteration() {
        Word newWord = randomizer.getWord();
        readWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo - add word.play method when done
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
        setTimer();
        timeUp.setVisibility(View.VISIBLE);
    }

    private void endIteration(boolean isSuccess){
        timeUp.setVisibility(View.GONE);
        if (isSuccess) {

        } else {

        }
        runIteration();
    }

    private void setTimer(){

    }
}
