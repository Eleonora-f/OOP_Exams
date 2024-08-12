package a01b.e2;

import java.util.Set;

public interface Logic {

    public void hit(Pair<Integer, Integer> position);

    public boolean isOver();

    Set<Pair<Integer, Integer>> getStars();
}
