package shellybekhor.dexi;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

import backend.RandomizerImplement;
import backend.Syllable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void moveToGameSyls(View view) {
        Intent intent = new Intent(MainActivity.this, GameSylsActivity.class);
        startActivity(intent);
    }

    public void moveToGameWords(View view) {
        Intent intent = new Intent(MainActivity.this, GameWordActivity.class);
        startActivity(intent);
    }



    public void audioPlayer(View view){
        //set up MediaPlayer
        RandomizerImplement r = new RandomizerImplement();
        Syllable s = new Syllable("ALEFxHOLAM","");
        s.play(MainActivity.this);
        int x;
    }

}
