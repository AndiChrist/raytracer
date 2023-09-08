package io.github.andichrist;

import static io.github.andichrist.Tuple.dot;

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

  public static Sphere sphere() {
    return new Sphere(1);
  }

  public static double[] intersect(Sphere sphere, Ray ray) {
    // the vector from the sphere's center, to the ray origin
    // remember: the sphere is centered at the world origin
    var sphere_to_ray = ray.origin.subtract(Tuple.point(0, 0, 0));

    var a = dot(ray.direction, ray.direction);
    var b = 2 * dot(ray.direction, sphere_to_ray);
    var c = dot(sphere_to_ray, sphere_to_ray) - 1;

    var discriminant = b * b - 4 * a * c;

    if (discriminant < 0)
      return new double[]{};

    var t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
    var t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

    return new double[]{t1, t2};
  }
}
