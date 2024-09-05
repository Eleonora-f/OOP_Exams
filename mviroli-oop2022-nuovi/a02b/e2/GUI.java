package a02b.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    // NON CAPISCO SE FUNZIONA. RIGUARDACI
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logic = new LogicImpl(size);

        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size, size));
        JButton checkButton = new JButton("Check > Restart");
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        main.add(BorderLayout.SOUTH, checkButton);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);
                // button.setText("" + position);

                logic.click(position);
                updateView();
                /*
                 * if (logic.isOver()) {
                 * System.exit(0);
                 * }
                 */
            }
        };

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logic.reset();
                updateView();
            }
        });
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

    protected void updateView() {
        var star = this.logic.getStars();
        var disab = this.logic.getStars();

        cells.forEach((b, p) -> {
            b.setText(star.contains(p) ? "*" : " ");
            b.setEnabled(!disab.contains(p));
        });
    }
}
