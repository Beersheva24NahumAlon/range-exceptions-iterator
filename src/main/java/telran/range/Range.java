package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
    private static final String ERROR_MESSAGE = "max less or equal min";
    private int min;
    private int max;
    private Predicate<Integer> predicate = null; 

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
        int current;

        @Override
        public boolean hasNext() {
            //TODO
            return false;
        }

        @Override
        public Integer next() {
            //TODO
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return -1;
        }

    }
}
