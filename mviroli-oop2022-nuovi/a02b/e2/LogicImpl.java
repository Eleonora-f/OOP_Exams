package a02b.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private int size;
    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    private Set<Pair<Integer, Integer>> disabled = new HashSet<>();
    private boolean gameOver;

    public LogicImpl(int size) {
        this.size = size;
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        return pos.getX() >= 0 && pos.getX() < size && pos.getY() >= 0 && pos.getY() < size;
    }

    private Set<Pair<Integer, Integer>> getCellsOnDiagonal(int diagValue) {
        Set<Pair<Integer, Integer>> diagonal = new HashSet<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i - j == diagValue) {
                    diagonal.add(new Pair<>(i, j));
                }
            }
        }
        return diagonal;

    }

    private boolean checkThree() {

        Set<Integer> checkedDiagonals = new HashSet<>();

        for (var el : stars) {
            int x = el.getX();
            int y = el.getY();
            int diagValue = x - y;
            if (!checkedDiagonals.contains(diagValue)) {
                Set<Pair<Integer, Integer>> diagonalCells = getCellsOnDiagonal(diagValue);
                int selectedCount = 0;

                for (var cell : diagonalCells) {
                    if (stars.contains(cell)) {
                        selectedCount++;
                    }
                }

                if (selectedCount == 3) {
                    disabled.addAll(diagonalCells);
                    gameOver = true;
                    return true;
                }
                checkedDiagonals.add(diagValue);
            }
        }
        return false;
    }

    public void reset() {
        if (gameOver) {
            stars.clear();
            disabled.clear();
            gameOver = false;
        } else {
            checkThree();
        }

    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        if (isOk(position) && !disabled.contains(position)) {
            if (stars.contains(position)) {
                stars.remove(position);
            } else if (!stars.contains(position)) {
                stars.add(position);
            }
        }
    }

    @Override
    public boolean isOver() {
        return gameOver;
    }

    @Override
    public List<Pair<Integer, Integer>> getStars() {
        return List.copyOf(this.stars);
    }

    @Override
    public Set<Pair<Integer, Integer>> getDisabled() {
        return Set.copyOf(this.disabled);
    }

}
