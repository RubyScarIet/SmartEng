package model;

import java.io.Serializable;

public class Language implements Serializable {
    private int id;
    private String code;
    private String name;
    private String iconURL;

    public Language() {
        super();
    }

    public Language(String code, String name, String iconURL) {
        super();
        this.code = code;
        this.name = name;
        this.iconURL = iconURL;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

}