package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Topic;
public class TopicDAO extends DAO {
    public TopicDAO() { super(); }
    public List<Object[]> getAllTopics() {
        List<Object[]> list = new ArrayList<>();
        if(con == null) return list;
        try {
            String sql = "SELECT ID, name FROM tblTopic WHERE tblLanguageID = 1 ORDER BY orderIndex ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Object[]{ rs.getInt("ID"), rs.getString("name") });
            }
        } catch(Exception e) {
            System.out.println("Lỗi getAllTopics: " + e.getMessage());
        }
        return list;
    }
}
