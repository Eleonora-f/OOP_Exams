package a03b.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int width;
    private int height;
    private boolean gameOver = false;
    private Random rnd = new Random();
    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    private Pair<Integer, Integer> oPos = null;

    public LogicImpl(int width, int height) {
        this.width = width;
        this.height = height;
        this.oPos = new Pair<Integer, Integer>(rnd.nextInt(height), rnd.nextInt(width));
    }

    private int drawDiag(int startX, int startY, int dx, int dy) {
        int x = startX;
        int y = startY;
        int steps = 0;
        while (x >= 0 && x < height && y >= 0 && y < width) {
            Pair<Integer, Integer> currStar = new Pair<>(x, y);
            stars.add(currStar);

            if (currStar.equals(oPos)) { // se le stelle colpiscono la o si chiude l'applicazioneoPos
                gameOver = true;
                return steps;
            }

            x += dx;
            y += dy;
            steps++;
        }
        // Riempi l'interno del triangolo
        /*
         * int endX = startX + (x - startX) / Math.abs(dx);
         * int endY = startY + (y - startY) / Math.abs(dy);
         * for (int i = startX; i != endX; i += dx) {
         * for (int j = startY; j != endY; j += dy) {
         * if (Math.abs(i - startX) <= Math.abs(j - startY)) {
         * Pair<Integer, Integer> currStar = new Pair<>(i, j);
         * stars.add(currStar);
         * if (currStar.equals(oPos)) {
         * gameOver = true;
         * return;
         * }
         * }
         * }
         * }
         */
        return steps;
    }

    private void fillTriangle(int startX, int startY, int length) {
        for (int x = 0; x < length; x++) {
            for (int y = -x; y <= x; y++) {
                int newX = startX + x;
                int newY = startY + y;
                if (newX < height && newY >= 0 && newY < width) {
                    Pair<Integer, Integer> currStar = new Pair<>(newX, newY);
                    stars.add(currStar);

                    if (currStar.equals(oPos)) {
                        gameOver = true;
                        return;
                    }
                }
            }
        }
    }

    private void doTriangle(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        // Disegna la diagonale verso destra
        int length = 0;
        while (x + length < height && y + length < width) {
            Pair<Integer, Integer> currStar = new Pair<>(x + length, y + length);
            if (currStar.equals(oPos)) {
                gameOver = true;
                return;
            }
            length++;
        }

        // Riempi l'interno del triangolo
        fillTriangle(x, y, length);
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (isOk(pos) && !stars.contains(pos)) { // se è dentro la griglia e non c'è già una stella
            doTriangle(pos); // crea il triangolo
        }
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return List.copyOf(stars);
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < width && pos.getY() >= 0 && pos.getY() < height;
    }

    @Override
    public Pair<Integer, Integer> getObjPos() {
        return this.oPos;
    }

}
