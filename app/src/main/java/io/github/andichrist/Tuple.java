package io.github.andichrist;

public record Tuple(double x, double y, double z, double w) implements MathOperations<Tuple> {

  public static final Tuple ZERO = new Tuple(0, 0, 0, 0);

  public boolean isZero() {
      return this.equals(Tuple.ZERO);
  }

  @Override
  public Tuple add(Tuple tuple) {
    return new Tuple(
        x + tuple.x,
        y + tuple.y,
        z + tuple.z,
        w + tuple.w
    );
  }

  @Override
  public Tuple subtract(Tuple tuple) {
    return new Tuple(
        x - tuple.x,
        y - tuple.y,
        z - tuple.z,
        w - tuple.w
    );
  }

  @Override
  public Tuple multiply(double factor) {
    return new Tuple(
        x * factor,
        y * factor,
        z * factor,
        w * factor
    );
  }

  @Override
  public Tuple multiply(Tuple tuple) {
    return new Tuple(
        x * tuple.x,
        y * tuple.y,
        z * tuple.z,
        w * tuple.w
    );
  }

  @Override
  public Tuple divide(double value) {
    return new Tuple(
        x / value,
        y / value,
        z / value,
        w / value
    );
  }

  @Override
  public Tuple normalize() {
    double magnitude = magnitude();
    return new Tuple(
        x / magnitude,
        y / magnitude,
        z / magnitude,
        w / magnitude
    );
  }

  @Override
  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z + w + w);
  }

  @Override
  public double dot(Tuple tuple) {
    return x * tuple.x +
        y * tuple.y +
        z * tuple.z +
        w * tuple.w;
  }

  @Override
  public Tuple cross(Tuple tuple) {
    return new Tuple(
        y * tuple.z - z * tuple.y,
        z * tuple.x - x * tuple.z,
        x * tuple.y - y * tuple.x,
        1.0
    );
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Tuple tuple)) return false;

    return Math.abs(tuple.x - x) < DELTA
        && Math.abs(tuple.y - y) < DELTA
        && Math.abs(tuple.z - z) < DELTA
        && Math.abs(tuple.w - w) < DELTA;
  }
}
