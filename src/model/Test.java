package model;

import java.io.Serializable;
import java.sql.Time;

public class Test implements Serializable {
    private int id;
    private String title;
    private String type;
    private Time timeLimit;
    private long rewardExp;
    private int totalQuestions;

    public Test() {
        super();
    }

    public Test(String title, String type, Time timeLimit, long rewardExp, int totalQuestions) {
        super();
        this.title = title;
        this.type = type;
        this.timeLimit = timeLimit;
        this.rewardExp = rewardExp;
        this.totalQuestions = totalQuestions;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Time getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Time timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getRewardExp() {
        return rewardExp;
    }

    public void setRewardExp(long rewardExp) {
        this.rewardExp = rewardExp;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

}