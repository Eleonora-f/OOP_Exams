package a02c.e2;

import java.util.List;

public interface Logic {

    List<Pair<Integer, Integer>> getStarPos();

    boolean isOver();

    void click(Pair<Integer, Integer> pos);

}
