package a03a.e2;

import java.util.*;

public interface Logic {

    boolean click(Pair<Integer, Integer> position);

    Pair<Integer, Integer> getHumanPos();

    Pair<Integer, Integer> getPcPos();

    boolean pcWin();

    boolean playWin();

    void movePC();

    // void reset();
}
