package dao;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class AlphabetDAOTest {

    @Test
    public void testGetAllLetters_ReturnsDataFromDB() {
        AlphabetDAO dao = new AlphabetDAO();
        List<Object[]> letters = dao.getAllLetters();
        
        assertNotNull("Danh sách chữ cái không được null", letters);
        // Lưu ý: Test này giả định bảng tblLetter đã có dữ liệu
        assertTrue("Cơ sở dữ liệu phải trả về ít nhất 1 chữ cái", letters.size() > 0);
        
        // Kiểm tra cấu trúc của dòng dữ liệu đầu tiên (character, type, exampleWord)
        Object[] firstLetter = letters.get(0);
        assertEquals("Mỗi dòng phải có đúng 3 trường dữ liệu theo truy vấn SQL", 3, firstLetter.length);
        
        // Kiểm tra kiểu dữ liệu
        assertNotNull("Character không được rỗng", firstLetter[0]);
        assertTrue("Character phải là String", firstLetter[0] instanceof String);
    }
}
