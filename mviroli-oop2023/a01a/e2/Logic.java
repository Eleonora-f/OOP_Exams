package a01a.e2;

import java.util.*;

public interface Logic {
    void click(Pair<Integer, Integer> pos);

    boolean isOver();

    Map<Pair<Integer, Integer>, Integer> getElem();
}
