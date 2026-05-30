package model;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    private int id;
    private String word;
    private String meaning;
    private String pronunciation;
    private Language language;

    public Vocabulary() {
        super();
    }

    public Vocabulary(String word, String meaning, String pronunciation, Language language) {
        super();
        this.word = word;
        this.meaning = meaning;
        this.pronunciation = pronunciation;
        this.language = language;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}