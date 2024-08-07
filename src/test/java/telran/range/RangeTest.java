package telran.range;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

public class RangeTest {
    Range range = Range.getRange(5, 10);

    @Test
    void wrongRageCreatingTest() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Range.getRange(3, 2));
    }

    @Test
    void rightNumberTest() throws OutOfRangeMaxValueException, OutOfRangeMinValueException {
            range.checkNumber(7);
    }

    @Test
    void wrongNumberTest() throws OutOfRangeMaxValueException, OutOfRangeMinValueException {
        assertThrowsExactly(OutOfRangeMaxValueException.class, () -> range.checkNumber(11));
        assertThrowsExactly(OutOfRangeMinValueException.class, () -> range.checkNumber(4));
    }
}
