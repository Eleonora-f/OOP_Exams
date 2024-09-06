package a04.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private Pair<Integer, Integer> rook = null;
    private Pair<Integer, Integer> king = null;
    private Random rnd = new Random();
    private int size;
    private boolean pcWInner = false;
    private boolean playerWinner = false;

    public LogicImpl(int size) {
        this.size = size;
        this.rook = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
        do {
            king = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
        } while (rook.equals(king));
    }

    private boolean isValid(Pair<Integer, Integer> pos) {
        return pos.getX() == rook.getX() || pos.getY() == rook.getY(); // se si trova nella stessa riga o colonna è
                                                                       // valido
    }

    private boolean isOk(Pair<Integer, Integer> pos) {
        int x = pos.getX();
        int y = pos.getY();
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    @Override
    public void movePc() {
        List<Pair<Integer, Integer>> moves = getPos();

        Pair<Integer, Integer> randomPos = null;
        do {
            randomPos = moves.get(rnd.nextInt(moves.size()));
        } while (!isOk(randomPos)); // randomPos.equals(rook)
        king = randomPos;
        if (rook.equals(king)) {
            pcWInner = true;
        }

    }

    private List<Pair<Integer, Integer>> getPos() {
        List<Pair<Integer, Integer>> pos = new ArrayList<>();
        int xPc = king.getX();
        int yPc = king.getY();
        if (isOk(king)) {
            pos.add(new Pair<Integer, Integer>(xPc - 1, yPc));
            pos.add(new Pair<Integer, Integer>(xPc + 1, yPc));
            pos.add(new Pair<Integer, Integer>(xPc, yPc - 1));
            pos.add(new Pair<Integer, Integer>(xPc, yPc + 1));
            pos.add(new Pair<Integer, Integer>(xPc - 1, yPc + 1));
            pos.add(new Pair<Integer, Integer>(xPc - 1, yPc - 1));
            pos.add(new Pair<Integer, Integer>(xPc + 1, yPc - 1));
            pos.add(new Pair<Integer, Integer>(xPc + 1, yPc + 1));
        }

        return pos;
    }

    @Override
    public boolean click(Pair<Integer, Integer> position) {
        if (isValid(position)) {
            rook = position;
            if (rook.equals(king)) { // se clicchi sul re hai vinto
                playerWinner = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public Pair<Integer, Integer> getKingPos() {
        return this.king;
    }

    @Override
    public Pair<Integer, Integer> getRookPos() {
        return this.rook;
    }

    @Override
    public boolean pcWIn() {
        return pcWInner;
    }

    @Override
    public boolean playerWIn() {
        return playerWinner;
    }

}
