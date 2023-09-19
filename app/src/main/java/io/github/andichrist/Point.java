package io.github.andichrist;

public record Point(double x, double y, double z) implements MathOperations<Point> {

  @Override
  public Point add(Point other) {
    return new Point(x + other.x, y + other.y, z + other.z);
  }

  public Point add(Vector other) {
    return new Point(x + other.x(), y + other.y(), z + other.z());
  }

  @Override
  public Point subtract(Point other) {
    return new Point(x - other.x, y - other.y, z - other.z);
  }

  public Point subtract(Vector other) {
    return new Point(x - other.x(), y - other.y(), z - other.z());
  }

  @Override
  public Point multiply(double scalar) {
    return new Point(x * scalar, y * scalar, z * scalar);
  }

  @Override
  public Point multiply(Point other) {
    return new Point(
        x * other.x,
        y * other.y,
        z * other.z
    );
  }

  @Override
  public Point divide(double scalar) {
    return new Point(x / scalar, y / scalar, z / scalar);
  }

  @Override
  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  @Override
  public Point normalize() {
      double magnitude = magnitude();
      return new Point(
          x / magnitude,
          y / magnitude,
          z / magnitude
      );
  }

  @Override
  public double dot(Point other) {
    return x * other.x +
        y * other.y +
        z * other.z;
  }

  @Override
  public Point cross(Point other) {
    return new Point(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    );
  }

  public double get(int key) {
    return switch (key) {
      case 0 -> x;
      case 1 -> y;
      case 2 -> z;
      case 3 -> 1.0;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point point)) return false;

    return Math.abs(point.x - x) < DELTA
        && Math.abs(point.y - y) < DELTA
        && Math.abs(point.z - z) < DELTA;
  }
}
