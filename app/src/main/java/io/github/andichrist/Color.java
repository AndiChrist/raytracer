package io.github.andichrist;

public record Color(double x, double y, double z) implements NTuple {

  public double get(int key) {
    return switch(key) {
      case 0 -> x;
      case 1 -> y;
      case 2 -> z;
      default -> throw new IllegalStateException("Unexpected value: " + key);
    };
  }

  public double get(String colorTone) {
    return switch (colorTone) {
      case "red" -> x;
      case "green" -> y;
      case "blue" -> z;
      default -> throw new IllegalStateException("Unexpected value: " + colorTone);
    };
  }

  public Color add(Color color) {
    return new Color(
        x + color.x(),
        y + color.y(),
        z + color.z()
    );
  }

  public Color subtract(Color color) {
    return new Color(
        x - color.x(),
        y - color.y(),
        z - color.z()
    );
  }

  public Color multiply(double factor) {
    return new Color(
        x * factor,
        y * factor,
        z * factor
    );
  }

  // Hadamard product (or Schur product)
  public Color multiply(Color color) {
    return new Color(
        x * color.x,
        y * color.y,
        z * color.z
    );
  }

  @Override
  public boolean equals(Object o) {
    double epsilon = 1e-5; // Toleranz für den Vergleich

    if (this == o) return true;
    if (!(o instanceof Color color)) return false;

    return Math.abs(color.x - x) < epsilon &&
        Math.abs(color.y - y) < epsilon &&
        Math.abs(color.z - z) < epsilon;
  }
}

