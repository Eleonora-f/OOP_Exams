package a01b.e2;

import java.util.*;

public interface Logic {

    boolean isOver();

    void click(Pair<Integer, Integer> pos);

    Map<Pair<Integer, Integer>, Integer> getElem();

}
