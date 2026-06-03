package dao;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class DailyMissionDAOTest {

    @Test
    public void testGetMissions_ReturnsDataFromDB() {
        DailyMissionDAO dao = new DailyMissionDAO();
        List<Object[]> missions = dao.getMissions();
        
        assertNotNull("Danh sách nhiệm vụ không được null", missions);
        // Lưu ý: Test này giả định cơ sở dữ liệu SmartEngDB đã được seed dữ liệu
        assertTrue("Cơ sở dữ liệu phải trả về ít nhất 1 nhiệm vụ", missions.size() > 0);
        
        // Kiểm tra cấu trúc của dòng dữ liệu đầu tiên (title, reward, description, targetValue)
        Object[] firstMission = missions.get(0);
        assertEquals("Mỗi dòng phải có đúng 4 trường dữ liệu theo truy vấn SQL", 4, firstMission.length);
        
        // Kiểm tra kiểu dữ liệu
        assertNotNull("Title không được rỗng", firstMission[0]);
        assertTrue("Reward phải là kiểu Integer", firstMission[1] instanceof Integer);
        assertTrue("TargetValue phải là kiểu Integer", firstMission[3] instanceof Integer);
    }
}
