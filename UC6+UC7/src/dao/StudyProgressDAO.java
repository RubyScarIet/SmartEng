package dao;

import model.StudyProgress;
import model.Lesson;
import model.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudyProgressDAO extends DAO {

    public StudyProgressDAO() {
        super();
    }

    public StudyProgress getProgress(int profileId, int lessonId) {
        String sql = "SELECT sp.ID, sp.status, sp.lastAccessed, sp.completionPercentage, sp.completedAt, "
                   + "sp.tblLessonID, sp.tblProfileID "
                   + "FROM tblStudyProgress sp "
                   + "WHERE sp.tblProfileID = ? AND sp.tblLessonID = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, profileId);
            ps.setInt(2, lessonId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("[StudyProgressDAO] Lỗi getProgress: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); } catch (Exception ignored) {}
        }
        return null;
    }

    public List<StudyProgress> getProgressByProfile(int profileId) {
        String sql = "SELECT sp.ID, sp.status, sp.lastAccessed, sp.completionPercentage, sp.completedAt, "
                   + "sp.tblLessonID, sp.tblProfileID "
                   + "FROM tblStudyProgress sp "
                   + "WHERE sp.tblProfileID = ?";
        List<StudyProgress> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, profileId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[StudyProgressDAO] Lỗi getProgressByProfile: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); } catch (Exception ignored) {}
        }
        return list;
    }

    
    public List<StudyProgress> getAll() {
        String sql = "SELECT ID, status, lastAccessed, completionPercentage, completedAt, "
                   + "tblLessonID, tblProfileID FROM tblStudyProgress";
        List<StudyProgress> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[StudyProgressDAO] Lỗi getAll: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); } catch (Exception ignored) {}
        }
        return list;
    }

    
    
    

    
    public boolean saveProgress(StudyProgress progress) {
        String sql = "INSERT INTO tblStudyProgress "
                   + "(status, lastAccessed, completionPercentage, completedAt, tblLessonID, tblProfileID) "
                   + "VALUES (?, CONVERT(time, GETDATE()), ?, ?, ?, ?)";
        java.sql.PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, progress.isStatus());
            ps.setString(2, progress.getCompletionPercentage());
            
            if (progress.getCompletedAt() != null) {
                ps.setTime(3, new java.sql.Time(progress.getCompletedAt().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.TIME);
            }
            
            if (progress.getLesson() != null) {
                ps.setInt(4, progress.getLesson().getId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            
            if (progress.getProfile() != null) {
                ps.setInt(5, progress.getProfile().getId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException e) {
            System.err.println("[StudyProgressDAO] Lỗi saveProgress: " + e.getMessage());
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
        }
    }

    
    public boolean updateProgress(StudyProgress progress) {
        String sql = "UPDATE tblStudyProgress "
                   + "SET status = ?, lastAccessed = CONVERT(time, GETDATE()), "
                   + "    completionPercentage = ?, completedAt = ? "
                   + "WHERE tblProfileID = ? AND tblLessonID = ?";
        java.sql.PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, progress.isStatus());
            ps.setString(2, progress.getCompletionPercentage());
            
            if (progress.getCompletedAt() != null) {
                ps.setTime(3, new java.sql.Time(progress.getCompletedAt().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.TIME);
            }
            
            if (progress.getProfile() != null) {
                ps.setInt(4, progress.getProfile().getId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            
            if (progress.getLesson() != null) {
                ps.setInt(5, progress.getLesson().getId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException e) {
            System.err.println("[StudyProgressDAO] Lỗi updateProgress: " + e.getMessage());
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
        }
    }

    
    public boolean upsertProgress(StudyProgress progress) {
        int profileId = progress.getProfile() != null ? progress.getProfile().getId() : 0;
        int lessonId  = progress.getLesson()  != null ? progress.getLesson().getId()  : 0;
        StudyProgress existing = getProgress(profileId, lessonId);
        if (existing == null) {
            return saveProgress(progress);
        } else {
            return updateProgress(progress);
        }
    }



    
    
    

    
    private StudyProgress mapRow(ResultSet rs) throws SQLException {
        StudyProgress sp = new StudyProgress();
        sp.setId(rs.getInt("ID"));
        sp.setStatus(rs.getBoolean("status"));
        sp.setCompletionPercentage(rs.getString("completionPercentage"));

        
        java.sql.Time lastAcc = rs.getTime("lastAccessed");
        if (lastAcc != null) sp.setLastAccessed(new java.util.Date(lastAcc.getTime()));

        
        java.sql.Time compAt = rs.getTime("completedAt");
        if (compAt != null) sp.setCompletedAt(new java.util.Date(compAt.getTime()));

        
        int lessonId = rs.getInt("tblLessonID");
        if (!rs.wasNull()) {
            Lesson lesson = new Lesson();
            lesson.setId(lessonId);
            sp.setLesson(lesson);
        }

        
        int profileId = rs.getInt("tblProfileID");
        if (!rs.wasNull()) {
            Profile profile = new Profile();
            profile.setId(profileId);
            sp.setProfile(profile);
        }

        return sp;
    }
}
