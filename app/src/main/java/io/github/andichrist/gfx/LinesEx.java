package io.github.andichrist.gfx;

import static java.awt.EventQueue.invokeLater;

import javax.swing.JFrame;

public class LinesEx extends JFrame {

  public LinesEx() {

    initUI();
  }

  private void initUI() {

    add(new SurfaceLines());
    pack(); // use getPreferredSize

    setTitle("Lines");
    //setSize(350, 250); // if not use getPreferredSize
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {

    invokeLater(() -> {
      LinesEx ex = new LinesEx();
      ex.setVisible(true);
    });

  }
}
