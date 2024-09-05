package a02a.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    // FUNZIONA, ma non chiude l'app quando tutte le celle piene ma alla penultima
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);
                // button.setText("" + position);

                logic.click(position);
                updateView();
                if (logic.isOver()) {
                    // System.exit(0);
                    restartGame(size);
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer, Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        updateView();
        this.setVisible(true);
    }

    private void updateView() {
        var elem = this.logic.getElem();
        var disabled = this.logic.getDisabled();
        cells.forEach((b, p) -> {
            b.setText(elem.containsKey(p) ? "B" : " ");
            b.setEnabled(!disabled.contains(p));
        });
    }

    private void restartGame(int size) {
        // Close the current window
        this.dispose();

        // Create a new instance of the GUI with the same size
        new GUI(size);
    }
}
