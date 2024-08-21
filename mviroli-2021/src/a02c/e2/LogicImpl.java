package a02c.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private List<Pair<Integer, Integer>> obstacles = new ArrayList<>();
    Pair<Integer, Integer> star;
    private Random rnd = new Random();
    private boolean gameOver;

    public LogicImpl(int size) {
        this.size = size;
        // inserimento della stella nella prima riga random
        this.star = new Pair<Integer, Integer>(0, rnd.nextInt(size));

        // inserimento delle O
        for (int i = 0; i < 20; i++) {
            int x, y;
            Pair<Integer, Integer> pos;
            while (obstacles.size() <= 20) {
                x = rnd.nextInt(size) + 1; // Impedisce che "o" sia nella prima riga
                y = rnd.nextInt(size);
                pos = new Pair<Integer, Integer>(x, y);
                obstacles.add(pos);
            }

        }
    }

    // controlla che sia dentro la griglia altrimenti finisce il gioco
    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < size && pos.getY() >= 0 && pos.getY() < size;
    }

    @Override
    public List<Pair<Integer, Integer>> getElem() {
        return this.obstacles;
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public void hit() {
        if (!isOk(star)) {
            gameOver = true;
            return;
        }
        // creo una posizione immediatamente sotto alla stella
        Pair<Integer, Integer> down = new Pair<Integer, Integer>(star.getX() + 1, star.getY());
        if (isOk(down) && !obstacles.contains(down)) {
            // Se sotto è libero, scendi
            star = down;
        } else if (isOk(down)) { // if (isOk(down) && cells.containsKey(down))
            // Controlla i movimenti laterali solo se non puoi scendere
            Pair<Integer, Integer> right = new Pair<Integer, Integer>(star.getX() + 1, star.getY() + 1);
            Pair<Integer, Integer> left = new Pair<Integer, Integer>(star.getX() + 1, star.getY() - 1);

            if (!obstacles.contains(left) && !obstacles.contains(right)) { // Se entrambe sono libere, la stella si
                                                                           // sposta casualmente in una delle due
                this.star = rnd.nextBoolean() ? left : right;
            } else if (!obstacles.contains(left)) {// se non ci sono ostacoli a sinistra nella riga sotto vai a sinistra
                                                   // in diagonale
                this.star = left;
            } else if (!obstacles.contains(right)) {// se non ci sono ostacoli a destra nella riga sotto vai a sinistra
                                                    // in diagonale
                this.star = right;
            } else {// se entrambe le pos laterali sono occupate, il gioco termina
                gameOver = true;
                return;
            }
        }
        // Se la stella ha raggiunto l'ultima riga, reinseriscila in una nuova posizione
        // random sulla prima riga
        if (star.getX() == size - 1) {
            star = new Pair<Integer, Integer>(0, rnd.nextInt(size));
        }
    }

    @Override
    public Pair<Integer, Integer> getStarPos() {
        return this.star;
    }

}
