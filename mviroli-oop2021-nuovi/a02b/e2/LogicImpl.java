package a02b.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicImpl implements Logic {

    private int size;
    private Map<Pair<Integer, Integer>, String> elem = new HashMap<>();
    private Pair<Integer, Integer> currPos = null;
    private String direction = "up";
    private Random rnd = new Random();
    private boolean gameOver;

    public LogicImpl(int size) {
        this.size = size;
        this.currPos = new Pair<Integer, Integer>(size - 1, rnd.nextInt(size));
        while (elem.size() < 20) {
            var randLetter = rnd.nextBoolean() ? "L" : "R";
            elem.put(new Pair<>(rnd.nextInt(size), rnd.nextInt(size)), randLetter);
        }
    }

    /*
     * private enum Direction {
     * UP(-1, 0), DOWN(1, 0), RIGHT(0, 1), LEFT(0, -1);
     * private final int x, y;
     * private Direction(int x, int y) {
     * this.x = x;
     * this.y = y;
     * }
     * public Pair<Integer, Integer> getNextPos(Pair<Integer, Integer> currPos) {
     * return new Pair<Integer, Integer>(currPos.getX() + x, currPos.getY() + y);
     * }
     * public static Direction changeDir(Direction current, String letter) {
     * if (letter.equals("R")) {
     * return current == UP ? RIGHT : current;
     * } else if (letter.equals("L")) {
     * return current == UP ? LEFT : current;
     * }
     * return current;
     * }
     * }
     */

    @Override
    public boolean isOver() {
        return gameOver;
    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    @Override
    public void click() {
        Pair<Integer, Integer> newPos = null;
        switch (direction) {
            case "up":
                newPos = new Pair<Integer, Integer>(currPos.getX() - 1, currPos.getY());
                break;
            case "right":
                newPos = new Pair<Integer, Integer>(currPos.getX(), currPos.getY() + 1);
                break;
            case "left":
                newPos = new Pair<Integer, Integer>(currPos.getX(), currPos.getY() - 1);
                break;
        }
        if (isOk(newPos)) {
            currPos = newPos;
            if (elem.containsKey(currPos)) {
                String letter = elem.get(currPos);
                if (letter.equals("R")) {
                    direction = "right";
                } else if (letter.equals("L")) {
                    direction = "left";
                }
            }

        } else {
            gameOver = true;
        }
    }

    @Override
    public Map<Pair<Integer, Integer>, String> getElem() {
        return new HashMap<>(this.elem);
    }

    @Override
    public Pair<Integer, Integer> getStarPos() {
        return this.currPos;
    }

}
