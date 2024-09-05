package a01b.e2;

import java.util.*;

public interface Logic {

    void click(Pair<Integer, Integer> position);

    boolean isOver();

    List<Pair<Integer, Integer>> getStars();

}
