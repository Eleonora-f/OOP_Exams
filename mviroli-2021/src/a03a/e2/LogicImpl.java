package a03a.e2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogicImpl implements Logic {

    Pair<Integer, Integer> lastClick;
    private Set<Pair<Integer, Integer>> stars = new HashSet<>();

    private boolean isFirstClick;
    private int size;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(Pair<Integer, Integer> p) {
        if (stars.isEmpty() && inFirstQuadr(p)) {// se non è già stato inserito ed è nel primo quadrante PRIMO
                                                 // RETTANGOLO
            lastClick = p;
            addRect(p, new Pair<Integer, Integer>((size - 1) - p.getX(), (size - 1) - p.getY()));
        } else if (!stars.contains(p) && inFirstQuadr(p)
                && (p.getX() > lastClick.getX() && p.getY() > lastClick.getY())) { // deve
            // riuscire
            // a
            // stare
            // dentro
            // l'altro
            // rettangolo
            lastClick = p;
            addRect(p, new Pair<Integer, Integer>((size - 1) - p.getX(), (size - 1) - p.getY()));
        }
    }

    @Override
    public boolean isOver() {
        return this.stars.contains(new Pair<>((size / 2) - 1, (size / 2) - 1));
    }

    @Override
    public Set<Pair<Integer, Integer>> getElem() {
        return this.stars;
    }

    private boolean inFirstQuadr(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() <= ((size / 2) - 1) && pos.getY() >= 0 && pos.getY() <= ((size / 2) - 1);

    }

    // faccio un for per ognuno dei 4
    // lati
    private void addRect(Pair<Integer, Integer> topLeft, Pair<Integer, Integer> bottRight) {
        // Add points for the top edge of the rectangle
        for (int y = topLeft.getY(); y <= bottRight.getY(); y++) {
            stars.add(new Pair<>(topLeft.getX(), y));
        }

        // Add points for the bottom edge of the rectangle
        for (int y = topLeft.getY(); y <= bottRight.getY(); y++) {
            stars.add(new Pair<>(bottRight.getX(), y));
        }

        // Add points for the left edge of the rectangle
        for (int x = topLeft.getX(); x <= bottRight.getX(); x++) {
            stars.add(new Pair<>(x, topLeft.getY()));
        }

        // Add points for the right edge of the rectangle
        for (int x = topLeft.getX(); x <= bottRight.getX(); x++) {
            stars.add(new Pair<>(x, bottRight.getY()));
        }
    }

}
