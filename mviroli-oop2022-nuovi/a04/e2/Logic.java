package a04.e2;

import java.util.*;

public interface Logic {

    void movePc();

    boolean click(Pair<Integer, Integer> position);

    Pair<Integer, Integer> getKingPos();

    Pair<Integer, Integer> getRookPos();

    boolean pcWIn();

    boolean playerWIn();

}
