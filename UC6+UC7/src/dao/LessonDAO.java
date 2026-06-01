package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO extends DAO {
    public LessonDAO() { super(); }

    public List<Object[]> getLessonsByTopic(int topicID) {
        List<Object[]> list = new ArrayList<>();
        if(con == null) return list;
        try {
            String sql = "SELECT ID, name FROM tblLesson WHERE tblTopicID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, topicID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Object[]{ rs.getInt("ID"), rs.getString("name") });
            }
        } catch(Exception e) {
            System.out.println("Lỗi getLessonsByTopic: " + e.getMessage());
        }
        return list;
    }

    public List<Object[]> searchLessons(String name, String code) {
        List<Object[]> list = new ArrayList<>();
        if(con == null) return list;
        try {
            String sql = "SELECT ID, name, code, description, des FROM tblLesson WHERE 1=1";
            if(name != null && !name.trim().isEmpty()) {
                sql += " AND name LIKE ?";
            }
            if(code != null && !code.trim().isEmpty()) {
                sql += " AND code LIKE ?";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            int paramIndex = 1;
            if(name != null && !name.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + name.trim() + "%");
            }
            if(code != null && !code.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + code.trim() + "%");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Object[]{ 
                    rs.getInt("ID"), 
                    rs.getString("name"),
                    rs.getString("code"),
                    rs.getString("description"),
                    rs.getString("des")
                });
            }
        } catch(Exception e) {
            System.out.println("Lỗi searchLessons: " + e.getMessage());
        }
        return list;
    }

    public boolean updateLesson(int id, String name, String code, String description, String des) {
        if(con == null) return false;
        try {
            String sql = "UPDATE tblLesson SET name = ?, code = ?, description = ?, des = ? WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, code);
            ps.setString(3, description);
            ps.setString(4, des);
            ps.setInt(5, id);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch(Exception e) {
            System.out.println("Lỗi updateLesson: " + e.getMessage());
            return false;
        }
    }
}