package model;
import java.io.Serializable;
import java.util.Date;
public class AccessHistory implements Serializable {
    private int id;
    private Date accessTime;
    private String ipAddress;
    private String deviceInfo;
    private String actionType;
    private Date timeStamp;
    private int duration;
    private User user;
    public AccessHistory() {
        super();
    }
    public AccessHistory(Date accessTime, String ipAddress, String deviceInfo, String actionType, Date timeStamp, int duration, User user) {
        super();
        this.accessTime = accessTime;
        this.ipAddress = ipAddress;
        this.deviceInfo = deviceInfo;
        this.actionType = actionType;
        this.timeStamp = timeStamp;
        this.duration = duration;
        this.user = user;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getAccessTime() {
        return accessTime;
    }
    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getDeviceInfo() {
        return deviceInfo;
    }
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
    public String getActionType() {
        return actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
