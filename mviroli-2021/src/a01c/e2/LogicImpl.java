package a01c.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;

    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> lastPos;
    private boolean vertical = false; // true=vertical, false=horizontal
    private boolean firstMove = true;

    public LogicImpl(int size) {
        this.size = size;
    }

    private void insertStars(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) { // da coreeggere

        var minCol = Math.min(p1.getY(), p2.getY());
        var maxCol = Math.max(p1.getY(), p2.getY());
        var minRow = Math.min(p1.getX(), p2.getX());
        var maxRow = Math.max(p1.getX(), p2.getX());

        for (int x = minRow; x <= maxRow; x++) {
            for (int y = minCol; y <= maxCol; y++) {
                elem.put(new Pair<Integer, Integer>(x, y), 0);
            }
        }

    }

    private boolean isMoveValid(Pair<Integer, Integer> newPos) {
        if (vertical) {
            return lastPos.getY() == newPos.getY(); // Movimento verticale
        } else {
            return lastPos.getX() == newPos.getX(); // Movimento orizzontale
        }
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        if (!elem.containsKey(position)) {
            if (elem.isEmpty()) { // oppure lastPos == null
                elem.put(position, 0);
                lastPos = position;
                firstMove = false;
            } else if (isMoveValid(position)) {
                insertStars(lastPos, position);
                elem.put(position, 0);
                lastPos = position;
                vertical = !vertical;
                // if (firstMove) {
                // firstMove = false;
                // vertical = (lastPos.getY() == position.getY());
                // } else {
                // vertical = !vertical;
                // }
            }
        }
    }

    @Override
    public boolean isOver() {
        return false;
        // this.elem.size() == (size * size);
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return this.elem;
    }

}
