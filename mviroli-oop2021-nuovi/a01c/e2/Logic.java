package a01c.e2;

import java.util.*;

public interface Logic {

    void click(Pair<Integer, Integer> pos);

    boolean isOver();

    List<Pair<Integer, Integer>> getElem();

}
