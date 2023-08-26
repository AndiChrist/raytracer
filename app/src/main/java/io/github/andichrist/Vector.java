package io.github.andichrist;

public class Vector extends AbstractTuple implements NTuple {

  public static double VECTOR = 0.0;

  public Vector(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = VECTOR;
  }

}
