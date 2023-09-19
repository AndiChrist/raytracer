package io.github.andichrist.shapes;

import io.github.andichrist.Matrix;
import io.github.andichrist.Point;

public record Sphere(int id, Point origin, double radius) {

  public Sphere() {
    this(1, new Point(0, 0, 0), 1.0);
  }

  public Matrix transform() {
    return Matrix.identity().translate(
        origin.x(), origin.y(), origin.z()
    );
  }
}
