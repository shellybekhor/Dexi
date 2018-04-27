package shellybekhor.dexi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import backend.*;

public class GameWordActivity extends AppCompatActivity {
    //GameWordRunner runner;
    Thread timer;
    private TextView showWord;
    private ImageView readWord;
    private ImageView success;
    private ImageView fail;
    private ImageView prog;
    private SequenceRandomizer randomizer;
    Word newWord;
    int width = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_words_activity);
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        showWord = findViewById(R.id.showWord);
        readWord = findViewById(R.id.readWord);
        success = findViewById(R.id.success);
        fail = findViewById(R.id.fail);
        prog = findViewById(R.id.progress);
        randomizer = new RandomizerImplement();
        startIteration();
    }

    public void play(View view){
        if (newWord != null) newWord.play(GameWordActivity.this);
    }

    public void startIteration() {
        newWord = randomizer.getWord();
        showWord.setText(newWord.toString());
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
    }

    private void endIteration(boolean isSuccess){
        if (isSuccess) {
            if(prog.getLayoutParams().width >= 830){
                end();
                return;
            }
            prog.getLayoutParams().width +=20;
        } else {
            if(prog.getLayoutParams().width > 0) {
                prog.getLayoutParams().width -= 20;
            }
        }
        startIteration();
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(GameWordActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void end(){
        Intent intent = new Intent(GameWordActivity.this, WinnerActivity.class);
        intent.putExtra("fromGame", 2);
        startActivity(intent);
        finish();
    }
}
