package io.github.andichrist;

import java.util.*;

import static io.github.andichrist.Tuple.dot;
import static java.util.Arrays.*;

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

  public static List<Intersection> intersect(Sphere sphere, Ray ray) {
    // the vector from the sphere's center, to the ray origin
    // remember: the sphere is centered at the world origin
    var o_c = ray.origin.subtract(sphere.origin()); // = (o - c)

    var u = ray.direction;
    var r = sphere.radius();

    var a = dot(u, u); // = u . u = ||u||^2
    var b = 2 * dot(u, o_c); // = 2[u . (o - c)]
    var c = dot(o_c, o_c) - r * r;
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
    Iterator<Intersection> iterator = intersections.iterator();
    while (iterator.hasNext()) {
      Intersection intersection = iterator.next();
      if (intersection.t() < 0) {
        iterator.remove(); // Entfernen Sie das Element mit negativem "t"-Wert
      }
    }

    // Sortieren der Liste nach "t" aufsteigend
    intersections.sort((a, b) -> Double.compare(a.t(), b.t()));

    return intersections;
  }

  public static Object hit(ArrayList<Intersection> intersections) {
    return intersections.get(0);
  }
}
