package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class PersonalSettingDAO extends DAO {
    public PersonalSettingDAO() { super(); }
    public Object[] getSettingByUserId(int userId) {
        if(con == null) return null;
        try {
            String sql = "SELECT dailyTimeGoal, isDarkMode, isNotificationEnabled, isSoundEnabled FROM tblPersonalSetting WHERE tblUserID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Object[] {
                    rs.getString("dailyTimeGoal"),
                    rs.getBoolean("isDarkMode"),
                    rs.getBoolean("isNotificationEnabled"),
                    rs.getBoolean("isSoundEnabled")
                };
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateSettings(int userId, int dailyTimeGoal, boolean isDarkMode, boolean isNotificationEnabled, boolean isSoundEnabled) {
        if(con == null) return false;
        try {
            String sql = "UPDATE tblPersonalSetting SET dailyTimeGoal = ?, isDarkMode = ?, isNotificationEnabled = ?, isSoundEnabled = ? WHERE tblUserID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dailyTimeGoal + " minutes");
            ps.setBoolean(2, isDarkMode);
            ps.setBoolean(3, isNotificationEnabled);
            ps.setBoolean(4, isSoundEnabled);
            ps.setInt(5, userId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
