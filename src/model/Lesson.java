package model;
import java.io.Serializable;
public class Lesson implements Serializable {
    private int id;
    private String name;
    private String code;
    private String context;
    private String des;
    private Test test;
    private LessonVocabulary lessonVocabulary;
    private Question question;
    public Lesson() {
        super();
    }
    public Lesson(String name, String code, String context, String des, Test test, LessonVocabulary lessonVocabulary, Question question) {
        super();
        this.name = name;
        this.code = code;
        this.context = context;
        this.des = des;
        this.test = test;
        this.lessonVocabulary = lessonVocabulary;
        this.question = question;
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
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public Test getTest() {
        return test;
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public LessonVocabulary getLessonVocabulary() {
        return lessonVocabulary;
    }
    public void setLessonVocabulary(LessonVocabulary lessonVocabulary) {
        this.lessonVocabulary = lessonVocabulary;
    }
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
}
