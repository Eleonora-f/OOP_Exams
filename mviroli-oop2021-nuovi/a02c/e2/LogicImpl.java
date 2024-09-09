package a02c.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicImpl implements Logic {

    private List<Pair<Integer, Integer>> elem = new ArrayList<>();
    private Pair<Integer, Integer> currPos = null;
    private Random rnd = new Random();
    private int size;
    private boolean gameOver;

    public LogicImpl(int size) {
        this.size = size;
        this.currPos = new Pair<Integer, Integer>(0, rnd.nextInt(size));
        while (elem.size() <= 20) {
            elem.add(new Pair<>(rnd.nextInt(size - 1) + 1, rnd.nextInt(size)));
        }
    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    private void getRandom() {
        var left = new Pair<>(currPos.getX(), currPos.getY() - 1);
        var right = new Pair<>(currPos.getX(), currPos.getY() + 1);
        var chooseR = rnd.nextBoolean() ? left : right;
        if (isOk(chooseR)) {
            if (chooseR == left && elem.contains(left)) {
                currPos = right;
            } else if (chooseR == right && elem.contains(right)) {
                currPos = left;
            } else {
                currPos = chooseR;
            }
        }
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        Pair<Integer, Integer> newP = new Pair<Integer, Integer>(currPos.getX() + 1, currPos.getY());

        if (isOk(newP)) {
            if (!elem.contains(newP)) { // se nn c'è nulla continua a scendere
                currPos = newP;
            } else { // se becchi una o vai in modo ranodm a destra o sinistra
                getRandom();
            }
        } else {
            gameOver = true;
        }
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getElem() {
        return List.copyOf(this.elem);
    }

    @Override
    public Pair<Integer, Integer> getStarPos() {
        return this.currPos;
    }

}
