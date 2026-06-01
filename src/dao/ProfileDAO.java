package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileDAO extends DAO {
    public ProfileDAO() { super(); }

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

    public boolean updateProfile(int userId, String displayName, String avatarURL) {
        if (con == null) return false;
        try {
            // Check if name already exists for another user (duplicate check for TC_UC1_04)
            String checkSql = "SELECT ID FROM tblProfile WHERE displayName = ? AND userID != ?";
            PreparedStatement psCheck = con.prepareStatement(checkSql);
            psCheck.setString(1, displayName);
            psCheck.setInt(2, userId);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                return false; // Name already exists
            }

            String sql = "UPDATE tblProfile SET displayName = ?, avatarURL = ? WHERE userID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, displayName);
            ps.setString(2, avatarURL);
            ps.setInt(3, userId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}