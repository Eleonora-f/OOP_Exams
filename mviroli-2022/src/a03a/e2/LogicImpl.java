package a03a.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicImpl implements Logic {

    private int size;
    private Pair<Integer, Integer> pcT;
    private Pair<Integer, Integer> playerT;
    private Random rnd = new Random();

    private boolean playerWinner = false;
    private boolean pcWinner = false;

    public LogicImpl(int size) {
        this.size = size;
        pcT = new Pair<Integer, Integer>(rnd.nextInt(size), rnd.nextInt(size));
        Pair<Integer, Integer> t = null;
        do {
            t = new Pair<>(rnd.nextInt(size), rnd.nextInt(size));
        } while (t == pcT); // continua a creare una nuova torre random finchè non saranno in 2 pos diverse
    }

    private boolean isValid(Pair<Integer, Integer> currPos, Pair<Integer, Integer> destPos) { // la mossa è valida se la
                                                                                              // cella di destinazione
                                                                                              // si trova nella stessa
                                                                                              // riga o stessa colonna
                                                                                              // di quella di parteza
        return (currPos.getX() == destPos.getX() || currPos.getY() == destPos.getY());
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        if (isValid(position, playerT)) {
            playerT = position; // aggiorno la posizione
            if (position.equals(pcT)) { // se dove ho spostato la mia torre è anche dove si trova la T del PC ho vinto
                playerWinner = true;
            }
            return playerWinner;
        }
        return false;
    }

    @Override
    public Pair<Integer, Integer> getPlayerT() {
        return this.playerT;
    }

    @Override
    public Pair<Integer, Integer> getPCT() {
        return this.pcT;
    }

    @Override
    public void getPcMove() {
        Pair<Integer, Integer> randomPos = null;
        do {
            randomPos = getPos().get(rnd.nextInt(getPos().size())); // genera un indice casuale all'interno
                                                                    // dell'intervallo delle dimensioni della lista di
                                                                    // posizioni possibili(getPos)
        } while (randomPos == pcT); // evita che la torre del PC resti sempre nella stessa pos
        this.pcT = randomPos;
        if (this.pcT.equals(playerT)) { // se una volta che il pc si è mosso, si trova sulla T del giocaotore, allo ha
                                        // vinto
            pcWinner = true;
        }
    }

    private List<Pair<Integer, Integer>> getPos() {
        List<Pair<Integer, Integer>> pos = new ArrayList<>();
        /// Aggiunge tutte le posizioni nella stessa colonna sopra la torre del computer
        for (int x = 0; x < this.pcT.getX(); x++) {
            pos.add(new Pair<>(x, this.pcT.getY()));
        }
        // // Aggiunge tutte le posizioni nella stessa colonna sotto la torre del
        // computer (inclusa la sua attuale posizione)
        for (int x = this.pcT.getX(); x < size; x++) {
            pos.add(new Pair<>(x, this.pcT.getY()));
        }
        //// Aggiunge tutte le posizioni nella stessa riga a sinistra della torre del
        //// computer
        for (int y = 0; y < this.pcT.getY(); y++) {
            pos.add(new Pair<>(this.pcT.getX(), y));
        }
        //// Aggiunge tutte le posizioni nella stessa riga a destra della torre del
        //// computer (inclusa la sua attuale posizione)
        for (int y = this.pcT.getY(); y < size; y++) {
            pos.add(new Pair<>(this.pcT.getX(), y));
        }
        return pos;
    }

    @Override
    public boolean pcWin() {
        return pcWinner;
    }

    @Override
    public boolean playerWin() {
        return pcWinner;
    }
}
