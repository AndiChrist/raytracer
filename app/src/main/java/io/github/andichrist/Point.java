package io.github.andichrist;

public record Point(double x, double y, double z) implements NTuple {

  public static double POINT = 1.0;

  public Point(Tuple tuple) {
    this(tuple.x(), tuple.y(), tuple.z());
  }

  public Tuple toTuple() {
    return new Tuple(x, y, z, POINT);
  }

  public Vector toVector() {
    return new Vector(x, y, z);
  }

  //@Override
  public double get(int key) {
    return switch(key) {
      case 0 -> x;
      case 1 -> y;
      case 2 -> z;
      case 3 -> POINT;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  public double get(String key) {
    return switch(key) {
      case "x" -> x;
      case "y" -> y;
      case "z" -> z;
      case "w" -> POINT;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  public Point add(Point point) {
    return new Point(
        x + point.x(),
        y + point.y(),
        z + point.z()
    );
  }

  public Point add(Vector vector) {
    return new Point(
        x + vector.x(),
        y + vector.y(),
        z + vector.z()
    );
  }

  public Point subtract(Vector vector) {
    return new Point(
        x - vector.x(),
        y - vector.y(),
        z - vector.z()
    );
  }

  // TODO: correct?
  public Vector subtract(Point point) {
    return new Vector(
        x - point.x(),
        y - point.y(),
        z - point.z()
    );
  }

  @Override
  public boolean equals(Object o) {
    double epsilon = 1e-5; // Toleranz für den Vergleich

    if (this == o) return true;
    if (!(o instanceof Point point)) return false;

    return Math.abs(point.x - x) < epsilon
        && Math.abs(point.y - y) < epsilon
        && Math.abs(point.z - z) < epsilon;
  }
}
