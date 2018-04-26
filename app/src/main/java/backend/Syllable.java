package backend;

public class Syllable {
    private String key;
    private String representation;
    private String audioPath;
    public Syllable(String key, String rep) {
        this.key = key;
        this.representation = rep;
    }
    public String toString(){
        return representation;
    }
    public String getKey(){
        return key;
    }
    public String getAudioPath(){
        return audioPath;
    }
}
