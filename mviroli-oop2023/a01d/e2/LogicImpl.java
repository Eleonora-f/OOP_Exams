package a01d.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    private int size;
    private boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private void move(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        List<Pair<Integer, Integer>> newStars = new ArrayList<>();
        for (var el : stars) {
            if (x == 0) {
                newStars.add(new Pair<Integer, Integer>(el.getX() - 1, el.getY()));
                if (el.getX() - 1 < 0) {
                    gameOver = true;
                }
            } else if (y == 0) {
                newStars.add(new Pair<Integer, Integer>(el.getX(), el.getY() - 1));
                if (el.getY() - 1 < 0) {
                    gameOver = true;
                }
            } else if (x == size - 1) {
                newStars.add(new Pair<Integer, Integer>(el.getX() + 1, el.getY()));
                if (el.getX() + 1 > size - 1) {
                    gameOver = true;
                }
            } else if (y == size - 1) {
                newStars.add(new Pair<Integer, Integer>(el.getX(), el.getY() + 1));
                if (el.getY() + 1 > size - 1) {
                    gameOver = true;
                }
            }
        }
        stars = newStars;
    }

    private boolean checkCorners(Pair<Integer, Integer> p) {
        return (p.getX() >= 2 && p.getY() >= 2 && p.getX() < size - 2 && p.getY() < size - 2);
    }

    private void addQuad(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        if (checkCorners(pos)) {
            for (int i = x - 2; i <= x + 2; i++) {
                for (int j = y - 2; j <= y + 2; j++) {
                    stars.add(new Pair<Integer, Integer>(i, j));
                }
            }
        }

    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        // System.out.println("POS = " + pos);
        if (stars.isEmpty()) {
            addQuad(pos);
        } else {
            move(pos);
        }
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return List.copyOf(this.stars);
    }

}
