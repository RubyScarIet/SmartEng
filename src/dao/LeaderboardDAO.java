package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class LeaderboardDAO extends DAO {
    public LeaderboardDAO() { super(); }
    public Object[][] getTopUsersByLeague(String leagueName) {
        if(con == null) return null;
        try {
            String sql = "SELECT p.avatarURL, p.displayName, p.totalEXP " +
                         "FROM tblProfile p " +
                         "INNER JOIN tblUser u ON p.userID = u.ID " +
                         "LEFT JOIN tblLeague l ON p.tblLeagueID = l.ID " +
                         "WHERE u.position != 'admin' AND l.name = ? " +
                         "ORDER BY p.totalEXP DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, leagueName);
            ResultSet rs = ps.executeQuery();
            List<Object[]> rows = new ArrayList<>();
            int rank = 1;
            while(rs.next()) {
                rows.add(new Object[] { rank++, rs.getString("avatarURL"), rs.getString("displayName"), rs.getInt("totalEXP") });
            }
            return rows.toArray(new Object[0][]);
        } catch(Exception e) {
            System.out.println("Lỗi truy vấn Leaderboard theo League: " + e.getMessage());
            return null;
        }
    }
    public List<String> getAllLeagues() {
        List<String> leagues = new ArrayList<>();
        if (con == null) return leagues;
        try {
            String sql = "SELECT name FROM tblLeague ORDER BY ID ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                leagues.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn League: " + e.getMessage());
        }
        return leagues;
    }
    public Object[] getUserStats(String displayName) {
        if(con == null) return null;
        try {
            String sql = "SELECT p.avatarURL, p.displayName, p.totalEXP, p.currentStreak, p.prizes, l.name as leagueName " +
                         "FROM tblProfile p " +
                         "LEFT JOIN tblLeague l ON p.tblLeagueID = l.ID " +
                         "WHERE p.displayName = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, displayName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Object[] {
                    rs.getString("avatarURL"),
                    rs.getString("displayName"),
                    rs.getInt("totalEXP"),
                    rs.getInt("currentStreak"),
                    rs.getString("prizes"),
                    rs.getString("leagueName")
                };
            }
        } catch(Exception e) {
            System.out.println("Lỗi truy vấn UserStats: " + e.getMessage());
        }
        return null;
    }
}
