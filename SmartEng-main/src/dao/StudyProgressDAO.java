package dao;

import java.sql.PreparedStatement;

public class StudyProgressDAO extends DAO {
    public StudyProgressDAO() { super(); }

    // Lưu tiến trình khi hoàn thành bài học
    public boolean saveProgress(int profileId, int lessonId) {
        if(con == null) return false;
        try {
            String sql = "INSERT INTO tblStudyProgress (status, completionPercentage, tblLessonID, tblProfileID) VALUES (1, '100%', ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, lessonId);
            ps.setInt(2, profileId);
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            System.out.println("Lỗi saveProgress: " + e.getMessage());
            return false;
        }
    }
}