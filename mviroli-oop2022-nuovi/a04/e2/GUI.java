package a04.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    // FUNZIONAA, fatto un po da me e finito da chat
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

                if (logic.click(position)) {
                    logic.movePc();
                }

                if (logic.playerWIn()) {
                    System.out.println("player win");
                    System.exit(0);
                }

                if (logic.pcWIn()) {
                    System.out.println("pc win");
                    System.exit(0);
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
        updateView();
        this.setVisible(true);
    }

    private void updateView() {
        var rook = this.logic.getRookPos();
        var king = this.logic.getKingPos();
        cells.forEach((b, p) -> {
            b.setText(rook.equals(p) ? "R" : king.equals(p) ? "K" : " ");
        });
    }
}
