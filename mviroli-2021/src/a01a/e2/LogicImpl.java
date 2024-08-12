package a01a.e2;

import java.util.LinkedList;
import java.util.List;

public class LogicImpl implements Logic {

    private final int size;
    private List<Pair<Integer, Integer>> stars = new LinkedList<>();
    private int pressioni = 0;
    private List<Pair<Integer, Integer>> lastStars = new LinkedList<>();

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        if (pressioni < 3 && !stars.contains(position)) {
            stars.add(position);
            pressioni++;
            lastStars.add(position);
        } else if (stars.contains(position)) {
            stars.remove(position);
            pressioni = 0;
            lastStars.clear();
        }
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return this.stars;
    }

    @Override
    public boolean isOver() {
        return pressioni == 3 && isDiagonal();
    }

    private boolean isAdjacent(Pair<Integer, Integer> curr, Pair<Integer, Integer> p) {
        return Math.abs(curr.getX() - p.getX()) == 1 && Math.abs(curr.getY() - p.getY()) == 1;
    }

    private boolean isDiagonal() {
        for (var p : lastStars) {
            int count = 0;
            for (var e : lastStars) {
                if (!e.equals(p) && isAdjacent(p, e)) {
                    count++;
                }
            }
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

}
