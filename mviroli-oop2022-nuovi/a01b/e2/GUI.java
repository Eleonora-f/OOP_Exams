package a01b.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    // FUNZIONA: fatto da me in 45 min
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
                    System.exit(0);
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
        this.updateView();
        this.setVisible(true);
    }

    private void updateView() {
        var stars = this.logic.getStars();
        cells.forEach((b, p) -> {
            b.setText(stars.contains(p) ? "*" : " ");
        });
    }
}
