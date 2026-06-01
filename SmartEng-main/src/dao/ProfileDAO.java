package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileDAO extends DAO {
    public ProfileDAO() { super(); }

    // (Giữ nguyên hàm getProfileByUserId cũ của bạn ở đây...)
    public Object[] getProfileByUserId(int userId) {
        if(con == null) return null;
        try {
            String sql = "SELECT displayName, avatarURL, level, totalEXP, currentStreak FROM tblProfile WHERE userID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Object[] {
                    rs.getString("displayName"),
                    rs.getString("avatarURL"),
                    rs.getInt("level"),
                    rs.getInt("totalEXP"),
                    rs.getInt("currentStreak")
                };
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // THÊM MỚI HÀM NÀY: Cập nhật EXP và Tim sau khi học xong
    public boolean updateLearningStats(int userId, int earnedExp, int heartsLost) {
        if(con == null) return false;
        try {
            // Cộng totalEXP và trừ heartCount cho User đang học
            String sql = "UPDATE tblProfile SET totalEXP = totalEXP + ?, heartCount = heartCount - ? WHERE userID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, earnedExp);
            ps.setInt(2, heartsLost);
            ps.setInt(3, userId);
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            System.out.println("Lỗi updateLearningStats: " + e.getMessage());
        }
        return false;
    }
}