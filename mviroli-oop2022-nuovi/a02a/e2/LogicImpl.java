package a02a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private Map<Pair<Integer, Integer>, String> bishop = new HashMap<>();
    private Set<Pair<Integer, Integer>> disabled = new HashSet<>();;
    boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() <= size - 1 && p.getY() >= 0 && p.getY() <= size - 1;
    }

    private void getDiag(Pair<Integer, Integer> pos) {
        // List<Pair<Integer, Integer>> disabled = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();

        int currX = x - 1;
        int currY = y - 1;
        while (currX >= 0 && currY >= 0) {
            disabled.add(new Pair<Integer, Integer>(currX, currY));
            currX--;
            currY--;
        }

        currX = x + 1;
        currY = y + 1;
        while (currX < size && currY < size) {
            disabled.add(new Pair<Integer, Integer>(currX, currY));
            currX++;
            currY++;
        }

        currX = x - 1;
        currY = y + 1;
        while (currX >= 0 && currY < size) {
            disabled.add(new Pair<Integer, Integer>(currX, currY));
            currX--;
            currY++;
        }

        currX = x + 1;
        currY = y - 1;
        while (currX < size && currY >= 0) {
            disabled.add(new Pair<Integer, Integer>(currX, currY));
            currX++;
            currY--;
        }

    }

    private boolean noMoreAvailable() {
        // Controlla se tutte le celle sono occupate o disabilitate
        return bishop.size() + disabled.size() == size * size;
    }

    private void resetGame() {
        bishop.clear();
        disabled.clear();
        gameOver = false;
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        if (isOk(position)) {
            if (!bishop.containsKey(position) && !disabled.contains(position)) {
                bishop.put(position, "B");
                getDiag(position); // disabilita le diag
                // Controlla se non ci sono più mosse disponibili
                if (noMoreAvailable()) {
                    gameOver = true;
                }
            } else if (bishop.containsKey(position) && gameOver) {
                // System.exit(0); consegna con bonus
                resetGame();
                return;
            }

        }
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public Map<Pair<Integer, Integer>, String> getElem() {
        return new HashMap<>(this.bishop);
    }

    @Override
    public Set<Pair<Integer, Integer>> getDisabled() {
        return Set.copyOf(this.disabled);
    }

}
