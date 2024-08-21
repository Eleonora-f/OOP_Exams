package a02c.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;
    private int counter = 0;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50 * size, 50 * size);
        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            this.logic.hit();
            this.updateView();
            if (this.logic.isOver()) {
                System.exit(0);
            }

        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var pos = new Pair<>(i, j);
                final JButton jb = new JButton(" ");
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.updateView();
        this.setVisible(true);
    }

    // private void updateView() {
    // var elem = this.logic.getElem();
    // var s = this.logic.getStarPos();
    // cells.forEach((b, p) -> {
    // b.setText(elem.contains(p) ? "O" : s.equals(p) ? "*" : " ");
    // });
    // }

    private void updateView() {
        var obstacles = this.logic.getElem();
        var star = this.logic.getStarPos();

        cells.forEach((button, position) -> {
            if (star.equals(position)) {
                button.setText("*");
            } else if (obstacles.contains(position)) {
                button.setText("O");
            } else {
                button.setText(" ");
            }
        });
    }

}
