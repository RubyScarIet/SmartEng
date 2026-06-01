package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class AccessHistoryDAO extends DAO {
    public AccessHistoryDAO() { super(); }
    public List<Object[]> searchByTime(String timeString) {
        List<Object[]> list = new ArrayList<>();
        if(con == null) return list;
        try {
            String sql = "SELECT ID, tblUserID, timeStamp, IpAddress, deviceInfo, actionType, duration " +
                         "FROM tblAccessHistory WHERE CONVERT(VARCHAR(5), timeStamp, 108) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, timeString);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int dur = rs.getInt("duration");
                String durStr = String.format("%02d:%02d:%02d", dur / 3600, (dur % 3600) / 60, dur % 60);
                java.sql.Timestamp ts = rs.getTimestamp("timeStamp");
                String timeFormatted = ts != null ? new java.text.SimpleDateFormat("HH:mm:ss").format(ts) : "";
                list.add(new Object[]{ 
                    rs.getInt("ID"), 
                    rs.getInt("tblUserID"),
                    timeFormatted, 
                    rs.getString("IpAddress"),
                    rs.getString("deviceInfo"),
                    rs.getString("actionType"),
                    timeFormatted, 
                    durStr 
                });
            }
        } catch(Exception e) {
            System.out.println("Lỗi searchByTime: " + e.getMessage());
        }
        return list;
    }
}
