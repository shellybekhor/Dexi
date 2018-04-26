package shellybekhor.dexi;

import android.view.View;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;

import backend.*;

public class GameWordRunner {
    private TextView showWord;
    private Button readWord;
    private Button success;
    private Button fail;
    private SequenceRandomizer randomizer;

    public GameWordRunner(AppCompatActivity app){
        showWord = app.findViewById(R.id.showWord);
        readWord = app.findViewById(R.id.readWord);
        success = app.findViewById(R.id.success);
        fail = app.findViewById(R.id.fail);
        randomizer = new RandomizerImplement();
    }

    public void startGame() {
        Word newWord = randomizer.getWord();
        showWord.setText(newWord.toString());
    }
}
