package shellybekhor.dexi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class GameSylsActivity extends Activity {
    private static final int NUM_SYLS = 100;

    GameSylsRunner gameSylsRunner;
    Thread timer;
    int speedChange = 1000;
    int progressCounter = 0;
    View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_syls_activity);
        gameSylsRunner = new GameSylsRunner(this);
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        progressView = findViewById(R.id.progress);
        runGameSyls();
    }

    public void start(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (gameSylsRunner.isPause()) {
                    try {
                        timer.sleep(gameSylsRunner.getSpeed());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(progressCounter == NUM_SYLS){
                                backToMenu(findViewById(R.id.stopButton));
                            }
                            gameSylsRunner.setSpeed(speedChange);
                            gameSylsRunner.progress();
                            progressCounter++;
                            int w = progressCalc(progressCounter);
                            progressView.getLayoutParams().width = w;
                        }
                    });
                }
            }
        });
        t.start();
    }

    public void runGameSyls() {
        gameSylsRunner.start();
        SeekBar speedBar = findViewById(R.id.speedBar);
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println(progressChangedValue);
                setSpeedChange(progressChangedValue);
            }
        });
        start();
    }


    public void toPause(View view){
        if(! gameSylsRunner.isPause()){
            start();
        }
        gameSylsRunner.setPause();
    }


    public void setSpeedChange(int speedChange) {
        this.speedChange = speedChange;
    }


    private int progressCalc(int cur){
        return 10 + (cur * 600 / NUM_SYLS);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(GameSylsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

