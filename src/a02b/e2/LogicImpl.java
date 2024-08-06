package a02b.e2;

import java.util.*;

//DA FINIRE, E' CONFUSO
public class LogicImpl implements Logic {

    private int size;
    private Set<Pair<Integer, Integer>> stars = new HashSet<>();
    private Set<Pair<Integer, Integer>> enabled = new HashSet<>();

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isDiagonal() {

        for (var el : stars) {
            int x = el.getX();
            int y = el.getY();

            Set<Pair<Integer, Integer>> diag1 = new HashSet<>();
            for (int i = 0; i < size; i++) {
                diag1.add(new Pair<>(i, y - x + i));
            }
            diag1.retainAll(stars);

            Set<Pair<Integer, Integer>> diag2 = new HashSet<>();
            for (int i = 0; i < size; i++) {
                diag2.add(new Pair<>(i, y + x - i)); // prende tutta la diagonale passando per una specifica cella
            }
            diag2.retainAll(stars);

            if (diag1.size() == 3) { // Se diagonal1 contiene esattamente 3 elementi, queste celle vengono aggiunte a
                                     // enabled, stars viene svuotato. Il metodo restituisce true
                                     // indicando che è stata trovata una diagonale valida.
                enabled.addAll(diag1);
                stars.clear();
                return true;
            } else if (diag2.size() == 3) {
                enabled.addAll(diag2);
                stars.clear();
                return true;
            }

        }
        return false;
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        if (stars.contains(pos)) {
            stars.remove(pos); // se clicci un *, toglilo
        } else {
            stars.add(pos); // altrimenti se è vuota aggiungila
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getStars() {
        return this.stars;
    }

    @Override
    public void reset() {
        stars.clear();
        enabled.clear();
    }

    @Override
    public Set<Pair<Integer, Integer>> getEnabled() {
        return this.enabled;
    }

    @Override
    public void checkButton() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkButton'");
    }

}
