package model;
import java.io.Serializable;
public class PersonalSetting implements Serializable {
    private int id;
    private String dailyTimeGoal;
    private boolean isNotificationEnabled;
    private boolean isDarkMode;
    private boolean isSoundEnabled;
    public PersonalSetting() {
        super();
    }
    public PersonalSetting(String dailyTimeGoal, boolean isNotificationEnabled, boolean isDarkMode, boolean isSoundEnabled) {
        super();
        this.dailyTimeGoal = dailyTimeGoal;
        this.isNotificationEnabled = isNotificationEnabled;
        this.isDarkMode = isDarkMode;
        this.isSoundEnabled = isSoundEnabled;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDailyTimeGoal() {
        return dailyTimeGoal;
    }
    public void setDailyTimeGoal(String dailyTimeGoal) {
        this.dailyTimeGoal = dailyTimeGoal;
    }
    public boolean isNotificationEnabled() {
        return isNotificationEnabled;
    }
    public void setNotificationEnabled(boolean isNotificationEnabled) {
        this.isNotificationEnabled = isNotificationEnabled;
    }
    public boolean isDarkMode() {
        return isDarkMode;
    }
    public void setDarkMode(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }
    public boolean isSoundEnabled() {
        return isSoundEnabled;
    }
    public void setSoundEnabled(boolean isSoundEnabled) {
        this.isSoundEnabled = isSoundEnabled;
    }
}
