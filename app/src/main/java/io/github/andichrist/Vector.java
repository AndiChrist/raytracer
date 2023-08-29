package io.github.andichrist;

public record Vector(double x, double y, double z) implements NTuple {

  public static double VECTOR = 0.0;

  public Vector(Tuple tuple) {
    this(tuple.x(), tuple.y(), tuple.z());
    assert tuple.w() == VECTOR;
  }

  public Tuple toTuple() {
    return new Tuple(x, y, z, VECTOR);
  }

  public Point toPoint() {
    return new Point(x, y, z);
  }

  @Override
  public double get(int key) {
    return switch(key) {
      case 0 -> x;
      case 1 -> y;
      case 2 -> z;
      case 3 -> VECTOR;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  @Override
  public double get(String key) {
    return switch(key) {
      case "x" -> x;
      case "y" -> y;
      case "z" -> z;
      case "w" -> VECTOR;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  public Vector add(Vector vector) {
    return new Vector(
        x + vector.x,
        y + vector.y,
        z + vector.z
    );
  }

  public Vector add(Point point) {
    return new Vector(
        x + point.x(),
        y + point.y(),
        z + point.z()
    );
  }

  public Vector subtract(Vector vector) {
    return new Vector(
        x - vector.x,
        y - vector.y,
        z - vector.z
    );
  }

  public Vector normalize() {
    double magnitude =  magnitude();
    return new Vector(
        x / magnitude,
        y / magnitude,
        z / magnitude
    );
  }

  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  public Vector multiply(double factor) {
    return new Vector(
        x * factor,
        y * factor,
        z * factor
    );
  }

  public double dot(Vector vector) {
    return x * vector.x +
        y * vector.y +
        z * vector.z;
  }

  public Vector cross(Vector vector) {
    return new Vector(
        y * vector.z - z * vector.y,
        z * vector.x - x * vector.z,
        x * vector.y - y * vector.x
    );
  }
}
