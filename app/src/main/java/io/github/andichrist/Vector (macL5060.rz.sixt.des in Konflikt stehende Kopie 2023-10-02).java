package io.github.andichrist;

public record Vector(double x, double y, double z) implements MathOperations<Vector> {
  @Override
  public Vector add(Vector other) {
    return new Vector(x + other.x, y + other.y, z + other.z);
  }

  @Override
  public Vector subtract(Vector other) {
    return new Vector(x - other.x, y - other.y, z - other.z);
  }

  @Override
  public Vector multiply(double scalar) {
    return new Vector(x * scalar, y * scalar, z * scalar);
  }

  @Override
  public Vector multiply(Vector other) {
    return new Vector(
        x * other.x,
        y * other.y,
        z * other.z
    );
  }

  @Override
  public Vector divide(double scalar) {
    return new Vector(x / scalar, y / scalar, z / scalar);
  }

  @Override
  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  @Override
  public Vector normalize() {
    double magnitude = magnitude();
    return new Vector(
        x / magnitude,
        y / magnitude,
        z / magnitude
    );
  }

  public boolean isUnit() {
    return Double.compare(magnitude(), 1.0) == 0;
  }

  @Override
  public double dot(Vector other) {
    return x * other.x +
        y * other.y +
        z * other.z;
  }

  public double dot(Point other) {
    return x * other.x() +
        y * other.y() +
        z * other.z();
  }

  @Override
  public Vector cross(Vector other) {
    return new Vector(
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
      case 3 -> 0.0;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vector vector)) return false;

    return Math.abs(vector.x - x) < DELTA
        && Math.abs(vector.y - y) < DELTA
        && Math.abs(vector.z - z) < DELTA;
  }
}
