package io.github.andichrist;

import java.awt.*;
import java.util.Arrays;

public class Canvas {

  private final Color[][] canvas;

  public Canvas(int width, int height) {
    this(width, height, Color.BLACK);
  }

  public Canvas(int width, int height, Color color) {
    canvas = new Color[width][height];

    // set canvas' default background
    Arrays.stream(canvas).forEach(row -> Arrays.fill(row, color));
  }

  public int getWidth() {
    return canvas.length;
  }

  public int getHeight() {
    return canvas[0].length;
  }

  public Color getColor(int x, int y) {
    return canvas[x][y];
  }

  public void setColor(int x, int y, Color color) {
    canvas[x][y] = color;
  }

}
