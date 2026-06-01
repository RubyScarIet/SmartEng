package test.unit;

import dao.LeagueDAO;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeagueDAOTest {
    private LeagueDAO leagueDAO;

    @Before
    public void setUp() {
        leagueDAO = new LeagueDAO();
    }

    @Test
    public void testGetTopUsersByLeague_HasUsers() {
        String leagueName = "Obsidian";
        Object[][] result = leagueDAO.getTopUsersByLeague(leagueName);
        assertNotNull("Danh sách top user không được null", result);
        
    }

    @Test
    public void testGetTopUsersByLeague_EmptyUsers() {
        String emptyLeagueName = "NewLeagueEmpty"; 
        Object[][] result = leagueDAO.getTopUsersByLeague(emptyLeagueName);
        assertNotNull("Trả về mảng nhưng kích thước = 0", result);
        assertEquals("Mảng trả về rỗng do giải chưa có người chơi", 0, result.length);
    }

    @Test
    public void testGetTopUsersByLeague_InvalidLeague() {
        String invalidLeague = "InvalidLeagueXYZ";
        Object[][] result = leagueDAO.getTopUsersByLeague(invalidLeague);
        assertNotNull("Trả về mảng nhưng kích thước = 0", result);
        assertEquals("Mảng trả về rỗng", 0, result.length);
    }

    @Test
    public void testGetUserStats_Exist() {
        String displayName = "player1"; 
        Object[] result = leagueDAO.getUserStats(displayName);
        
    }

    @Test
    public void testGetUserStats_NotExist() {
        String notExistName = "user_does_not_exist_9999";
        Object[] result = leagueDAO.getUserStats(notExistName);
        assertNull("Truy vấn user không tồn tại trả về null", result);
    }

    @Test
    public void testGetAllLeagues_HasData() {
        List<String> result = leagueDAO.getAllLeagues();
        assertNotNull("Danh sách giải đấu không được null", result);
        
    }

    @Test
    public void testGetAllLeagues_EmptyData() {
        List<String> result = leagueDAO.getAllLeagues();
        assertEquals("Danh sách giải đấu phải rỗng", 0, result.size());
    }
}
