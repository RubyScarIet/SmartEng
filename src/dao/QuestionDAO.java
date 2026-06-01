package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class QuestionDAO extends DAO {
    public QuestionDAO() { super(); }
    public List<Object[]> getQuestionsByLesson(int lessonID) {
        List<Object[]> list = new ArrayList<>();
        if(con == null) return list;
        try {
            String sql = "SELECT ID, content, questionType, options, correctAnswer FROM tblQuestion WHERE tblLessonID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, lessonID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Object[]{ 
                    rs.getInt("ID"), 
                    rs.getString("content"),
                    rs.getString("questionType"),
                    rs.getString("options"),
                    rs.getString("correctAnswer")
                });
            }
        } catch(Exception e) {
            System.out.println("Lỗi getQuestionsByLesson: " + e.getMessage());
        }
        return list;
    }
}
