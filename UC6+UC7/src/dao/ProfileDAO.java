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

    public boolean addEXP(int profileId, int expToAdd) {
        if(con == null) return false;
        try {
            String sql = "UPDATE tblProfile SET totalEXP = totalEXP + ? WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, expToAdd);
            ps.setInt(2, profileId);
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateHeart(int profileId, int heartDelta) {
        if(con == null) return false;
        try {
            String sql = "UPDATE tblProfile SET heartCount = CASE " +
                         "WHEN heartCount + ? > 5 THEN 5 " +
                         "WHEN heartCount + ? < 0 THEN 0 " +
                         "ELSE heartCount + ? END WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, heartDelta);
            ps.setInt(2, heartDelta);
            ps.setInt(3, heartDelta);
            ps.setInt(4, profileId);
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}