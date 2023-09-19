package io.github.andichrist;

import io.github.andichrist.shapes.Sphere;

import java.util.*;

import static java.util.Arrays.*;

public record Ray(Point origin, Vector direction) {

  public Point position(double pos) {
    return origin.add(direction.multiply(pos));
  }

  public static List<Intersection> intersect(Sphere sphere, Ray ray) {
    // the vector from the sphere's center, to the ray origin
    // remember: the sphere is centered at the world origin
    var o_c = ray.origin.subtract(sphere.origin()); // = (o - c)

    var u = ray.direction;
    var r = sphere.radius();

    var a = u.dot(u); // = u . u = ||u||^2
    var b = 2 * u.dot(o_c); // = 2[u . (o - c)]
    var c = o_c.dot(o_c) - r * r;
       // = (o - c) * (o - c) - r^2 = ||o - c||^2 - r^2

    var discriminant = b * b - 4 * a * c;

    if (discriminant < 0)
      return new ArrayList<>();

    var t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
    var t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

    var doubles = new ArrayList<>(asList(t1, t2));

    // Sortiere die ArrayList nach dem Namen
    Collections.sort(doubles, Comparator.comparingDouble(d -> d));

    var i1 = new Intersection(doubles.get(0), sphere);
    var i2 = new Intersection(doubles.get(1), sphere);

    return new ArrayList<>(asList(i1, i2));
  }

  public static Object intersections(ArrayList<Intersection> intersections) {
    // Entfernen von Intersection-Records mit negativen "t"-Werten und Sortieren
    // Entfernen Sie das Element mit negativem "t"-Wert
    intersections.removeIf(intersection -> intersection.t() < 0);

    // Sortieren der Liste nach "t" aufsteigend
    intersections.sort(Comparator.comparingDouble(Intersection::t));

    return intersections;
  }

  public static Object hit(ArrayList<Intersection> intersections) {
    return intersections.get(0);
  }

  public static Ray transform(Ray r, Matrix m) {
    var newOrigin = m.multiply(r.origin);
    var newDirection = m.multiply(r.direction);

    return new Ray(newOrigin, newDirection);
  }
}
