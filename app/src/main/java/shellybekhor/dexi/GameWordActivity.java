package shellybekhor.dexi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class GameWordActivity extends AppCompatActivity {
    GameWordRunner runner;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_word);
        runner = new GameWordRunner(this);
        runner.runIteration();
    }


    public void backToMenu(View view) {
        Intent intent = new Intent(GameWordActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
