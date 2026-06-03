import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import model.DailyMission;

public class UC03_DailyMissionTest {

    private DailyMission mission;

    @Before
    public void setUp() {
        mission = new DailyMission("Hoàn thành 5 bài học", "Học 5 bài trong ngày hôm nay", 5, 100);
    }

    @Test
    public void testConstructorWithParams() {
        assertEquals("Hoàn thành 5 bài học", mission.getTitle());
        assertEquals("Học 5 bài trong ngày hôm nay", mission.getDescription());
        assertEquals(5L, mission.getTargetValue());
        assertEquals(100, mission.getReward());
    }

    @Test
    public void testTargetValueNegative() {
        mission.setTargetValue(-1);
        assertEquals(-1L, mission.getTargetValue()); 
    }

    @Test
    public void testDifferentMissionsHaveDifferentTitles() {
        DailyMission m1 = new DailyMission("Nhiệm vụ A", "Mô tả A", 5, 50);
        DailyMission m2 = new DailyMission("Nhiệm vụ B", "Mô tả B", 10, 100);
        assertNotEquals(m1.getTitle(), m2.getTitle());
    }
}
