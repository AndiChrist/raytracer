package io.github.andichrist;

public record Sphere(int id, Tuple origin, double radius) {

  public Sphere() {
    this(1, Tuple.point(0, 0, 0), 1.0);
  }

  public Matrix transform() {
    return Matrix.identity().translate(
        origin.x(), origin.y(), origin.z()
    );
  }
}
