package model;
import java.io.Serializable;
import java.util.Date;
public class PersonalMission implements Serializable {
    private int id;
    private Date assignedDate;
    private String currentProgress;
    private boolean isCompleted;
    private boolean isRewardClaimed;
    private DailyMission dailyMission;
    private Profile profile;
    public PersonalMission() {
        super();
    }
    public PersonalMission(Date assignedDate, String currentProgress, boolean isCompleted, boolean isRewardClaimed, DailyMission dailyMission, Profile profile) {
        super();
        this.assignedDate = assignedDate;
        this.currentProgress = currentProgress;
        this.isCompleted = isCompleted;
        this.isRewardClaimed = isRewardClaimed;
        this.dailyMission = dailyMission;
        this.profile = profile;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getAssignedDate() {
        return assignedDate;
    }
    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }
    public String getCurrentProgress() {
        return currentProgress;
    }
    public void setCurrentProgress(String currentProgress) {
        this.currentProgress = currentProgress;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public boolean isRewardClaimed() {
        return isRewardClaimed;
    }
    public void setRewardClaimed(boolean isRewardClaimed) {
        this.isRewardClaimed = isRewardClaimed;
    }
    public DailyMission getDailyMission() {
        return dailyMission;
    }
    public void setDailyMission(DailyMission dailyMission) {
        this.dailyMission = dailyMission;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
