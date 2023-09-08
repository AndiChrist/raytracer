package io.github.andichrist;

public record Ray(Tuple origin, Tuple direction) {
  public Ray {
    if (!origin.isPoint())
      throw new IllegalArgumentException("Origin should be a POINT");
    if (!direction.isVector())
      throw new IllegalArgumentException("Direction should be a VECTOR");
  }

  public Tuple position(double pos) {
    return origin.add(direction.multiply(pos));
  }
}
