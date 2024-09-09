package a03a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic {

    private Set<Pair<Integer, Integer>> elem = new HashSet<>();
    private Pair<Integer, Integer> firstPos = null;
    private int size;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    private void getRect() {
        var oppV = new Pair<>((size - 1) - firstPos.getX(), (size - 1) - firstPos.getY());
        for (int i = firstPos.getX(); i < oppV.getX(); i++) {
            elem.add(new Pair<Integer, Integer>(i, firstPos.getY()));
        }
        for (int j = firstPos.getY(); j < oppV.getY(); j++) {
            elem.add(new Pair<Integer, Integer>(firstPos.getX(), j));
        }
        for (int i = firstPos.getX(); i < oppV.getX(); i++) {
            elem.add(new Pair<Integer, Integer>(i, oppV.getY()));
        }
        for (int j = firstPos.getY(); j < oppV.getY(); j++) {
            elem.add(new Pair<Integer, Integer>(oppV.getX(), j));
        }
        elem.add(oppV);
    }

    @Override
    public Set<Pair<Integer, Integer>> getElem() {
        return Set.copyOf(this.elem);
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (isOk(pos)) { // primo rettangolo
            if (elem.isEmpty()) {
                firstPos = pos;
                getRect();
            } else if (pos.getX() > firstPos.getX() && pos.getY() > firstPos.getY()) {
                firstPos = pos;
                getRect();
            }
        }
    }

}
