package model;
import java.io.Serializable;
public class TestResult implements Serializable {
    private int id;
    private float score;
    private boolean isPassed;
    private int attemptNumber;
    private Test test;
    private Profile profile;
    public TestResult() {
        super();
    }
    public TestResult(float score, boolean isPassed, int attemptNumber, Test test, Profile profile) {
        super();
        this.score = score;
        this.isPassed = isPassed;
        this.attemptNumber = attemptNumber;
        this.test = test;
        this.profile = profile;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public boolean isPassed() {
        return isPassed;
    }
    public void setPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }
    public int getAttemptNumber() {
        return attemptNumber;
    }
    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }
    public Test getTest() {
        return test;
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
