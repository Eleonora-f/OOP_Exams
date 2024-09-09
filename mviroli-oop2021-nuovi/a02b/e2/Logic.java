package a02b.e2;

import java.util.*;

public interface Logic {

    boolean isOver();

    void click();

    Map<Pair<Integer, Integer>, String> getElem();

    Pair<Integer, Integer> getStarPos();

}
