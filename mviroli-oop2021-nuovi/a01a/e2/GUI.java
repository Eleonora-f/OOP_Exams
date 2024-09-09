package a01a.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    // FUNZIONA MA FA SELEZIONARE ANCHE RIGHE O COLONNE(fatto da me)
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
            button.setText("" + cells.get(button));
            // button.setEnabled(false);

            var position = cells.get(button);
            this.logic.click(position);
            this.updateView();
            if (this.logic.isOver()) {
                // setEnabled(b.);
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
        var elem = this.logic.getElem();
        cells.forEach((b, p) -> {
            b.setText(elem.containsKey(p) ? (elem.get(p).equals(1) ? "1" : "*") : " ");
        });
    }

}
