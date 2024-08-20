package a02b.e2;

import java.util.*;

public class LogicImpl implements Logic {

    Map<Pair<Integer, Integer>, String> cells = new HashMap<>();
    Pair<Integer, Integer> star;
    private Random rnd = new Random();
    private Direcion currDir;
    private boolean gameOver;
    private int size;

    public LogicImpl(int size) {
        this.size = size;
        this.star = new Pair<Integer, Integer>(size - 1, rnd.nextInt(size)); // la metto random nell'ultima
                                                                             // riga
        cells.put(star, "*");
        this.currDir = Direcion.UP;// Direz iniziale
        for (int i = 0; i < 20; i++) {
            int x, y;
            Pair<Integer, Integer> pos;
            do {
                x = rnd.nextInt(size);
                y = rnd.nextInt(size);
                pos = new Pair<>(x, y);
            } while (cells.containsKey(pos)); // genera finchè quella cella non viene occupata

            cells.put(pos, rnd.nextBoolean() ? "L" : "R");
        }
    }

    private enum Direcion {
        UP(-1, 0), LEFT(0, -1), RIGHT(0, 1);

        private int x, y;

        Direcion(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair<Integer, Integer> move(Pair<Integer, Integer> pos) {
            return new Pair<Integer, Integer>(pos.getX() + x, pos.getY() + y);
        }
    }

    private void changeDir(Pair<Integer, Integer> currP) {
        if (cells.get(currP).equals("R")) {
            currDir = Direcion.RIGHT;
        } else if (cells.get(currP).equals("L")) {
            currDir = Direcion.LEFT;
        }
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return (pos.getX() >= 0 && pos.getX() < size && pos.getY() >= 0 && pos.getY() < size);
    }

    @Override
    public void click() {

        Pair<Integer, Integer> newPos = currDir.move(star);

        if (!isOk(newPos)) {
            gameOver = true;
            return; // Il gioco è finito, la stella è uscita dalla griglia
        }

        if (cells.containsKey(newPos)) {
            if (cells.get(newPos).equals("R") || cells.get(newPos).equals("L")) {
                changeDir(newPos); // cambia dir se c'è L o R
            }

        }
        // Aggiorna la posizione della stella (*)
        cells.remove(star); // Rimuove la stella dalla posizione precedente
        star = newPos; // Aggiorna la posizione
        cells.put(star, "*"); // Posiziona la stella nella nuova posizione
    }

    @Override
    public boolean isOver() {
        return this.gameOver;
    }

    @Override
    public Map<Pair<Integer, Integer>, String> getElem() {
        return this.cells;
    }

}
