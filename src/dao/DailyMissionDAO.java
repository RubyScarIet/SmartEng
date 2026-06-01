package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class DailyMissionDAO extends DAO {
    public DailyMissionDAO() { super(); }
    public List<Object[]> getMissions() {
        List<Object[]> missions = new ArrayList<>();
        if (con == null) return missions;
        try {
            String sql = "SELECT title, reward, description, targetValue FROM tblDailyMission";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                missions.add(new Object[] {
                    rs.getString("title"),
                    rs.getInt("reward"),
                    rs.getString("description"),
                    rs.getInt("targetValue")
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return missions;
    }
}
