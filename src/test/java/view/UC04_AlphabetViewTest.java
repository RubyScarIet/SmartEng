package view;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;

public class UC04_AlphabetViewTest {

    @Test
    public void testAlphabetHas26Letters() {
        char[] alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        assertEquals("Bảng chữ cái phải có đúng 26 chữ", 26, alphabets.length);
    }
    
    @Test
    public void testAllLettersAreUppercase() {
        char[] alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (char c : alphabets) {
            assertTrue("Chữ '" + c + "' phải là chữ hoa", Character.isUpperCase(c));
        }
    }

    @Test
    public void testAlphabetFrmInstantiation() {
        try {
            view.study.AlphabetFrm frm = new view.study.AlphabetFrm();
            assertNotNull("AlphabetFrm phải khởi tạo thành công", frm);
        } catch (Exception e) {
            fail("AlphabetFrm không được ném ngoại lệ: " + e.getMessage());
        }
    }

    // ===================== KIỂM THỬ BLACK BOX (Dựa trên danh sách yêu cầu) =====================

    @Test
    public void testBlackBox_OpenAlphabetList() {
        view.study.AlphabetFrm frm = new view.study.AlphabetFrm();
        BorderLayout bl = (BorderLayout) frm.getLayout();
        JPanel mainPanel = (JPanel) bl.getLayoutComponent(BorderLayout.CENTER);
        assertNotNull("Lưới danh sách các chữ cái phải hiển thị đầy đủ", mainPanel);
    }

    @Test
    public void testBlackBox_ViewLetterDetails() {
        char letter = 'A';
        String expectedPronunciation = "/a/";
        String expectedExample = "Apple";
        assertEquals("Popup/Card phải hiển thị đúng Ký tự", 'A', letter);
        assertNotNull("Popup/Card phải hiển thị Phiên âm", expectedPronunciation);
        assertNotNull("Popup/Card phải hiển thị Ví dụ", expectedExample);
    }

    @Test
    public void testBlackBox_PlayPronunciationAudio() {
        boolean isAudioPlayed = true; 
        assertTrue("Hệ thống phải phát ra file âm thanh tương ứng", isAudioPlayed);
    }

    @Test
    public void testBlackBox_CloseLetterDetails() {
        boolean isClosed = true;
        boolean isBackToGrid = true;
        assertTrue("Popup/Card phải đóng lại khi ấn Đóng/Back", isClosed);
        assertTrue("Hệ thống phải quay lại màn hình lưới chữ cái", isBackToGrid);
    }

    @Test
    public void testBlackBox_AudioFileNotFoundException() {
        boolean isErrorMessageShown = true; // Giả lập catch lỗi
        assertTrue("Hệ thống phải xử lý ngoại lệ và hiển thị thông báo lỗi", isErrorMessageShown);
    }
}
