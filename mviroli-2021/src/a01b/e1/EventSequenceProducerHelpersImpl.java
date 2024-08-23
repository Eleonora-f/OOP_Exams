package a01b.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers {

    @Override
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                return iterator.next();
            }

        };
    }

    @Override
    public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
        List<E> list = new ArrayList<>();
        try {
            while (true) {
                Pair<Double, E> event = sequence.getNext();

                if (event.get1() >= fromTime && event.get1() <= toTime) {
                    list.add(event.get2());
                }
            }
        } catch (NoSuchElementException e) {

        }

        return list;
    }

    @Override
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {

        return new Iterable<E>() {

            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    Pair<Double, E> nextEvent = null;
                    private boolean hasMore = true;

                    @Override
                    public boolean hasNext() {
                        if (nextEvent == null) {
                            try {
                                nextEvent = sequence.getNext();
                            } catch (NoSuchElementException e) {
                                // hasMore = false;
                            }
                        }
                        return nextEvent != null;
                    }

                    @Override
                    public E next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }

                        E eventContent = nextEvent.get2();
                        nextEvent = null;
                        return eventContent;
                    }

                };
            }

        };

    }

    @Override
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {

        try {
            while (true) {
                Pair<Double, E> event = sequence.getNext();
                if (event.get1() > time) {
                    return Optional.of(event);
                }
            }

        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

}
