package test.unit;

import dao.QuestionDAO;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class QuestionDAOTest {
    private QuestionDAO questionDAO;

    @Before
    public void setUp() {
        questionDAO = new QuestionDAO();
    }

    @Test
    public void testGetQuestionsByLesson_HasQuestions() {
        int lessonId = 1; 
        List<Object[]> result = questionDAO.getQuestionsByLesson(lessonId);
        assertNotNull("Danh sách câu hỏi không được null", result);
        
    }

    @Test
    public void testGetQuestionsByLesson_EmptyQuestions() {
        int emptyLessonId = 999999; 
        List<Object[]> result = questionDAO.getQuestionsByLesson(emptyLessonId);
        assertNotNull("Danh sách câu hỏi trả về mảng rỗng, không null", result);
        assertEquals("Lesson trống trả về danh sách rỗng", 0, result.size());
    }

    @Test
    public void testGetQuestionsByLesson_InvalidLessonId() {
        int invalidLessonId = -1;
        List<Object[]> result = questionDAO.getQuestionsByLesson(invalidLessonId);
        assertNotNull(result);
        assertEquals("Lesson ID âm trả về danh sách rỗng", 0, result.size());
    }
}
