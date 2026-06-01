package model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String position;
    private Profile profile;
    private PersonalSetting personalSetting;

    public User() {
        super();
    }

    public User(String username, String password, String fullname, String position, Profile profile, PersonalSetting personalSetting) {
        super();
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.position = position;
        this.profile = profile;
        this.personalSetting = personalSetting;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public PersonalSetting getPersonalSetting() {
        return personalSetting;
    }

    public void setPersonalSetting(PersonalSetting personalSetting) {
        this.personalSetting = personalSetting;
    }

}