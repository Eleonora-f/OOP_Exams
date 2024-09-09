package a01b.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> firstClick = null;
    private Pair<Integer, Integer> secClick = null;
    private int size;
    private boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    private boolean isOneTwoAdj() {
        int x1 = secClick.getX();
        int x2 = firstClick.getX();
        int y1 = secClick.getY();
        int y2 = firstClick.getY();
        return x1 == x2 || y1 == y2; // Se sono su stessa colonna allora ok(semplificato)
    }

    private boolean putThree(Pair<Integer, Integer> pos) {
        if (isOneTwoAdj() && secClick != null) { // se son sulla stessa colonna
            return pos.getX() == firstClick.getX() || pos.getY() == firstClick.getY();
        }
        return false;
    }

    private void getCorner(Pair<Integer, Integer> p) {
        int minOneTwo = Math.min(firstClick.getX(), secClick.getX());
        int maxOneTwo = Math.max(firstClick.getX(), secClick.getX());
        int minOneThree = Math.min(firstClick.getY(), p.getY());
        int maxOneThree = Math.max(firstClick.getY(), p.getY());

        for (int i = minOneTwo; i < maxOneTwo; i++) {
            Pair<Integer, Integer> current = new Pair<>(i, firstClick.getY());
            // Controlla se la cella non contiene già '1' o '2' o '3'
            if (!elem.containsKey(current)) {
                elem.put(current, 0); // Asterisco
            }
        }

        for (int j = minOneThree; j < maxOneThree; j++) {
            Pair<Integer, Integer> current = new Pair<>(firstClick.getX(), j);
            // Controlla se la cella non contiene già '1' o '2' o '3'
            if (!elem.containsKey(current)) {
                elem.put(current, 0); // Asterisco
            }
        }

    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (isOk(pos)) {
            if (firstClick == null) { // se è il primo click
                firstClick = pos;
                elem.put(firstClick, 1);
            } else if (secClick == null) { // secondo click
                secClick = pos;
                if (isOneTwoAdj()) {

                    elem.put(secClick, 2);
                }
            } else if (putThree(pos)) { // terzo click
                elem.put(pos, 3);
                getCorner(pos);
                gameOver = true;
            }
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
