package a01a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    private boolean gameOver = false;
    Queue<Pair<Integer, Integer>> lastsThreeQue = new LinkedList<>();
    private Pair<Integer, Integer> prevStar = null;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < size && pos.getY() >= 0 && pos.getY() < size;
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (isOk(pos)) {

            if (!stars.contains(pos)) { // se non c'è la star mettila
                stars.add(pos);
                lastsThreeQue.add(pos);

                if (lastsThreeQue.size() > 3) {
                    lastsThreeQue.poll(); // rimuovi il più vecchio se ci sono già 3 elem
                }

                if (lastThree()) {
                    gameOver = true;
                }
            } else if (stars.contains(pos)) { // se c'è toglila
                stars.remove(pos);
                lastsThreeQue.remove(pos);
            }
        }
    }

    // OPZIONE 1:mia
    private boolean isDiag(Pair<Integer, Integer> curr) {
        if (prevStar == null) {
            prevStar = curr;
            return true; // Se non c'è una posizione precedente, consideriamo valida la prima mossa
        }
        int x1 = prevStar.getX();
        int y1 = prevStar.getY();
        int x2 = curr.getX();
        int y2 = curr.getY();

        // Verifica se la nuova posizione è in diagonale rispetto alla precedente
        if (x2 == x1 + 1 && (y2 == y1 + 1 || y2 == y1 - 1)) {
            prevStar = curr; // Aggiorna la posizione precedente
            return true;
        }

        return false;
    }

    private boolean lastThree() {
        if (lastsThreeQue.size() < 3) {
            return false;
        }
        // Reset di prevStar prima di iniziare il controllo
        prevStar = null;
        // Verifica la validità diagonale per ogni elemento nella coda
        for (Pair<Integer, Integer> pos : lastsThreeQue) {
            if (!isDiag(pos)) {
                return false;
            }
        }

        return true; // Tutte le mosse sono valide in diagonale
    }

    // OPZIONE 2:chat
    /*
     * private boolean isDiag(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2)
     * {
     * int x1 = p1.getX();
     * int y1 = p1.getY();
     * int x2 = p2.getX();
     * int y2 = p2.getY();
     * 
     * return (x2 == x1 + 1 && (y2 == y1 + 1 || y2 == y1 - 1));
     * }
     * 
     * private boolean lastThree() {
     * if (lastsThreeQue.size() < 3) {
     * return false;
     * }
     * List<Pair<Integer, Integer>> list = new ArrayList<>(lastsThreeQue);
     * 
     * Pair<Integer, Integer> first = list.get(0);
     * Pair<Integer, Integer> second = list.get(1);
     * Pair<Integer, Integer> third = list.get(2);
     * 
     * // Verifica che le tre posizioni siano consecutive in diagonale
     * return (isDiag(first, second) && isDiag(second, third));
     * }
     */

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return List.copyOf(this.stars);
    }

}
