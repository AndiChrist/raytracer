package io.github.andichrist;

public record Tuple(double x, double y, double z, double w) {

  public static final Tuple ZERO = new Tuple(0, 0, 0, 0);

  public static double VECTOR = 0.0;
  public static double POINT = 1.0;

  public boolean isZero() {
      return this.equals(Tuple.ZERO);
  }

  public boolean isPoint() {
    return w == POINT;
  }
  public boolean isVector() {
    return w == VECTOR;
  }

  public Tuple asPoint() {
    return newPoint(x, y, z);
  }
  public Tuple asVector() {
    return newVector(x, y, z);
  }

  public static Tuple newPoint(double x, double y, double z) {
    return new Tuple(x, y, z, POINT);
  }
  public static Tuple newVector(double x, double y, double z) {
    return new Tuple(x, y, z, VECTOR);
  }
  public static Tuple newColor(double x, double y, double z) {
    return new Tuple(x, y, z, VECTOR);
  }

  public Tuple(double x, double y, double z) {
    this(x, y, z, VECTOR);
  }

  /*
  public Point toPoint() {
    if (w != Point.POINT) throw new RuntimeException("Tuple is not a Point.");
    return new Point(this);
  }

  public Vector toVector() {
    if (w != Vector.VECTOR) throw new RuntimeException("Tuple is not a Vector.");
    return new Vector(this);
  }
   */

  public Tuple add(Tuple tuple) {
    return new Tuple(
        x + tuple.x(),
        y + tuple.y(),
        z + tuple.z(),
        w + tuple.w()
    );
  }

  public Tuple subtract(Tuple tuple) {
    return new Tuple(
        x - tuple.x(),
        y - tuple.y(),
        z - tuple.z(),
        w - tuple.w()
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

  public Tuple multiply(Tuple tuple) {
    return new Tuple(
        x * tuple.x,
        y * tuple.y,
        z * tuple.z,
        w * tuple.w
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

  public Tuple normalize() {
    double magnitude =  magnitude();
    return new Tuple(
        x / magnitude,
        y / magnitude,
        z / magnitude,
        w / magnitude
    );
  }

  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  public double dot(Tuple tuple) {
    return x * tuple.x +
        y * tuple.y +
        z * tuple.z;
  }

  public Tuple cross(Tuple tuple) {
    return new Tuple(
        y * tuple.z - z * tuple.y,
        z * tuple.x - x * tuple.z,
        x * tuple.y - y * tuple.x
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

  public double get(String key) {
    return switch(key) {
      case "x","red" -> x;
      case "y","green" -> y;
      case "z","blue" -> z;
      case "w" -> w;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  @Override
  public boolean equals(Object o) {
    double epsilon = 1e-5; // Toleranz für den Vergleich

    if (this == o) return true;
    if (!(o instanceof Tuple tuple)) return false;

    return Math.abs(tuple.x - x) < epsilon
        && Math.abs(tuple.y - y) < epsilon
        && Math.abs(tuple.z - z) < epsilon;
  }
}
