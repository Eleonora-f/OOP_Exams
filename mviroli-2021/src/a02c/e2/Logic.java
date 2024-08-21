package a02c.e2;

import java.util.*;

public interface Logic {

    List<Pair<Integer, Integer>> getElem();

    Pair<Integer, Integer> getStarPos();

    boolean isOver();

    void hit();

}
