package a03c.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {
    // DA FINIRE PERCHE' NON VA
    private int size;
    private Set<Pair<Integer, Integer>> obj = new HashSet<>();
    private Set<Pair<Integer, Integer>> stars = new HashSet<>();
    private boolean gameOver = false;
    private Direction currDirection = Direction.UP; // Direzione iniziale
    private Random rnd = new Random();

    public LogicImpl(int size) {
        this.size = size;

        // Inizializzazione delle stelle nell'ultima riga
        for (int i = 0; i < size; i++) {
            stars.add(new Pair<>(size - 1, i));
            obj.add(new Pair<Integer, Integer>(rnd.nextInt(size - 2), i));

        }

        // Inizializzazione degli ostacoli (tranne nelle ultime due righe)
        // for (int i = 0; i < size; i++) {
        // int obstacleRow;
        // do {
        // obstacleRow = rnd.nextInt(size - 2); // Escludi le ultime due righe
        // } while (obstacles.contains(new Pair<>(i, obstacleRow))); // Evita duplicati
        // obstacles.add(new Pair<>(i, obstacleRow));
        // }

        // aggiungo le O random, tranne che nelle ultime 2 righe
    }

    private enum Direction {
        UP(-1, 0), DOWN(1, 0);

        int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Direction next() {
            return this == UP ? DOWN : UP;
        }
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < size && pos.getY() >= 0 && pos.getY() < size;
    }

    @Override
    public void click() {

        Set<Pair<Integer, Integer>> newStar = new HashSet<>();
        for (Pair<Integer, Integer> star : stars) {

            var newP = new Pair<>(star.getX() + currDirection.x, star.getY());

            if (isOk(newP)) {
                // Verifica se la nuova posizione contiene un ostacolo
                if (obj.contains(newP)) {
                    obj.remove(newP);
                    currDirection = currDirection.next(); // Inverti direzione
                    newP = new Pair<>(star.getX() + currDirection.x, star.getY());

                    if (isOk(newP)) {
                        newStar.add(newP);
                    } else {
                        gameOver = true;
                        break;
                    }
                } else {
                    newStar.add(newP); // La stella si muove nella nuova posizione
                }
            } else {
                gameOver = true;
                break;
            }
        }

        if (!gameOver) {
            stars.clear();
            stars.addAll(newStar);
        }

    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public Set<Pair<Integer, Integer>> getElem() {
        return new HashSet<>(obj); // Restituisci una copia degli ostacoli
    }

    @Override
    public Set<Pair<Integer, Integer>> getStarPos() {
        return new HashSet<>(stars); // Restituisci una copia delle posizioni delle stelle
    }
}
