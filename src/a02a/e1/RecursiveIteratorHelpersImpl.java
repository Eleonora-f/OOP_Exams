package a02a.e1;

import java.util.List;
import java.util.Iterator;

//STRANO, DA FINIRE
public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers {

    @Override
    public <X> RecursiveIterator<X> fromList(List<X> list) {
        if (list.isEmpty()) {
            return null;
        }
        return new RecursiveIterator<X>() {
            Iterator<X> iterator = list.iterator();
            X currentElem = iterator.hasNext() ? iterator.next() : null;

            @Override
            public X getElement() {
                return currentElem;
            }

            @Override
            public RecursiveIterator<X> next() {
                if (iterator.hasNext()) {
                    currentElem = iterator.next();
                    return this;
                } else {
                    return null;
                }
            }

        };

    }

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        return new RecursiveIterator<X>() {

            @Override
            public X getElement() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getElement'");
            }

            @Override
            public RecursiveIterator<X> next() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'next'");
            }
            
        }
    }

    @Override
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'zip'");
    }

    @Override
    public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'zipWithIndex'");
    }

    @Override
    public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alternate'");
    }

}
