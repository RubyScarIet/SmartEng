package test;

import dao.UserDAO;
import model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOTest {
    private UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    public void testCheckLogin_ValidAdmin() {
        User user = new User();
        user.setUsername("admin01");
        user.setPassword("Admin@2024"); 

        boolean result = userDAO.checkLogin(user);

        assertTrue("Kỳ vọng: Đăng nhập thành công với tài khoản Admin", result);
        assertEquals("Kỳ vọng: Position phải được set là Admin", "Admin", user.getPosition());
    }

    @Test
    public void testCheckLogin_InvalidAccount() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("mat_khau_sai_hoan_toan");

        boolean result = userDAO.checkLogin(user);
        assertFalse("Kỳ vọng: Đăng nhập thất bại khi sai tài khoản hoặc mật khẩu", result);
    }
}