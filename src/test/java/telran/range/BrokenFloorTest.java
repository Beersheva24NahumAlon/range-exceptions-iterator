package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BrokenFloorTest {
    private int getMinimalBrokenFloor(BallBrokenFloor bbf) {
        int start = 1;
        int finish = bbf.getNFloors();
        int middle = (start + finish) / 2;
        while (start <= finish) {
            try {
                bbf.checkFloor(middle);
                start = middle + 1;
            } catch (Exception e) {
                finish = middle - 1;
            }
            middle = (start + finish) / 2;
        }
        return start;
    }

    @Test
    void minimalBrokenFloorTest() {
        int[] floors = { 200, 17, 1001, 2000 };
        for (int floor : floors) {
            BallBrokenFloor bbf = new BallBrokenFloor(floor);
            assertEquals(bbf.getMinBrokenFloor(), getMinimalBrokenFloor(bbf));
        }
    }
}
