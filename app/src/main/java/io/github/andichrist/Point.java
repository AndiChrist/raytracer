package io.github.andichrist;

public class Point extends AbstractTuple implements NTuple {

  public static double POINT = 1.0;

  public Point(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = POINT;
  }
}
