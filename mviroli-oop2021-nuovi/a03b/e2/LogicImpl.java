package a03b.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> currPos = null;
    private Random rnd = new Random();
    private Direction dir = Direction.UP;
    private int num = 0;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return new HashMap<>(this.elem);
    }

    private enum Direction {

        UP(-1, 0), DOWN(1, 0), RIGHT(0, 1), LEFT(0, -1);

        private int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair<Integer, Integer> move(Pair<Integer, Integer> p) {
            return new Pair<Integer, Integer>(p.getX() + x, p.getY() + y);
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
                    throw new IllegalStateException();

            }
        }

    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {

        if (elem.isEmpty()) {
            this.currPos = new Pair<>(rnd.nextInt((size - 2) - 2) + 2, rnd.nextInt((size - 2) - 2) + 2);
            elem.put(currPos, num);
        } else {

            for (int i = 0; i < 4; i++) {
                Pair<Integer, Integer> newP = dir.move(currPos);
                if (isOk(newP) && !elem.containsKey(newP)) {
                    currPos = newP;
                    num++;
                    elem.put(currPos, num);
                    return;
                }

                dir = dir.next();
            }
            /*
             * var cont = new Pair<>(currPos.getX(), currPos.getY() - 1);
             * if (!elem.containsKey(cont)) {
             * newP = cont;
             * }
             */

        }
    }

}
