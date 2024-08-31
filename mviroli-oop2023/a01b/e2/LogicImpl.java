package a01b.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {

    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private boolean gameOver = false;
    private int num = 0;
    private int size;
    private boolean moveright = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    private void left() {
        Map<Pair<Integer, Integer>, Integer> newMap = new HashMap<>();

        for (var el : elem.entrySet()) {
            var pos = el.getKey();
            var num = el.getValue();
            var newPos = new Pair<>(pos.getX(), pos.getY() - 1);
            if (isOk(newPos)) {
                newMap.put(newPos, num);
            } else {
                gameOver = true;
            }
        }

        elem = newMap;

    }

    private void right() {
        Map<Pair<Integer, Integer>, Integer> newMap = new HashMap<>();

        for (var el : elem.entrySet()) {
            var pos = el.getKey();
            var num = el.getValue();
            var newPos = new Pair<>(pos.getX(), pos.getY() + 1);
            if (isOk(newPos)) {
                newMap.put(newPos, num);
            } else {
                gameOver = true;
            }
        }
        elem = newMap;

    }

    private boolean isOk(Pair<Integer, Integer> position) {
        return position.getX() >= 0 && position.getX() < size && position.getY() >= 0 && position.getY() < size;
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        if (num < 5) {
            if (!elem.containsKey(pos) && isOk(pos)) {
                elem.put(pos, num);
                num++;
            }
        } else {
            if (moveright) {
                right();
            } else {
                left();
            }

            if (elem.keySet().stream().anyMatch(p -> p.getY() == 0)) {
                moveright = true;
            }

            // Controllo se il gioco è finito dopo lo spostamento a destra
            if (elem.keySet().stream().anyMatch(p -> p.getY() == size)) {
                gameOver = true;
            }
        }

        if (gameOver) {
            System.exit(0); // Chiude l'applicazione se il gioco è finito
        }
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getElem() {
        return new HashMap<>(this.elem);
    }

}
