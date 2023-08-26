package io.github.andichrist;

public class Color extends AbstractTuple implements NTuple {

  public Color(double x, double y, double z) {
    this.x = x; // red
    this.y = y; // green
    this.z = z; // blue
  }

  public double get(String colorTone) {
    return switch (colorTone) {
      case "red" -> x;
      case "green" -> y;
      case "blue" -> z;
      default -> throw new IllegalStateException("Unexpected value: " + colorTone);
    };
  }

}
