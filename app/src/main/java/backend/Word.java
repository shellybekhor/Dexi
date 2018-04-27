package backend;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Word {
    private List<Syllable> syllables;
    private boolean ready = false;
    private WordPlayer player;

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
        player = new WordPlayer(context);
        ready = true;
        player.play();
    }

    private class WordPlayer implements MediaPlayer.OnCompletionListener {
        Queue<AssetFileDescriptor> syllablesQueue;
        private MediaPlayer mediaPlayer = null;
        long start, end;
        private boolean isReady = false;
        Context context;

        WordPlayer(Context context) {
            this.syllablesQueue = new LinkedList<>();
            this.context = context;
            try {
                reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void play() {
            if (isReady) {
                mediaPlayer.start();
            } else {
                try {
                    reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void reset() throws IOException {
            for (Syllable syl : syllables) {
                this.syllablesQueue.add(context.getApplicationContext().getAssets().openFd(syl.getKey() + Syllable.AUDIO_EXT));
            }
            AssetFileDescriptor descriptor = this.syllablesQueue.poll();
            start = descriptor.getStartOffset();
            end = descriptor.getLength();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
            mediaPlayer.prepare();
            this.isReady = true;
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            if (!syllablesQueue.isEmpty()) {
                try {
                    AssetFileDescriptor descriptor = this.syllablesQueue.poll();
                    start = descriptor.getStartOffset();
                    end = descriptor.getLength();
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setOnCompletionListener(this);
                    mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
