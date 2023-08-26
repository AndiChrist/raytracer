package io.github.andichrist;

public class Tuple extends AbstractTuple implements NTuple {

  public static final Tuple ZERO = new Tuple(0, 0, 0, 0);

  public Tuple(double x, double y, double z, double w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  public boolean isZero() {
      return this.equals(Tuple.ZERO);
  }

}
