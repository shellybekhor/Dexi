package shellybekhor.dexi;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import backend.ExternalDataHandler;
import backend.RandomizerImplement;
import backend.Statistics;
import backend.Syllable;
import backend.Word;

public class MainActivity extends Activity {

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}