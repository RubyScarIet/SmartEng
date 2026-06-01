package model;
import java.io.Serializable;
public class Topic implements Serializable {
    private int id;
    private String name;
    private String description;
    private int orderIndex;
    public Topic() {
        super();
    }
    public Topic(String name, String description, int orderIndex) {
        super();
        this.name = name;
        this.description = description;
        this.orderIndex = orderIndex;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getOrderIndex() {
        return orderIndex;
    }
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}
