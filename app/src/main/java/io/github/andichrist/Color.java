package io.github.andichrist;

public record Color(double x, double y, double z) implements MathOperations<Color> {
  @Override
  public Color add(Color other) {
    return new Color(x + other.x, y + other.y, z + other.z);
  }

  @Override
  public Color subtract(Color other) {
    return new Color(x - other.x, y - other.y, z - other.z);
  }

  @Override
  public Color multiply(double scalar) {
    return new Color(x * scalar, y * scalar, z * scalar);
  }

  @Override
  public Color multiply(Color other) {
    return new Color(x * other.x, y * other.y, z * other.z);
  }

  @Override
  public Color divide(double scalar) {
    return new Color(x / scalar, y / scalar, z / scalar);
  }

  @Override
  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  @Override
  public Color normalize() {
    double magnitude = magnitude();
    return new Color(
        x / magnitude,
        y / magnitude,
        z / magnitude
    );
  }

  @Override
  public double dot(Color other) {
    return x * other.x +
        y * other.y +
        z * other.z;
  }

  @Override
  public Color cross(Color other) {
    return new Color(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    );
  }

  public double get(String key) {
    return switch(key) {
      case "x","red" -> x;
      case "y","green" -> y;
      case "z","blue" -> z;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Color color)) return false;

    return Math.abs(color.x - x) < DELTA
        && Math.abs(color.y - y) < DELTA
        && Math.abs(color.z - z) < DELTA;
  }
}
