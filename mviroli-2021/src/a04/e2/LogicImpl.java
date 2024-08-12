package a04.e2;

import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class LogicImpl implements Logic {

    private int size;
    private Pair<Integer, Integer> pcK;
    private Pair<Integer, Integer> playerT;
    private Random rnd = new Random();
    private boolean playerWinner = false;
    private boolean pcWinner = false;

    public LogicImpl(int size) {
        this.size = size;
        this.reset();
        /*
         * pcK = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
         * Pair<Integer, Integer> t = null;
         * do {
         * t = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
         * 
         * } while (t == pcK);
         * playerT = t;
         */
    }

    private boolean isValid(Pair<Integer, Integer> p, Pair<Integer, Integer> curr) { // OK
        return (!p.equals(curr)) && (p.getX() == curr.getX() || p.getY() == curr.getY());
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) { // OK
        if (isValid(position, playerT)) {
            playerT = position;
            /*
             * if (position.equals(pcK)) {
             * playerWinner = true;
             * }
             */
            return true;
        }
        return false;
    }

    @Override
    public Pair<Integer, Integer> getPcK() {
        return this.pcK;
    }

    @Override
    public Pair<Integer, Integer> getPlayerT() {
        return this.playerT;
    }

    private List<Pair<Integer, Integer>> pcMoves() { // OK
        List<Pair<Integer, Integer>> listMoves = new ArrayList<>();
        // Metto tutte le possibili mosse
        listMoves.add(new Pair<Integer, Integer>(this.pcK.getX() + 1, this.pcK.getY() + 1));
        listMoves.add(new Pair<Integer, Integer>(this.pcK.getX() - 1, this.pcK.getY() - 1));
        listMoves.add(new Pair<Integer, Integer>(this.pcK.getX() - 1, this.pcK.getY()));
        listMoves.add(new Pair<Integer, Integer>(this.pcK.getX() + 1, this.pcK.getY()));
        listMoves.add(new Pair<Integer, Integer>(this.pcK.getX(), this.pcK.getY() - 1));
        listMoves.add(new Pair<Integer, Integer>(this.pcK.getX(), this.pcK.getY() + 1));
        return listMoves;
    }

    @Override
    public boolean isOver() { // OK
        return this.pcK.equals(this.playerT);
    }

    @Override
    public void reset() {
        this.pcK = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
        Pair<Integer, Integer> tmp = null;
        do {
            tmp = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
        } while (tmp == this.pcK);
        this.playerT = tmp;
    }

    @Override
    public void computerMove() {
        if (pcMoves().contains(this.playerT)) { // verifica se una delle possibili mosse del re porta alla stessa
                                                // posizione occupata dalla torre
            this.pcK = this.playerT; // allora il re si sposta sulla torre
        } else {
            Pair<Integer, Integer> tmp = null;
            do {
                tmp = pcMoves().get(rnd.nextInt(pcMoves().size())); // genera mosse casuali dalla lista delle possibili
                                                                    // mosse del king
            } while (!(tmp.getX() >= 0 && tmp.getY() < size && tmp.getX() < size && tmp.getY() >= 0));// genera mosse
                                                                                                      // causali finchè
                                                                                                      // la mossa
                                                                                                      // selezionata non
                                                                                                      // rientra nei
                                                                                                      // confini della
                                                                                                      // griglia

            this.pcK = tmp; // poi viene aggiornata la pos del king a quella della mossa selezionata(random)
        }
    }

}
