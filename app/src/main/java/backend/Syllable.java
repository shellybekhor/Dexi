package backend;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class Syllable {
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
            AssetFileDescriptor descriptor = context.getApplicationContext().getAssets().openFd(key + ".ogg");
            long start = descriptor.getStartOffset();
            long end = descriptor.getLength();
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
