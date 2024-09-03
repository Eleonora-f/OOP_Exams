package a02b.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    // NON FUNZIONA X NIENTE BENE
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var pos = cells.get(jb);

            this.logic.click(pos);
            this.updateView();
            if (this.logic.isOver()) {
                System.exit(0);
            }
            // jb.setText(String.valueOf(cells.indexOf(jb)));
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var pos = new Pair<>(i, j);
                final JButton jb = new JButton(pos.toString());
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.updateView();
        this.setVisible(true);
    }

    private void updateView() {
        var star = this.logic.getStars();
        cells.forEach((b, p) -> {
            b.setText(star.contains(p) ? "*" : " ");
        });
    }

}
