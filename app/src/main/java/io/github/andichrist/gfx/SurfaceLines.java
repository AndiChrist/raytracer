package io.github.andichrist.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class SurfaceLines extends JPanel {

    public SurfaceLines() {
        setBackground(Color.BLACK);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GREEN);
        g2d.drawLine(30, 30, 200, 30);
        g2d.drawLine(200, 30, 30, 200);
        g2d.drawLine(30, 200, 200, 200);
        g2d.drawLine(200, 200, 30, 30);

        g2d.setColor(Color.RED);
        g2d.drawLine(200, 150, 200, 150); // red dot
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 250);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }


}
