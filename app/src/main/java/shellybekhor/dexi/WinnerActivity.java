package shellybekhor.dexi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WinnerActivity extends AppCompatActivity {

    public int fromGame = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        fromGame = getIntent().getIntExtra("fromGame",0);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.success);
        mediaPlayer.start();
    }

    public void backToMenu(View view){
        Intent intent = new Intent(WinnerActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void replay(View view){
        if(fromGame == 1){
            Intent intent = new Intent(WinnerActivity.this, GameSylsActivity.class);
            startActivity(intent);
        }
        if(fromGame == 2){
            Intent intent = new Intent(WinnerActivity.this, GameWordActivity.class);
            startActivity(intent);
        }
    }
}
