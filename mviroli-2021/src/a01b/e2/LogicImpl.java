package a01b.e2;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;

public class LogicImpl implements Logic {

    private final int size;
    private Set<Pair<Integer, Integer>> stars = new HashSet<>();
    private int addStar = 0;
    private int removeStar = 0;

    public LogicImpl(int size) {
        this.size = size;
    }

    private List<Pair<Integer, Integer>> getAdj(Pair<Integer, Integer> p) {
        List<Pair<Integer, Integer>> adj = new LinkedList<>();
        // sono tutti i casi di diagonali
        adj.add(new Pair<Integer, Integer>(p.getX() - 1, p.getY() - 1));
        adj.add(new Pair<Integer, Integer>(p.getX() + 1, p.getY() + 1));
        adj.add(new Pair<Integer, Integer>(p.getX() - 1, p.getY() + 1));
        adj.add(new Pair<Integer, Integer>(p.getX() + 1, p.getY() - 1));

        return adj;
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        removeStar = 0;
        addStar = 0;
        for (var el : getAdj(position)) { // sfoglia tutte le pos in diago della pos cliccata e controlla se c'è già una
                                          // * o meno
            if (!this.stars.contains(el)) {
                this.stars.add(el);
                addStar++;
            } else {
                this.stars.remove(el);
                removeStar++;
            }
        }
    }

    @Override
    public boolean isOver() {
        return addStar == 1 && removeStar == 3;
    }

    @Override
    public Set<Pair<Integer, Integer>> getStars() {
        return this.stars;
    }

}
