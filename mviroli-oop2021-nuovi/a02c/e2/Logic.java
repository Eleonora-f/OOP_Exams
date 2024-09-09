package a02c.e2;

import java.util.List;

public interface Logic {

    void click(Pair<Integer, Integer> pos);

    boolean isOver();

    List<Pair<Integer, Integer>> getElem();

    Pair<Integer, Integer> getStarPos();

}
