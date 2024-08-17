package a01c.e2;

import java.util.*;

public interface Logic {

    void click(Pair<Integer, Integer> position);

    boolean isOver();

    Map<Pair<Integer, Integer>, Integer> getElem();
}
