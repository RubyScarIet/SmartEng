package model;
import java.io.Serializable;
public class League implements Serializable {
    private int id;
    private String name;
    private String iconURL;
    public League() {
        super();
    }
    public League(String name, String iconURL) {
        super();
        this.name = name;
        this.iconURL = iconURL;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
