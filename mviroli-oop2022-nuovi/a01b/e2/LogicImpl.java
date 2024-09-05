package a01b.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    List<Pair<Integer, Integer>> stars = new ArrayList<>();
    List<Pair<Integer, Integer>> neighbours = new ArrayList<>();
    Queue<Pair<Integer, Integer>> lastFour = new LinkedList<>();
    private boolean gameOver = false;
    private int add = 0;
    private int remove = 0;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() > 0 && p.getX() < size - 1 && p.getY() > 0 && p.getY() < size - 1;
    }

    private void doAdj(Pair<Integer, Integer> pos) { // creo una
        neighbours.clear(); // Pulisce la lista dei vicini per ogni nuovo click
        var p1 = new Pair<>(pos.getX() - 1, pos.getY() - 1);
        var p2 = new Pair<>(pos.getX() + 1, pos.getY() + 1);
        var p3 = new Pair<>(pos.getX() - 1, pos.getY() + 1);
        var p4 = new Pair<>(pos.getX() + 1, pos.getY() - 1);

        neighbours.add(p1);
        neighbours.add(p2);
        neighbours.add(p3);
        neighbours.add(p4);
    }

    private void putStars(Pair<Integer, Integer> pos) {
        // List<Pair<Integer, Integer>> list = new ArrayList<>(lastFour);

        for (var el : neighbours) { // Scorri tutti i vicini della cella cliccata
            if (!stars.contains(el)) {// Se vuota metti
                add++;
                stars.add(el);
                System.out.println("print" + el);
            } else {
                remove++;
                stars.remove(el);
            }
        }

        // se presente togli
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        System.out.println("pos" + position);
        if (isOk(position)) {
            doAdj(position);
            putStars(position);
            if (add == 1 && remove == 3) {
                gameOver = true;
            }
            add = 0;
            remove = 0;
        }
    }

    @Override
    public boolean isOver() {
        return this.gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return List.copyOf(this.stars);
    }

}
