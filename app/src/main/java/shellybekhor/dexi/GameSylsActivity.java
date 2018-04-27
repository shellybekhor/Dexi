package shellybekhor.dexi;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import backend.ExternalDataHandler;
import backend.GameStatistics;

public class GameSylsActivity extends Activity {
    private static final int NUM_SYLS = 20;

    GameSylsRunner gameSylsRunner;
    Thread timer;
    int speedChange = 1000;
    int progressCounter = 0;
    View progressView;
    boolean finish = false;
    Handler myHandler;

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
    public void start() {
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
                                progressCounter = 0;
                                finish = true;
                                end();
                            }
                            gameSylsRunner.setSpeed(speedChange);
                            gameSylsRunner.progress();
                            progressCounter++;
                            int w = progressCalc(progressCounter);
                            progressView.getLayoutParams().width = w;
                        }
                    });
                    if(finish)
                    {
                        break;
                    }
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
                setSpeedChange(progressChangedValue);
            }
        });
        start();
    }


    public void toPause(View view) {
        if (!gameSylsRunner.isPause()) {
            start();
        }
        gameSylsRunner.setPause();
    }


    public void setSpeedChange(int speedChange) {
        this.speedChange = speedChange;
    }


    private int progressCalc(int cur) {
        return (cur * 800 / NUM_SYLS);
    }

    public void backToMenu(View view) {
        finish = true;
        Intent intent = new Intent(GameSylsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish = true;
        Intent intent = new Intent(GameSylsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void end() {
        Intent intent = new Intent(GameSylsActivity.this, WinnerActivity.class);
        intent.putExtra("fromGame", 1);
        startActivity(intent);
        finish();
    }
}

