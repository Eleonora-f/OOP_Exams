package a02b.e2;

import java.util.*;

public interface Logic {

    void hit(Pair<Integer, Integer> position);

    void reset();

    Set<Pair<Integer, Integer>> getStars();

    Set<Pair<Integer, Integer>> getEnabled();

    void checkButton();

}
