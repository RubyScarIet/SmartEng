package model;
import java.io.Serializable;
public class Profile implements Serializable {
    private int id;
    private String displayName;
    private String avatarURL;
    private long totalEXP;
    private int currentStreak;
    private int heartCount;
    private String prizes;
    private Language language;
    private League league;
    public Profile() {
        super();
    }
    public Profile(String displayName, String avatarURL, long totalEXP, int currentStreak, int heartCount, String prizes, Language language, League league) {
        super();
        this.displayName = displayName;
        this.avatarURL = avatarURL;
        this.totalEXP = totalEXP;
        this.currentStreak = currentStreak;
        this.heartCount = heartCount;
        this.prizes = prizes;
        this.language = language;
        this.league = league;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getAvatarURL() {
        return avatarURL;
    }
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
    public long getTotalEXP() {
        return totalEXP;
    }
    public void setTotalEXP(long totalEXP) {
        this.totalEXP = totalEXP;
    }
    public int getCurrentStreak() {
        return currentStreak;
    }
    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }
    public int getHeartCount() {
        return heartCount;
    }
    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }
    public String getPrizes() {
        return prizes;
    }
    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }
    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }
    public League getLeague() {
        return league;
    }
    public void setLeague(League league) {
        this.league = league;
    }
}
