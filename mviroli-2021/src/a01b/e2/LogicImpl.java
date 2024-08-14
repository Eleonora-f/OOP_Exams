package a01b.e2;

import java.util.*;

public class LogicImpl implements Logic {
    // FATTO DA ME, non inserisce il 3 finale
    private int size;
    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> onePos;
    private Pair<Integer, Integer> twoPos;
    private boolean one = true;
    private boolean two = false;
    boolean disabled = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private void insertStars(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
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

    @Override
    public void click(Pair<Integer, Integer> position) {
        if (disabled) {
            return;
        }
        if (one && !elem.containsKey(position)) {
            onePos = position;
            elem.put(onePos, 1);
            one = false;
            two = true;
        } else if (two && !elem.containsKey(position)) {
            if (isTwoOK(position)) {
                twoPos = position;
                elem.put(twoPos, 2);
                two = false;
            }

        } else if (!disabled) {// faccio insertstart qui perchè la consegna mi chiede di riempire le righe alla
            // fine dei 3 click
            if (isPerp(position)) {
                elem.put(position, 3);
                insertStars(onePos, twoPos);
                insertStars(onePos, position);
                disabled = true; // disabilito il gioco dopo l'inserimento delle stelle
            }
        }

    }

    private boolean isPerp(Pair<Integer, Integer> position) {// per gestire il terzo click in che 1-2-3 creino un angolo
                                                             // retto
        return (onePos.getX() == position.getX() && onePos.getY() == twoPos.getY())
                || (onePos.getY() == position.getY() && onePos.getX() == twoPos.getX());
    }

    private boolean isTwoOK(Pair<Integer, Integer> pos) { // il n2 può essere o sulla stessa riga o sulla stessa colonna
                                                          // di 1
        return pos.getX() == onePos.getX() || pos.getY() == onePos.getY();
    }

    @Override
    public boolean isOver() { // quando tutte le celle vengono disabilitate
        return disabled;
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return this.elem;
    }

}
