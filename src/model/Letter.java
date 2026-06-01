package model;
import java.io.Serializable;
public class Letter implements Serializable {
    private int id;
    private String character;
    private String type;
    private String audioURL;
    private String exampleWord;
    private String exampleAudioURL;
    public Letter() {
        super();
    }
    public Letter(String character, String type, String audioURL, String exampleWord, String exampleAudioURL) {
        super();
        this.character = character;
        this.type = type;
        this.audioURL = audioURL;
        this.exampleWord = exampleWord;
        this.exampleAudioURL = exampleAudioURL;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getAudioURL() {
        return audioURL;
    }
    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }
    public String getExampleWord() {
        return exampleWord;
    }
    public void setExampleWord(String exampleWord) {
        this.exampleWord = exampleWord;
    }
    public String getExampleAudioURL() {
        return exampleAudioURL;
    }
    public void setExampleAudioURL(String exampleAudioURL) {
        this.exampleAudioURL = exampleAudioURL;
    }
}
