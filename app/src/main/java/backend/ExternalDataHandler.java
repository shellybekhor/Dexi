package backend;


import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternalDataHandler {
    private final static String statsFilename = "stats";
    private final File statsFile;
    public Statistics statistics;

    public ExternalDataHandler(Context context) {
        statsFile = new File(context.getFilesDir().getPath(), statsFilename);
    }

    public void readExternalData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(statsFile));
            statistics = (Statistics) ois.readObject();
        } catch (IOException e) {
            statistics = new Statistics();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeExternalData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(statsFile));
            oos.writeObject(statistics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
