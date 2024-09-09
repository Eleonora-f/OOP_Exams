package a02a.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicImpl implements Logic {

    private int size;
    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private Pair<Integer, Integer> currPos = null;
    private Random rnd = new Random();
    private int num = 0;
    private boolean gameOver;

    public LogicImpl(int size) {
        this.size = size;

    }

    private boolean isOk(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    private void getPath() {
        var nextUp = new Pair<>(currPos.getX() - 1, currPos.getY());
        var nextDown = new Pair<>(currPos.getX() + 1, currPos.getY());
        var nextLeft = new Pair<>(currPos.getX(), currPos.getY() - 1);
        var nextRight = new Pair<>(currPos.getX(), currPos.getY() + 1);
        if (isOk(nextUp) && !elem.containsKey(nextUp)) { // se la prossima cella è in alto allora inseriscila
            currPos = nextUp;
            num++;
            elem.put(nextUp, num);
        } else if (isOk(nextDown) && !elem.containsKey(nextDown)) { // altrimenti cambia dire
            currPos = nextDown;
            num++;
            elem.put(nextDown, num);
        } else if (isOk(nextRight) && !elem.containsKey(nextRight)) { // altrimenti cambia dire
            currPos = nextRight;
            num++;
            elem.put(nextRight, num);
        } else if (isOk(nextLeft) && !elem.containsKey(nextLeft)) { // altrimenti cambia dire
            currPos = nextLeft;
            num++;
            elem.put(nextLeft, num);
        } else {
            gameOver = true;
        }

    }

    @Override
    public void click() {
        if (elem.isEmpty()) { // prima cella = 0
            currPos = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
            elem.put(currPos, 0);
        } else { // se non è il primo click mala cella è libera
            getPath();
            // num++;
            // elem.put(pos, num);
        }

    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return new HashMap<>(this.elem);
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

}
