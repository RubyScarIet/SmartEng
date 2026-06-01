package test.unit;

import dao.ProfileDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfileDAOTest {
    private ProfileDAO profileDAO;

    @Before
    public void setUp() {
        profileDAO = new ProfileDAO();
    }

    @Test
    public void testAddEXP_Success() {
        int validProfileId = 1;
        int expToAdd = 50;

    }

    @Test
    public void testAddEXP_Fail() {
        int invalidProfileId = -999;
        int expToAdd = 50;
        boolean result = profileDAO.addEXP(invalidProfileId, expToAdd);
        assertFalse("ID không tồn tại trả về false", result);
    }

    @Test
    public void testUpdateHeart_Success() {
        int validProfileId = 1;
        int heartDelta = -1; 
        
        
    }

    @Test
    public void testUpdateHeart_Fail() {
        int invalidProfileId = -999;
        int heartDelta = -1;
        boolean result = profileDAO.updateHeart(invalidProfileId, heartDelta);
        assertFalse("Cập nhật tim thất bại do ID không tồn tại", result);
    }
}
