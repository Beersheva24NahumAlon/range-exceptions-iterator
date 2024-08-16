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
        int current = min;

        RangeIterator() {
            Integer first = find();
            this.current = first != null ? first : min;
        }

        @Override
        public boolean hasNext() {
            return find() != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int res = find();
            current = res + 1;
            return res;
        }

        private Integer find() {
            int cur = current;
            while (!predicate.test(cur) && cur <= max) {
                cur++;
            }
            return cur > max ? null : cur;
        }
    }
}
