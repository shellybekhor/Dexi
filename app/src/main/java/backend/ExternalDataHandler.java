package backend;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternalDataHandler {
    private static final String statsPath = "stats_save";
    public Statistics statistics;

    public ExternalDataHandler() {

    }

    public void readExternalData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(statsPath));
            statistics = (Statistics) ois.readObject();
        } catch (IOException e) {
            statistics = new Statistics();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeExternalData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(statsPath));
            oos.writeObject(statistics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
