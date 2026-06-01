package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonalSettingDAO extends DAO {
    public PersonalSettingDAO() { super(); }

    public Object[] getSettingByUserId(int userId) {
        if(con == null) return null;
        try {
            // Lấy thời gian học
            String sql = "SELECT dailyTimeGoal, isDarkMode FROM tblPersonalSetting WHERE tblUserID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            // Lấy ngôn ngữ từ bảng Profile
            String sqlLang = "SELECT tblLanguageID FROM tblProfile WHERE userID = ?";
            PreparedStatement psLang = con.prepareStatement(sqlLang);
            psLang.setInt(1, userId);
            ResultSet rsLang = psLang.executeQuery();
            
            int langId = 1;
            if(rsLang.next()) {
                langId = rsLang.getInt("tblLanguageID");
            }

            if(rs.next()) {
                return new Object[] {
                    rs.getString("dailyTimeGoal"),
                    langId
                };
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSettings(int userId, int dailyTimeGoal, int languageId) {
        if(con == null) return false;
        try {
            // Cập nhật tblPersonalSetting (thời gian học)
            String sql = "UPDATE tblPersonalSetting SET dailyTimeGoal = ? WHERE tblUserID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dailyTimeGoal + " minutes");
            ps.setInt(2, userId);
            int affected1 = ps.executeUpdate();
            
            // Cập nhật tblProfile (Ngôn ngữ)
            String sql2 = "UPDATE tblProfile SET tblLanguageID = ? WHERE userID = ?";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, languageId);
            ps2.setInt(2, userId);
            int affected2 = ps2.executeUpdate();
            
            return affected1 > 0 || affected2 > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}