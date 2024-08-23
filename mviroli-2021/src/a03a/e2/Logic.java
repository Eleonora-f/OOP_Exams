package a03a.e2;

import java.util.*;

public interface Logic {

    void hit(Pair<Integer, Integer> p);

    boolean isOver();

    Set<Pair<Integer, Integer>> getElem();
}
