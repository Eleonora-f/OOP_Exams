package a02a.e2;

import java.util.*;

public interface Logic {

    void click(Pair<Integer, Integer> position);

    boolean isOver();

    List<Pair<Integer, Integer>> getStars();

    List<Pair<Integer, Integer>> getObjects();

}
