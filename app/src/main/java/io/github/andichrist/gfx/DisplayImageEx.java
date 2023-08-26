package io.github.andichrist.gfx;

import java.awt.EventQueue;
import javax.swing.JFrame;

/*
http://zetcode.com/gfx/java2d/shapesandfills/
 */
public class DisplayImageEx extends JFrame {

  public DisplayImageEx() {
    initUI();
  }

  private void initUI() {
    add(new Surface3());
    //add(new Surface());

    pack();

    setTitle("Raytrace");
    setSize(350, 250);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      DisplayImageEx ex = new DisplayImageEx();
      ex.setVisible(true);
    });
  }
}
