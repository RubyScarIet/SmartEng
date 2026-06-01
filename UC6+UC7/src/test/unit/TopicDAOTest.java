package test.unit;

import dao.TopicDAO;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class TopicDAOTest {
    private TopicDAO topicDAO;

    @Before
    public void setUp() {
        topicDAO = new TopicDAO();
    }

    @Test
    public void testGetAllTopics_ValidData() {
        List<Object[]> result = topicDAO.getAllTopics();
        assertNotNull("Danh sách Topic không được null", result);
        assertTrue("Cần có ít nhất 1 Topic trả về (nếu DB có dữ liệu)", result.size() > 0);
    }

    @Test
    public void testGetAllTopics_EmptyData() {
        List<Object[]> result = topicDAO.getAllTopics();
        assertEquals("Danh sách Topic phải rỗng khi không có dữ liệu", 0, result.size());
    }
}
