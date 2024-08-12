package a04.e2;

public interface Logic {

    boolean hit(Pair<Integer, Integer> position);

    Pair<Integer, Integer> getPcK();

    Pair<Integer, Integer> getPlayerT();

    void computerMove();

    boolean isOver();

    void reset();
}
