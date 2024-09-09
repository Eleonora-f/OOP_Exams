package a01b.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50 * size, 50 * size);
        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var button = (JButton) e.getSource();
            var pos = cells.get(button);
            // button.setText("" + cells.indexOf(button));
            // button.setEnabled(false);

            this.logic.click(pos);
            this.updateView();
            if (this.logic.isOver()) {
                cells.forEach((b, p) -> {
                    b.setEnabled(false);
                });
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
        var elem = this.logic.getElem();

        cells.forEach((b, p) -> {
            b.setText(
                    elem.containsKey(p) ? elem.get(p) == 1 ? "1" : elem.get(p) == 2 ? "2" : elem.get(p) == 3 ? "3" : "*"
                            : " ");
        });
    }

}
