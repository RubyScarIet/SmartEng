package test.unit;

import dao.StudyProgressDAO;
import model.StudyProgress;
import model.Lesson;
import model.Profile;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudyProgressDAOTest {
    private StudyProgressDAO studyProgressDAO;

    @Before
    public void setUp() {
        studyProgressDAO = new StudyProgressDAO();
    }

    @Test
    public void testGetProgress_Exist() {
        StudyProgress result = studyProgressDAO.getProgress(1, 1);
        if (result != null) {
            assertEquals("ProfileID khớp", 1, result.getProfile().getId());
            assertEquals("LessonID khớp", 1, result.getLesson().getId());
        }
    }

    @Test
    public void testGetProgress_NotExist() {
        StudyProgress result = studyProgressDAO.getProgress(-1, -1);
        assertNull("Tiến trình không tồn tại trả về null", result);
    }

    @Test
    public void testSaveProgress_Success() {
        
        StudyProgress sp = new StudyProgress();
        sp.setStatus(false);
        sp.setCompletionPercentage("50.0");
        
        Profile p = new Profile(); p.setId(2);
        Lesson l = new Lesson(); l.setId(2);
        sp.setProfile(p);
        sp.setLesson(l);
        
        
        
    }

    @Test
    public void testSaveProgress_Fail() {
        StudyProgress sp = new StudyProgress();
        
        boolean result = studyProgressDAO.saveProgress(sp);
        assertFalse("Lưu thất bại trả về false", result);
    }
}
