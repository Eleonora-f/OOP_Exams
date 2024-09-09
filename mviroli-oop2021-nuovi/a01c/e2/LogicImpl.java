package a01c.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private List<Pair<Integer, Integer>> elem = new ArrayList<>();
    private Pair<Integer, Integer> currPos = null;
    private boolean horiz = false;
    private boolean vert = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    private void horizontal(Pair<Integer, Integer> pos) {

        int minY = Math.min(currPos.getY(), pos.getY());
        int maxY = Math.max(currPos.getY(), pos.getY());
        for (int i = minY; i <= maxY; i++) {
            elem.add(new Pair<>(currPos.getX(), i));
        }
    }

    private void vertical(Pair<Integer, Integer> pos) {
        int minx = Math.min(currPos.getX(), pos.getX());
        int maxX = Math.max(currPos.getX(), pos.getX());
        for (int i = minx; i <= maxX; i++) {
            elem.add(new Pair<>(i, currPos.getY()));
        }

    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (isOk(pos) && !elem.contains(pos)) {
            if (currPos == null) { // primo click
                currPos = pos;
                elem.add(currPos);
            } else {
                if (currPos.getX() == pos.getX() && !horiz) {
                    horizontal(pos);
                    currPos = pos;
                    horiz = true;
                    vert = false;
                } else if (currPos.getY() == pos.getY() && !vert) {
                    vertical(pos);
                    currPos = pos;
                    horiz = false;
                    vert = true;
                }
            }

        }
    }

    @Override
    public boolean isOver() {
        return false;
        // return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getElem() {
        return List.copyOf(this.elem);
    }

}
