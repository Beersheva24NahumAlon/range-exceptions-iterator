package telran.range;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

    @Test
    void iteratorTest() {
        //TODO
        Range rangeIt = Range.getRange(0, 2);
        Iterator<Integer> it = rangeIt.iterator();
        Integer[] expected = { 0, 1, 2 };
        Integer[] actual = new Integer[expected.length];
        int i = 0;
        while (it.hasNext()) {
            actual[i++] = it.next();
        }
        assertArrayEquals(expected, actual);
        assertThrowsExactly(NoSuchElementException.class, () -> it.next());
    }
}
