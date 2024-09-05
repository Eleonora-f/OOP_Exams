package a02a.e2;

import java.util.*;

public interface Logic {

    boolean isOver();

    void click(Pair<Integer, Integer> position);

    Map<Pair<Integer, Integer>, String> getElem();

    Set<Pair<Integer, Integer>> getDisabled();

}
