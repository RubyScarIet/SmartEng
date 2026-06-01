package test.unit;

import dao.LessonDAO;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LessonDAOTest {
    private LessonDAO lessonDAO;

    @Before
    public void setUp() {
        lessonDAO = new LessonDAO();
    }

    @Test
    public void testGetLessonsByTopic_HasLessons() {
        int topicId = 1; 
        List<Object[]> result = lessonDAO.getLessonsByTopic(topicId);
        assertNotNull("Danh sách bài học không được null", result);
        
    }

    @Test
    public void testGetLessonsByTopic_EmptyLessons() {
        int emptyTopicId = 999999; 
        List<Object[]> result = lessonDAO.getLessonsByTopic(emptyTopicId);
        assertNotNull("Danh sách bài học trả về mảng rỗng, không phải null", result);
        assertEquals("Danh sách bài học phải rỗng", 0, result.size());
    }

    @Test
    public void testGetLessonsByTopic_InvalidTopicId() {
        int invalidTopicId = -1;
        List<Object[]> result = lessonDAO.getLessonsByTopic(invalidTopicId);
        assertNotNull("Danh sách không null", result);
        assertEquals("Topic ID âm phải trả về danh sách rỗng", 0, result.size());
    }
}
