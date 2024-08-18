package a02a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    int size = 0;
    private Map<Pair<Integer, Integer>, Integer> cells = new HashMap<>();
    private Pair<Integer, Integer> currPos;
    private int nextNum = 0; // Numero da inserire
    private Direction currDirection;
    private Random rnd = new Random();
    private boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private enum Direction {
        UP(-1, 0), DOWN(1, 0), RIGHT(0, 1), LEFT(0, -1);

        private int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Metodo per ottenere la nuova posizione in base alla direzione
        public Pair<Integer, Integer> move(Pair<Integer, Integer> pos) { // per ottenere la nuova pos
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
            }
            return UP;
        }

    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return (p.getX() >= 0 && p.getX() < size) && (p.getY() >= 0 && p.getY() < size) && (!this.cells.containsKey(p));
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return this.cells;
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public boolean click() {
        if (this.cells.isEmpty()) { // se è il primo click
            currPos = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
            cells.put(currPos, nextNum++);
            currDirection = Direction.UP;
            return true;
        }

        for (int i = 0; i < 4; i++) {
            // Trova la nuova posizione nella direzione corrente
            var newP = currDirection.move(currPos);

            if (isOk(newP)) {
                currPos = newP;
                cells.put(currPos, nextNum++);
                return true;
            }

            currDirection = currDirection.next(); // Cambia direzione se non è valida
        }

        gameOver = true;
        return false;

    }

}
