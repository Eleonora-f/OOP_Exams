package a01c.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> pos1 = null;
    private Pair<Integer, Integer> pos2 = null;
    private int size = 0;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isFirstQuadr(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < (size / 2) && pos.getY() >= 0 && pos.getY() < (size / 2);

    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < size && pos.getY() >= 0 && pos.getY() < size;

    }

    private void addRect(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        for (int i = p1.getX(); i <= p2.getX(); i++) {
            for (int j = p1.getY(); j <= p2.getY(); j++) {
                elem.put(new Pair<Integer, Integer>(i, j), 0);
            }
        }

    }

    private void expandRect() {
        int newX1 = Math.max(0, pos1.getX() - 1);
        int newX2 = Math.min(size - 1, pos2.getX() + 1);
        int newY1 = Math.max(0, pos2.getY() - 1);
        int newY2 = Math.min(size - 1, pos2.getY() + 1);
        // Aggiorna pos1 e pos2 ai nuovi vertici
        pos1 = new Pair<>(newX1, newY1);
        pos2 = new Pair<>(newX2, newY2);

        // Aggiungi il rettangolo espanso
        addRect(pos1, pos2);
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (elem.isEmpty() && isOk(pos) && isFirstQuadr(pos)) { // primo click
            pos1 = pos;
            elem.put(pos, 1);
        } else if (elem.size() == 1 && pos1 != null && isOk(pos) && !pos.equals(pos1)) { // secondo click
            pos2 = pos;
            elem.put(pos, 2);
            // addRect(lastPos, pos);
        } else if (elem.size() == 2) { // terzo click che riempi la tabella di 0
            addRect(pos1, pos2);
        } else if (elem.size() > 2) { // dal terzo click in poi espandi il rettangolo
            expandRect();
        }
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return new HashMap<>(this.elem);
    }

    @Override
    public boolean isOver() {
        return elem.size() == size * size;
    }

}
