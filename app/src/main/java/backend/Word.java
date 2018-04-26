package backend;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.List;

public class Word {
    List<Syllable> syllables;

    public Word(List<Syllable> syllables) {
        this.syllables = syllables;
    }

    public String toString() {
        String str = "";
        for (Syllable s : syllables) {
            str = str.concat(s.toString());
        }
        return str;
    }

    public void play(Context context) {
        try {
            AssetFileDescriptor[] descriptors = new AssetFileDescriptor[syllables.size()];
            for (int i = 0; i < descriptors.length; i++) {
                descriptors[i] = context.getApplicationContext().getAssets().openFd(syllables.get(i).getKey() + Syllable.AUDIO_EXT);
            }
            long start = descriptors[0].getStartOffset();
            long end = descriptors[0].getLength();
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(descriptors[0].getFileDescriptor(), start, end);
            mediaPlayer.setOnCompletionListener(new syllablePlayer());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class syllablePlayer implements MediaPlayer.OnCompletionListener {
        syllablePlayer() {

        }
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
        }
    }
}
