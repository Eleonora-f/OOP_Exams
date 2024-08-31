package a02b.e2;

import java.util.*;

public class LogicImpl implements Logic {
    // FUNZIONA MA SENZA CONTROLLO CHE POSSA USCIRE DALLA TABELLA
    private List<Pair<Integer, Integer>> stars = new ArrayList<>();

    private Pair<Integer, Integer> currClick;
    private boolean gameOver = false;
    private int size;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean clickInBox(Pair<Integer, Integer> pos, Pair<Integer, Integer> click) {
        int x = pos.getX();
        int y = pos.getY();
        return click.getX() > x - 2 && click.getX() < x + 2 && click.getY() > y - 2 && click.getY() < y + 2;
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        System.out.println("POS = " + pos);
        if (stars.isEmpty()) {
            currClick = pos;
            addSquare(pos);
        } else if (!stars.contains(pos) && clickInBox(currClick, pos)) { // se clicco una delle 4 celle vuote dentro il
                                                                         // quadrato allora spostale
            // currClick = pos;
            move(pos);
            currClick = pos;

        } else if (stars.contains(pos)) {
            gameOver = true;
        }
    }

    private void addSquare(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();

        for (int i = x - 2; i <= x + 2; i++) {
            if (i % 2 == 0) { // se pari
                for (int j = y - 2; j <= y + 2; j++) {
                    stars.add(new Pair<Integer, Integer>(i, j));
                }
            } else {// Se dispari
                for (int j = y - 2; j <= y + 2; j += 2) {
                    stars.add(new Pair<Integer, Integer>(i, j));
                }
            }

        }
    }

    private void move(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        List<Pair<Integer, Integer>> newStars = new ArrayList<>();

        if (currClick.getX() + 1 == x && currClick.getY() + 1 == y) { // angolo alto sinistra
            for (var el : stars) {
                newStars.add(new Pair<Integer, Integer>(el.getX() + 1, el.getY() + 1));
            }

        } else if (currClick.getX() - 1 == x && currClick.getY() - 1 == y) {
            for (var el : stars) {
                newStars.add(new Pair<Integer, Integer>(el.getX() - 1, el.getY() - 1));// angolo basso destra
            }

        }
        stars = newStars;
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return List.copyOf(this.stars);
    }

}
