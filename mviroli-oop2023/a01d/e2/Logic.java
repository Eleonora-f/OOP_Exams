package a01d.e2;

import java.util.*;

public interface Logic {

    void click(Pair<Integer, Integer> pos);

    boolean isOver();

    List<Pair<Integer, Integer>> getStars();

}
