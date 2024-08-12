package a03a.e2;

public interface Logic {
    boolean hit(Pair<Integer, Integer> position);

    Pair<Integer, Integer> getPlayerT();

    Pair<Integer, Integer> getPCT();

    boolean pcWin();

    boolean playerWin();

    void getPcMove();
}
