package model;

import java.io.Serializable;

public class DailyMission implements Serializable {
    private int id;
    private String title;
    private String description;
    private long targetValue;
    private int reward;

    public DailyMission() {
        super();
    }

    public DailyMission(String title, String description, long targetValue, int reward) {
        super();
        this.title = title;
        this.description = description;
        this.targetValue = targetValue;
        this.reward = reward;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(long targetValue) {
        this.targetValue = targetValue;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

}