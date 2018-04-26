package shellybekhor.dexi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Runner runner;
    int speedChange = 0;
    private volatile boolean threadRunning = false;
    final Thread timer = new Thread(new Runnable() {
        @Override
        public void run() {
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar speedBar = (SeekBar) findViewById(R.id.speedBar);
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
                speedChange = progressChangedValue;
            }
        });

        runner = new Runner(this);
        runner.start();
        start();

    }

    public void start(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runner.isPause()) {
                    try {
                        timer.sleep(runner.getSpeed());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runner.progress();
                            runner.setSpeed(speedChange);
                        }
                    });
                }
            }
        });
        t.start();
    }

    public void toPause(View view){
        if(! runner.isPause()){
            start();
        }
        runner.setPause();
    }


}
