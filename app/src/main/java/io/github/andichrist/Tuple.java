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

  public static Tuple point(double x, double y, double z) {
    return new Tuple(x, y, z, POINT);
  }
  public static Tuple vector(double x, double y, double z) {
    return new Tuple(x, y, z, VECTOR);
  }
  public static Tuple color(double x, double y, double z) {
    return new Tuple(x, y, z, VECTOR);
  }

  public Tuple(double x, double y, double z) {
    this(x, y, z, VECTOR);
  }

  public Tuple add(Tuple tuple) {
    return add(this, tuple);
  }

  public static Tuple add(Tuple tupleA, Tuple tupleB) {
    return new Tuple(
        tupleA.x + tupleB.x(),
        tupleA.y + tupleB.y(),
        tupleA.z + tupleB.z()
    );
  }

  public Tuple subtract(Tuple tuple) {
    return subtract(this, tuple);
  }

  public static Tuple subtract(Tuple tupleA, Tuple tupleB) {
    return new Tuple(
        tupleA.x - tupleB.x(),
        tupleA.y - tupleB.y(),
        tupleA.z - tupleB.z()
    );
  }

  public Tuple multiply(double factor) {
    return multiply(this, factor);
  }

  public static Tuple multiply(Tuple tuple, double factor) {
    return new Tuple(
        tuple.x * factor,
        tuple.y * factor,
        tuple.z * factor
    );
  }

  public Tuple multiply(Tuple tuple) {
    return multiply(this, tuple);
  }

  public static Tuple multiply(Tuple tupleA, Tuple tupleB) {
    return new Tuple(
        tupleA.x * tupleB.x,
        tupleA.y * tupleB.y,
        tupleA.z * tupleB.z
    );
  }

  public Tuple divide(double value) {
    return divide(this, value);
  }

  public static Tuple divide(Tuple tuple, double value) {
    return new Tuple(
        tuple.x / value,
        tuple.y / value,
        tuple.z / value
    );
  }

  public Tuple normalize() {
    return normalize(this);
  }

  public static Tuple normalize(Tuple tuple) {
    double magnitude =  magnitude(tuple);
    return new Tuple(
        tuple.x / magnitude,
        tuple.y / magnitude,
        tuple.z / magnitude
    );
  }

  public double magnitude() {
    return magnitude(this);
  }

  public static double magnitude(Tuple tuple) {
    return Math.sqrt(tuple.x * tuple.x + tuple.y * tuple.y + tuple.z * tuple.z);
  }

  public double dot(Tuple tuple) {
    return dot(this, tuple);
  }

  public static double dot(Tuple tupleA, Tuple tupleB) {
    return tupleA.x * tupleB.x +
        tupleA.y * tupleB.y +
        tupleA.z * tupleB.z;
  }

  public Tuple cross(Tuple tuple) {
    return cross(this, tuple);
  }

  public static Tuple cross(Tuple tupleA, Tuple tupleB) {
    return new Tuple(
        tupleA.y * tupleB.z - tupleA.z * tupleB.y,
        tupleA.z * tupleB.x - tupleA.x * tupleB.z,
        tupleA.x * tupleB.y - tupleA.y * tupleB.x
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

  @Override
  public String toString() {
    if (this.isPoint())
      return "Point[x=" + x + ", y=" + y + ", z=" + z + "]";
    else if (this.isVector())
      return "Vector[x=" + x + ", y=" + y + ", z=" + z + "]";
    else
      return "Tuple[x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + "]";
  }
}
