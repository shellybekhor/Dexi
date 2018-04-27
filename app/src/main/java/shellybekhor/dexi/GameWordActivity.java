package shellybekhor.dexi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWordActivity extends AppCompatActivity {
    GameWordRunner runner;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_words_activity);
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        runner = new GameWordRunner(this, timer);
        runner.startIteration();
        /**/
        /*Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runner.progress();
                    }
                });
            }
        }, 0, 100);*/
        /*ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                runner.progress();
            }
        }, 0, 100, TimeUnit.MILLISECONDS);*/
    }

    private void setTimer(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runner.getIteration()) {
                    try {
                        timer.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runner.progress();
                        }
                    });
                }
            }
        });
        t.start();
    }

    public void runGame() {
        while (true) {
            runner.startIteration();
            setTimer();
        }
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(GameWordActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
