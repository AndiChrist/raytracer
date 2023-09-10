package io.github.andichrist;

public record Sphere(int id, Tuple origin, double radius) {

  public Sphere() {
    this(1, Tuple.point(0, 0, 0), 1.0);
  }
}
