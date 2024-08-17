package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
    private static final String ERROR_MESSAGE = "max less or equal min";
    private int min;
    private int max;
    private Predicate<Integer> predicate = n -> true; 

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void setPredicate(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public static Range getRange(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return new Range(min, max);
    }

    public void checkNumber(int number) throws OutOfRangeMaxValueException, OutOfRangeMinValueException {
        if (number > max) {
            throw new OutOfRangeMaxValueException(max, number);
        }
        if (number < min) {
            throw new OutOfRangeMinValueException(min, number);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        Integer current = min - 1;

        RangeIterator() {
            current = findNext();
        }

        @Override
        public boolean hasNext() {
            return current <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Integer res = current; 
            current = findNext(); 
            return res;
        }

        private Integer findNext() {
            int cur = current + 1;
            while (cur <= max && !predicate.test(cur)) {
                cur++;
            }
            return cur;
        }
    }
}
