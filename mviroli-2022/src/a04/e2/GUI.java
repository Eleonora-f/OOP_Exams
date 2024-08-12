package a04.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

//NON MI E' CHIARISSIMO SE VADA
public class GUI extends JFrame {

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
                if (logic.hit(position)) {
                    if (logic.isOver()) {
                        System.out.println("Vittoria");
                        logic.reset();
                    } else {
                        logic.computerMove();
                        if (logic.isOver()) {
                            System.exit(0);
                        }
                    }
                }
                updateView();
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
        var king = this.logic.getPcK();
        var rook = this.logic.getPlayerT();

        cells.forEach((b, p) -> {
            b.setText(king.equals(p) ? "K" : rook.equals(p) ? "R" : " ");
        });
    }
}
