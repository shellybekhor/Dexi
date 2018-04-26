package backend;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;

public class Syllable {
    public static final String AUDIO_EXT = ".ogg";
    private String key;
    private String representation;
    private String audioPath;

    public Syllable(String key, String rep) {
        this.key = key;
        this.representation = rep;
    }

    public String toString() {
        return representation;
    }

    public String getKey() {
        return key;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void play(Context context) {
        try {

            AssetFileDescriptor descriptor = context.getApplicationContext().getAssets().openFd(key + AUDIO_EXT);
            long start = descriptor.getStartOffset();
            long end = descriptor.getLength();
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
