package a03c.e2;

import java.util.Set;

public interface Logic {

    void click();

    boolean isOver();

    Set<Pair<Integer, Integer>> getElem();

    Set<Pair<Integer, Integer>> getStarPos();

}
