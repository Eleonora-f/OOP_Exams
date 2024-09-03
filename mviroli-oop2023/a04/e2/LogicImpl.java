package a04.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    private Pair<Integer, Integer> currStar = null;
    private Random rnd = new Random();
    private boolean rightFlag = true;
    private boolean leftFlag = false;
    private int width;
    private boolean gameOver = false;

    public LogicImpl(int width) {
        this.width = width;
        this.currStar = new Pair<>(0, rnd.nextInt(width));
        stars.add(currStar);
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < width && pos.getY() >= 0 && pos.getY() < width;
    }

    private boolean isObl(Pair<Integer, Integer> p) {
        int x = currStar.getX();
        int y = currStar.getY();

        // Verifica se la nuova posizione è una mossa diagonale valida rispetto
        // all'ultima
        if ((p.getX() == x + 1) && (p.getY() == y + 1 || p.getY() == y - 1)) {
            currStar = p; // Aggiorna la posizione corrente a quella nuova valida
            return true;
        }

        // Se la mossa non è valida, il gioco termina
        // gameOver = true;
        return false;

    }

    @Override
    public void click(Pair<Integer, Integer> position) {

        if (position.getX() == 0) { // ignora click sulla 1^ riga
            return;
        }
        if (isOk(position) && isObl(position)) {
            stars.add(new Pair<>(position.getX(), position.getY()));
            if (position.getX() == width - 1) {// se siamo arrivati all'ultima riga termina
                gameOver = true;
            }
        }
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return List.copyOf(this.stars);
    }
    /*
     * @Override
     * public Pair<Integer, Integer> getFirstPos() {
     * return this.firstStar;
     * }
     */

}
