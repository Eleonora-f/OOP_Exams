package a03b.e2;

import java.util.*;

public interface Logic {

    List<Pair<Integer, Integer>> getStars();

    boolean isOver();

    void click(Pair<Integer, Integer> pos);

    Pair<Integer, Integer> getObjPos();

}
