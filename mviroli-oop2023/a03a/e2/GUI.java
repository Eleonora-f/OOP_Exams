package a03a.e2;

import javax.swing.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

//FUNZIONA
public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int width, int height) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * height);
        this.logic = new LogicImpl(width, height);

        JPanel panel = new JPanel(new GridLayout(height, width));
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

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
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

    private void updateView() {
        var elem = this.logic.getElem();
        var obj = this.logic.getObjPos();
        cells.forEach((b, p) -> {
            b.setText(elem.contains(p) ? "*" : obj.equals(p) ? "o" : " ");
        });
    }

}
