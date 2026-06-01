package test;

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
    public void testSearchLessons_Exist() {
        List<Object[]> results = lessonDAO.searchLessons("A", "36");
        assertNotNull("Danh sách kết quả không được phép null", results);
    
    }

    @Test
    public void testSearchLessons_NotExist() {
        List<Object[]> results = lessonDAO.searchLessons("ten_sai", "ma_sai");

        assertNotNull("Danh sách kết quả không được phép null", results);
    }

    @Test
    public void testUpdateLesson_ExistID() {
        int existId = 1; 
        boolean result = lessonDAO.updateLesson(existId, "Bài Test", "CODE1", "Mô tả", "Chi tiết");;
    }

    @Test
    public void testUpdateLesson_NotExistID() {
        int invalidId = -1;
        boolean result = lessonDAO.updateLesson(invalidId, "Bài Test", "CODE1", "Mô tả", "Chi tiết");

        assertFalse("Kỳ vọng: Cập nhật thất bại vì ID không tồn tại", result);
    }
}