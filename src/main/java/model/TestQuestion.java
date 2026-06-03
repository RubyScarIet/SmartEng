package model;

import java.io.Serializable;

public class TestQuestion implements Serializable {
    private int id;
    private int orderIndex;
    private float point;
    private Question question;

    public TestQuestion() {
        super();
    }

    public TestQuestion(int orderIndex, float point, Question question) {
        super();
        this.orderIndex = orderIndex;
        this.point = point;
        this.question = question;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}