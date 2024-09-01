package a01c.e2;

import java.util.*;

public interface Logic {

    Map<Pair<Integer, Integer>, Integer> getElem();

    boolean isOver();

    void click(Pair<Integer, Integer> pos);

}
