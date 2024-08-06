package a02b.e1;

import java.util.List;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers {

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        if (list.isEmpty()) {
            return null;
        }

        return null;

    }

    @Override
    public Cursor<Integer> naturals() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'naturals'");
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'take'");
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forEach'");
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toList'");
    }

}
