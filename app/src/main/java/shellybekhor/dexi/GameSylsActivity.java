package shellybekhor.dexi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class GameSylsActivity extends AppCompatActivity {
    GameSylsRunner gameSylsRunner;
    Thread timer;
    int speedChange = 0;

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
        runGameSyls();
    }

    public void start(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (gameSylsRunner.isPause()) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gameSylsRunner.progress();
                            gameSylsRunner.setSpeed(speedChange);
                        }
                    });

                    try {
                        timer.sleep(gameSylsRunner.getSpeed());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public void runGameSyls() {
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


}

