package a02a.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private List<Pair<Integer, Integer>> stars = new ArrayList<>();
    private List<Pair<Integer, Integer>> objects = new ArrayList<>();
    Queue<Pair<Integer, Integer>> lastsFour = new LinkedList<>();
    private int size;
    private boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;

        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                for (int j = 1; j < size; j += 2) {
                    stars.add(new Pair<Integer, Integer>(i, j));
                }
            } else {
                for (int j = 0; j < size; j++) {
                    stars.add(new Pair<Integer, Integer>(i, j));
                }
            }
        }
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        if (!stars.contains(position) && !objects.contains(position)) { // se la celle cliccata è vuota, inserisci la o
            objects.add(position);
            lastsFour.add(position);
            if (lastsFour.size() > 4) {
                lastsFour.poll();// rimuovi il più vecchio se ci sono già 4 elementi
            }
            if (lastFour()) {
                gameOver = true;
            }
        }
    }

    private boolean lastFour() {
        if (lastsFour.size() < 4) {
            return false;
        }
        // creata lista temporanea list che contiene i quattro elementi più recenti
        List<Pair<Integer, Integer>> list = new ArrayList<>(lastsFour);

        Pair<Integer, Integer> p1 = list.get(0);
        Pair<Integer, Integer> p2 = list.get(1);
        Pair<Integer, Integer> p3 = list.get(2);
        Pair<Integer, Integer> p4 = list.get(3);

        return isSquare(p1, p2, p3, p4);
    }

    private boolean isSquare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2, Pair<Integer, Integer> p3,
            Pair<Integer, Integer> p4) {
        Set<Integer> xCoords = new HashSet<>(); // inizializzazione
        Set<Integer> yCoords = new HashSet<>();
        // Le coordinate X e Y di ciascuno dei quattro punti vengono aggiunte ai
        // rispettivi insiemi
        xCoords.add(p1.getX());
        xCoords.add(p2.getX());
        xCoords.add(p3.getX());
        xCoords.add(p4.getX());

        yCoords.add(p1.getY());
        yCoords.add(p2.getY());
        yCoords.add(p3.getY());
        yCoords.add(p4.getY());

        // Se i quattro punti formano un quadrato, dovrebbero esserci esattamente due
        // valori distinti di X e due valori distinti di Y
        return xCoords.size() == 2 && yCoords.size() == 2;
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
    public List<Pair<Integer, Integer>> getObjects() {
        return List.copyOf(this.objects);
    }

}
