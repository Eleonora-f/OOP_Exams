package a03b.e2;

import java.util.*;

//NON RIESCO A FINIRLO
public class LogicImpl implements Logic {

    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> currPos = null;
    private Random rnd = new Random();
    private int nextNum = 0;
    private Integer size;
    private boolean gameOver = false;
    private Direction currDir;

    public LogicImpl(int size) {
        this.size = size;
        currPos = new Pair<Integer, Integer>(rnd.nextInt(size - 4) + 2, rnd.nextInt(size - 4) + 2);
        elem.put(currPos, nextNum);
    }

    private enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

        private int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private Pair<Integer, Integer> move(Pair<Integer, Integer> pos) {
            return new Pair<Integer, Integer>(pos.getX() + x, pos.getY() + y);
        }

        public Direction next() {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return UP;
                default:
                    return UP;
            }

        }
    }

    @Override
    public void click() {
        // if (this.elem.isEmpty()) {

        // currDir = Direction.UP;
        // return true;
        // }

        for (int i = 0; i < 4; i++) {
            var newP = currDir.move(currPos);
            if (isOk(newP)) {
                currPos = newP;
                elem.put(currPos, nextNum++);
                return true;
            }
            currDir = currDir.next();// cambia direzione se non è valida
        }

        gameOver = true;
        return false;

    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return (pos.getX() >= 0 && pos.getX() < size) && (pos.getY() >= 0 && pos.getY() < size)
                && (!elem.containsKey(pos));
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return new HashMap<>(this.elem);
    }

}
