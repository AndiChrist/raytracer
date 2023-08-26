package io.github.andichrist;

public abstract class AbstractTuple implements NTuple {

  protected double x;
  protected double y;
  protected double z;
  protected double w;

  public double getX() {
    return x;
  }
  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }
  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return z;
  }
  public void setZ(double z) {
    this.z = z;
  }

  public double getW() {
    return w;
  }
  public void setW(double w) {
    this.w = w;
  }

  public double get(String key) {
    return switch(key) {
      case "x" -> x;
      case "y" -> y;
      case "z" -> z;
      case "w" -> w;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  public double get(int key) {
    return switch(key) {
      case 0 -> x;
      case 1 -> y;
      case 2 -> z;
      case 3 -> w;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  public boolean isPoint() {
    return getW() == Point.POINT;
  }
  public boolean isVector() {
    return getW() == Vector.VECTOR;
  }

  @Override
  public boolean equals(Object o) {
    double epsilon = 0.00001;

    if (this == o) return true;
    if (!(o instanceof AbstractTuple)) return false;

    NTuple tuple = (NTuple) o;

    return Math.abs(tuple.getZ() - getZ()) < epsilon &&
    Math.abs(tuple.getY() - getY()) < epsilon &&
    Math.abs(tuple.getZ() - getZ()) < epsilon &&
    Math.abs(tuple.getW() - getW()) < epsilon;
  }

  @Override
  public String toString() {
    return "AbstractTuple{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        ", w=" + w +
        '}';
  }
}
