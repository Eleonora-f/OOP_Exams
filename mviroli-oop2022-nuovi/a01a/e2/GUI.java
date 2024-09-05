package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

//FUNZIONA
public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        /*
         * ActionListener al = new ActionListener() {
         * var button = (JButton) e.getSource();
         * var position = cells.get(button);
         * // button.setText("" + position);
         * this.logic.click();
         * 
         * if (this.logic.isOver()) {
         * System.exit(0);
         * }
         * //public void actionPerformed(ActionEvent e) {
         * 
         * //}
         * };
         */

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            // jb.setText(String.valueOf(cells.indexOf(jb)));
            var pos = cells.get(jb);
            this.logic.click(pos);
            this.updateView();
            if (this.logic.isOver()) {
                System.exit(0);
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
        var star = this.logic.getStars();
        cells.forEach((b, p) -> {
            b.setText(star.contains(p) ? "*" : " ");
        });
    }
}
