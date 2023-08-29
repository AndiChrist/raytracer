package io.github.andichrist;

public record Tuple(double x, double y, double z, double w) implements NTuple {

  public static final Tuple ZERO = new Tuple(0, 0, 0, 0);

  public boolean isZero() {
      return this.equals(Tuple.ZERO);
  }

  public Point toPoint() {
    if (w != Point.POINT) throw new RuntimeException("Tuple is not a Point.");
    return new Point(this);
  }

  public Vector toVector() {
    if (w != Vector.VECTOR) throw new RuntimeException("Tuple is not a Vector.");
    return new Vector(this);
  }

  @Override
  public double get(int key) {
    return switch(key) {
      case 0 -> x;
      case 1 -> y;
      case 2 -> z;
      case 3 -> w;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  @Override
  public double get(String key) {
    return switch(key) {
      case "x" -> x;
      case "y" -> y;
      case "z" -> z;
      case "w" -> w;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  //@Override
  public Tuple add(Tuple tuple) {
    return new Tuple(
        x + tuple.x(),
        y + tuple.y(),
        z + tuple.z(),
        w + tuple.w()
    );
  }

  public Tuple multiply(double factor) {
    return new Tuple(
        x * factor,
        y * factor,
        z * factor,
        w * factor
    );
  }

  public Tuple divide(double value) {
    return new Tuple(
        x / value,
        y / value,
        z / value,
        w / value
    );
  }
}
