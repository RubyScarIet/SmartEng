package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlphabetDAO extends DAO {
    public AlphabetDAO() { super(); }

    public List<Object[]> getAllLetters() {
        List<Object[]> letters = new ArrayList<>();
        if (con == null) return letters;
        try {
            String sql = "SELECT character, type, exampleWord FROM tblLetter WHERE tblLanguageID = 1 ORDER BY character ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                letters.add(new Object[] {
                    rs.getString("character"),
                    rs.getString("type"), // could be pronunciation
                    rs.getString("exampleWord")
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return letters;
    }
}
