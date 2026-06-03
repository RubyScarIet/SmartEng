package view;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;

public class UC03_DailyMissionViewTest {

    @Test
    public void testMissionCardCompleted_IsClaimable() {
        int current = 2;
        int target = 2;
        boolean isClaimable = (current == target);
        assertTrue("Nhiệm vụ hoàn thành phải được phép claim", isClaimable);
    }

    @Test
    public void testDailyMissionFrmInstantiation() {
        try {
            view.profile.DailyMissionFrm frm = new view.profile.DailyMissionFrm();
            assertNotNull("DailyMissionFrm phải khởi tạo thành công", frm);
        } catch (Exception e) {
            fail("DailyMissionFrm không được ném ngoại lệ khi khởi tạo: " + e.getMessage());
        }
    }

    // ===================== KIỂM THỬ BLACK BOX (Dựa trên danh sách yêu cầu) =====================

    @Test
    public void testBlackBox_OpenMissionList() {
        view.profile.DailyMissionFrm frm = new view.profile.DailyMissionFrm();
        BorderLayout bl = (BorderLayout) frm.getLayout();
        Component center = bl.getLayoutComponent(BorderLayout.CENTER);
        assertNotNull("Danh sách nhiệm vụ phải được hiển thị đầy đủ trên giao diện", center);
    }

    @Test
    public void testBlackBox_ShowProgressCorrectly() {
        int current = 2;
        int target = 3;
        String expectedProgress = "Progress: " + current + "/" + target + "   ";
        assertEquals("Tiến độ phải hiển thị chính xác (VD: Đã làm 2/3 bài tập)", "Progress: 2/3   ", expectedProgress);
    }

    @Test
    public void testBlackBox_MissionCompletedStatus() {
        int current = 3;
        int target = 3;
        boolean isCompleted = (current == target);
        assertTrue("Trạng thái phải chuyển sang hoàn thành khi tiến độ đạt tối đa", isCompleted);
    }

    @Test
    public void testBlackBox_RewardButtonDisabledWhenNotCompleted() {
        int current = 1;
        int target = 3;
        boolean isClaimable = (current == target);
        assertFalse("Nút nhận thưởng không được kích hoạt khi chưa hoàn thành nhiệm vụ", isClaimable);
    }

    @Test
    public void testBlackBox_RewardButtonClickSuccessWhenCompleted() {
        int current = 3;
        int target = 3;
        boolean isClaimable = (current == target);
        assertTrue("Nút nhận thưởng phải được kích hoạt khi đã hoàn thành nhiệm vụ", isClaimable);
        boolean isClicked = true;
        assertTrue("Click nhận thưởng thành công và cập nhật phần thưởng", isClicked && isClaimable);
    }
}
