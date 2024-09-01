package a03a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    private Pair<Integer, Integer> oPos = null;
    Random rnd = new Random();
    private Integer width;
    private int height;
    private boolean gameOver = false;

    public LogicImpl(int width, int height) {
        this.height = height;
        this.width = width;
        this.oPos = new Pair<Integer, Integer>(rnd.nextInt(height), width - 1);
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() <= width - 1 && pos.getY() >= 0 && pos.getY() <= height - 1;
    }

    private void addTrajectory(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        int deltaX = -1;
        int deltaY = 1;

        while (x >= 0 && x < height && y < width) { // (x >= 0 && x < height && y < width)
            stars.add(new Pair<Integer, Integer>(x, y)); // la prima stella inserita è quella cliccata

            if (oPos.equals(new Pair<>(x, y))) {
                gameOver = true;
                return;
            }
            // Aggiorna la posizione per la prossima iterazione
            x += deltaX;
            y += deltaY;

            // Gestisce il rimbalzo sul bordo superiore
            if (x < 0) {
                x = 1;// Riposiziona all'interno del bordo
                deltaX = 1;
            } else if (x >= height) { // Gestisce il rimbalzo sul bordo inferiore, quando esce da sotto alla griglia
                x = height - 2; // riposizionato a height - 2, subito sopra il bordo inferiore
                deltaX = -1; // Cambia la direzione verticale verso l'alto
            }
        }

    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (isOk(pos)) {
            stars.clear();
            addTrajectory(pos);
        }
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getElem() {
        return List.copyOf(this.stars);
    }

    @Override
    public Pair<Integer, Integer> getObjPos() {
        return this.oPos;
    }

}
