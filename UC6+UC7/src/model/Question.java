package model;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String content;
    private String questionType;
    private String difficulty;
    private String options;
    private String correctAnswer;


    public Question() {
        super();
    }

    public Question(String content, String questionType, String difficulty, String options, String correctAnswer) {
        super();
        this.content = content;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }



}