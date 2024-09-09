package a01c.e1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class EventHistoryFactoryImpl implements EventHistoryFactory {

    @Override
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        return new EventHistory<E>() {

            private Pair<Double, E> p = iterator.next();

            @Override
            public double getTimeOfEvent() {
                return this.time;

            }

            @Override
            public E getEventContent() {

                return event;

            }

            @Override
            public boolean moveToNextEvent() {
                if (timeNext >= time) {
                    map.put(timeNext, event);
                }
                return false;

            }

        };
    }

    @Override
    public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromIterators'");
    }

    @Override
    public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromListAndDelta'");
    }

    @Override
    public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromRandomTimesAndSupplier'");
    }

    @Override
    public EventHistory<String> fromFile(String file) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromFile'");
    }

}
