package a03a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private Pair<Integer, Integer> currHTower = null;
    private Pair<Integer, Integer> currPcTower = null;
    private Random rnd = new Random();
    private boolean gameOver = false;
    private boolean pcWin = false;
    private boolean playerWin = false;

    public LogicImpl(int size) {
        this.size = size;
        this.currHTower = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
        Pair<Integer, Integer> t = null;
        do {
            t = new Pair<>(rnd.nextInt(size), rnd.nextInt(size));
        } while (currHTower == t);
        this.currPcTower = t;

    }

    private boolean isPosOk(Pair<Integer, Integer> pos) {
        int x = currHTower.getX();
        int y = currHTower.getY();
        return (!pos.equals(currHTower) && (pos.getX() == x || pos.getY() == y)); // se sto facendo uno spostamento e lo
                                                                                  // sto facendo in
                                                                                  // verticale/orizzontale

    }

    /*
     * public void reset() { parte opzionale
     * if (pcWin || playerWin) {
     * // Ricomincia il gioco con nuove posizioni casuali per entrambe le torri
     * currHTower = new Pair<>(rnd.nextInt(size), rnd.nextInt(size));
     * do {
     * currPcTower = new Pair<>(rnd.nextInt(size), rnd.nextInt(size));
     * } while (currHTower.equals(currPcTower)); // Evita che le torri inizino sulla
     * stessa posizione
     * 
     * // Resetta gli stati di vittoria
     * pcWin = false;
     * playerWin = false;
     * }
     * }
     */

    @Override
    public boolean click(Pair<Integer, Integer> position) {
        if (isPosOk(position)) { // se è vuota, spostala li
            currHTower = position;
            if (currHTower.equals(currPcTower)) {
                // se sposti la torre e puoi mangiare allora
                // mangia
                playerWin = true;
            }
            return true;
        }
        return false;
    }

    public void movePC() {

        // Ottiene tutte le posizioni possibili in cui può muoversi
        // (orizzontali/verticali)
        List<Pair<Integer, Integer>> possibleMoves = getPos();
        Pair<Integer, Integer> randomPos = null;
        do {
            randomPos = possibleMoves.get(rnd.nextInt(possibleMoves.size()));
        } while (randomPos == currPcTower);
        currPcTower = randomPos;
        if (currHTower.equals(currPcTower)) {
            pcWin = true;
        }
    }

    private List<Pair<Integer, Integer>> getPos() {// creo tutti le possibili cell in cui può andare random
        List<Pair<Integer, Integer>> pos = new ArrayList<>();
        int xPc = currPcTower.getX();
        int yPc = currPcTower.getY();
        // Aggiungi tutte le posizioni sulla stessa colonna
        for (int x = 0; x < size; x++) {
            pos.add(new Pair<>(x, yPc));
        }

        // Aggiungi tutte le posizioni sulla stessa riga
        for (int y = 0; y < size; y++) {
            pos.add(new Pair<>(xPc, y));
        }

        return pos;
    }

    @Override
    public Pair<Integer, Integer> getHumanPos() {
        return this.currHTower;
    }

    @Override
    public Pair<Integer, Integer> getPcPos() {
        return this.currPcTower;
    }

    @Override
    public boolean pcWin() {
        return pcWin;
    }

    @Override
    public boolean playWin() {
        return playerWin;
    }

}
