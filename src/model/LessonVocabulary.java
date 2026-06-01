package model;
import java.io.Serializable;
public class LessonVocabulary implements Serializable {
    private int id;
    private int orderIndex;
    private boolean isReview;
    private Vocabulary vocabulary;
    public LessonVocabulary() {
        super();
    }
    public LessonVocabulary(int orderIndex, boolean isReview, Vocabulary vocabulary) {
        super();
        this.orderIndex = orderIndex;
        this.isReview = isReview;
        this.vocabulary = vocabulary;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderIndex() {
        return orderIndex;
    }
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
    public boolean isReview() {
        return isReview;
    }
    public void setReview(boolean isReview) {
        this.isReview = isReview;
    }
    public Vocabulary getVocabulary() {
        return vocabulary;
    }
    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }
}
