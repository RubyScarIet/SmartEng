package test;

import dao.AccessHistoryDAO;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class AccessHistoryDAOTest {
    private AccessHistoryDAO accessHistoryDAO;

    @Before
    public void setUp() {
        accessHistoryDAO = new AccessHistoryDAO();
    }

    @Test
    public void testSearchByTime_ExistData() {
        String validTime = "08:30";
        List<Object[]> results = accessHistoryDAO.searchByTime(validTime);
        assertNotNull("Danh sách kết quả không được phép null", results);
    
    }
    @Test
    public void testSearchByTime_NoData() {
        String invalidTime = "23:59"; 
        List<Object[]> results = accessHistoryDAO.searchByTime(invalidTime);

        assertNotNull("Danh sách kết quả không được phép null", results);

    }
}