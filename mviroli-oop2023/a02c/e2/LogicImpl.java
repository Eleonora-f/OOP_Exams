package a02c.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    Pair<Integer, Integer> currPos;
    private int size;
    private boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private void addSquare(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (x != i || y != j) {
                    stars.add(new Pair<Integer, Integer>(i, j));
                }

            }
        }
    }

    private void expandSquare(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        if(pos)
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (stars.isEmpty()) {// primo click
            addSquare(pos);
        } else {
            expandSquare(pos);
        }
    }

    @Override
    public List<Pair<Integer, Integer>> getStarPos() {
        return List.copyOf(stars);
    }

    @Override
    public boolean isOver() {
        return false;
    }

}
