package model;

import java.io.Serializable;
import java.util.Date;

public class StudyProgress implements Serializable {
    private int id;
    private boolean status;
    private Date lastAccessed;
    private String completionPercentage;
    private Date completedAt;
    private Lesson lesson;
    private Profile profile;

    public StudyProgress() {
        super();
    }

    public StudyProgress(boolean status, Date lastAccessed, String completionPercentage, Date completedAt, Lesson lesson, Profile profile) {
        super();
        this.status = status;
        this.lastAccessed = lastAccessed;
        this.completionPercentage = completionPercentage;
        this.completedAt = completedAt;
        this.lesson = lesson;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public String getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(String completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}