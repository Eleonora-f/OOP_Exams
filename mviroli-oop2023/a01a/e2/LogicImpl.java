package a01a.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {
    private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
    private int size;
    private int number = 1;
    private boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public void click(Pair<Integer, Integer> pos) {
        // System.out.println("pos: e num:" + pos);
        if (!elem.containsKey(pos) && isOk(pos) && !isAdj(pos)) {

            elem.put(pos, number);
            number++;
            // System.out.println("pos: e num:" + pos + number);
        } else if (isAdj(pos)) {
            upRight();
        }
    }

    private boolean isAdj(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        boolean isAdj = false;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // System.out.printf("%d %d \n", i, j);
                if (i != x && j != y) {
                    if (elem.containsKey(new Pair<>(i, j))) {
                        isAdj = true;
                    }
                }
            }
        }
        return isAdj;
    }

    private void upRight() {
        Map<Pair<Integer, Integer>, Integer> updated = new HashMap<>();

        for (var el : elem.entrySet()) {
            var pos = el.getKey();
            var num = el.getValue();
            var newPos = new Pair<>(pos.getX() - 1, pos.getY() + 1);

            if (isOk(newPos)) {
                updated.put(newPos, num);
            } else {
                gameOver = true;
            }

        }
        elem = updated;
    }

    private boolean isOk(Pair<Integer, Integer> position) {
        return position.getX() >= 0 && position.getX() < size && position.getY() >= 0 && position.getY() < size;

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
