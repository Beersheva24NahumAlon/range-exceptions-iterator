package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BrokenFloorTest {
    private int getMinimalBrokenFloor(BallBrokenFloor bbf) {
        // TODO
        return -1;
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
