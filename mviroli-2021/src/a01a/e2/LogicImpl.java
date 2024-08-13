package a01a.e2;

import java.util.Map;
import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    boolean one = true;
    private Pair<Integer, Integer> lastOne;
    Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        if (one && !elem.containsKey(position)) {
            elem.put(position, 1);
            lastOne = position;
            one = false;
        } else {
            if (!elem.containsKey(position)) {
                for (var el : getRectangle(position, lastOne)) {
                    elem.put(el, 0);
                }
                elem.replace(lastOne, 0);
                one = true;
                lastOne = null;
            }
        }
    }

    public Set<Pair<Integer, Integer>> getRectangle(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        int minRow = Math.min(p1.getX(), p2.getX());
        int maxRow = Math.max(p1.getX(), p2.getX());
        int minCol = Math.min(p1.getY(), p2.getY());
        int maxCol = Math.max(p1.getY(), p2.getY());

        Set<Pair<Integer, Integer>> list = new HashSet<>();
        for (int x = minRow; x <= maxRow; x++) {
            for (int y = minCol; y <= maxCol; y++) {
                var p = new Pair<>(x, y);
                if (!elem.containsKey(p)) {
                    list.add(p);
                }
            }
        }
        return list;
    }

    @Override
    public boolean isOVer() {
        return this.elem.size() == (size * size);
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return this.elem;
    }

}
