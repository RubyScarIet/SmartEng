package dao;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DAO {
    public UserDAO() { 
        super(); 
    }

    public boolean checkLogin(User user) {
        boolean result = false;
        
        // Nếu DB chưa chạy, dùng logic giả định (Mock) để bạn vẫn test được App
        if(con == null) {
            System.out.println("Connection is null, using mock login!");
            user.setPosition(user.getUsername().equalsIgnoreCase("admin") ? "Admin" : "Learner");
            return true; 
        }

        String sql = "SELECT * FROM tblUser WHERE Username = ? AND Password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Đọc thông tin từ DB, xử lý lỗi cột linh hoạt tùy cấu trúc DB của bạn
                try { user.setId(rs.getInt("ID")); } catch (Exception e) {}
                try { user.setPosition(rs.getString("Role")); } catch (Exception e) {
                    try { user.setPosition(rs.getString("Position")); } catch (Exception ex) {}
                }
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}