package a04.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int width) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * width);
        this.logic = new LogicImpl(width);

        JPanel panel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var pos = cells.get(jb);

            this.logic.click(pos);
            this.updateView();
            if (this.logic.isOver()) {
                System.exit(0);
            }
        };

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
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
        // var first = this.logic.getFirstPos();
        cells.forEach((b, p) -> {
            b.setText(star.contains(p) ? "*" : " ");
        });
    }

}
