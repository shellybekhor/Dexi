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

import java.util.ArrayList;
import java.util.List;

import backend.ExternalDataHandler;
import backend.RandomizerImplement;
import backend.Statistics;
import backend.Syllable;
import backend.Word;

public class MainActivity extends Activity {
    ExternalDataHandler handler;
    Statistics statistics;

    @Override
    protected void onStart() {
        super.onStart();
        handler = new ExternalDataHandler(MainActivity.this);
        handler.readExternalData();
        statistics = handler.getStatisticsObject();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        handler.writeExternalData();
    }

    public void moveToGameSyls(View view) {
        Intent intent = new Intent(MainActivity.this, GameSylsActivity.class);
        intent.putExtra("gameStats",statistics.getGameStat(Statistics.SYMBOL_GAME));
        startActivity(intent);
    }

    public void moveToGameWords(View view) {
        Intent intent = new Intent(MainActivity.this, GameWordActivity.class);
        intent.putExtra("gameStats",statistics.getGameStat(Statistics.WORD_GAME));
        startActivity(intent);
    }


    public void audioPlayer(View view) {
        //set up MediaPlayer
        RandomizerImplement r = new RandomizerImplement();
        List<Syllable> a = new ArrayList<>();
        a.add(new Syllable("SHINxTSERE", ""));
        a.add(new Syllable("LAMEDxHIRIQ", ""));
        a.add(new Syllable("SHINxPATAHxNUN", ""));
        a.add(new Syllable("MEMxQUBUTSxREISH", ""));
        Word w = new Word(a);
        w.play(MainActivity.this);
    }

}
