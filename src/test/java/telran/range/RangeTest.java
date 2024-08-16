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
        Range rangeIt1 = Range.getRange(0, 6);
        rangeIt1.setPredicate(n -> n % 2 != 0);
        Integer[] expected1 = { 1, 3, 5 };
        assertForInterators(rangeIt1, expected1);

        Range rangeIt2 = Range.getRange(0, 10);
        rangeIt2.setPredicate(n -> n % 2 == 0);
        Integer[] expected2 = { 0, 2, 4, 6, 8, 10 };
        assertForInterators(rangeIt2, expected2);

        Range rangeIt3 = Range.getRange(0, 50);
        rangeIt3.setPredicate(n -> n % 10 == 0);
        Integer[] expected3 = { 0, 10, 20, 30, 40, 50 };
        assertForInterators(rangeIt3, expected3);

        Range rangeIt4 = Range.getRange(1, 9);
        rangeIt4.setPredicate(n -> n % 10 == 0);
        Integer[] expected4 = {};
        assertForInterators(rangeIt4, expected4);

        Range rangeIt5 = Range.getRange(1, 5);
        Integer[] expected5 = { 1, 2, 3, 4, 5 };
        assertForInterators(rangeIt5, expected5);
    }

    void assertForInterators(Range range, Integer[] expected) {
        Iterator<Integer> it = range.iterator();
        Integer[] actual = new Integer[expected.length];
        int i = 0;
        while (i < actual.length) {
            actual[i++] = it.next();
        }
        assertArrayEquals(expected, actual);
        assertThrowsExactly(NoSuchElementException.class, () -> it.next());
    }
}
