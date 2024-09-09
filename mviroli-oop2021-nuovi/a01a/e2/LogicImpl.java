package a01a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private boolean gameOver = false;

    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> currPos = null; // ci salvo l'1

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < size && pos.getY() >= 0 && pos.getY() < size;
    }

    private void getRect(Pair<Integer, Integer> p2) {
        int x1 = Math.min(currPos.getX(), p2.getX());
        int y1 = Math.min(currPos.getY(), p2.getY());
        int x2 = Math.max(currPos.getX(), p2.getX());
        int y2 = Math.max(currPos.getY(), p2.getY());
        // uno per
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                elem.put(new Pair<Integer, Integer>(i, j), 0);
            }

        }
    }

    private boolean isRect(Pair<Integer, Integer> pos) { // per escludere che vengano presi quadrati
        int diffX = Math.abs(pos.getX() - currPos.getX());
        int diffY = Math.abs(pos.getY() - currPos.getY());

        // Escludi quadrati e seleziona solo rettangoli, dove uno dei due lati è più
        // lungo dell'altro
        return (diffX > diffY) || (diffY > diffX);
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        if (currPos == null && isOk(position) && !elem.containsKey(position)) {
            currPos = position;
            elem.put(currPos, 1);

        } else if (currPos != null && isOk(position)) { // faccio selezionare rett. solo dall'alto verso il basso
            if (isRect(position) && position.getX() != currPos.getX() || position.getY() != currPos.getY()) {
                elem.put(position, 0);
                getRect(position);
            }
            currPos = null;
        }

        if (elem.size() == size * size) {
            gameOver = true;
        }
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
