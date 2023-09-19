package io.github.andichrist;

import io.github.andichrist.shapes.Sphere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ObjectCache {
  Map<String, Object> hash = new HashMap<>();

  static void set(String name, Object tuple) {
    hash.put(name, tuple);
  }

  static MathOperations get(String name) {
    return (MathOperations) hash.get(name);
  }

  static Sphere getSphere(String name) {
    return (Sphere) hash.get(name);
  }

  static Ray getRay(String name) {
    return (Ray) hash.get(name);
  }

  static Intersection getIntersection(String name) {
    return (Intersection) hash.get(name);
  }

  static Matrix getMatrix(String name) {
    return (Matrix) hash.get(name);
  }

  static Vector getVector(String name) {
    return (Vector) hash.get(name);
  }

  static Point getPoint(String name) {
    return (Point) hash.get(name);
  }

  static Color getColor(String name) {
    return (Color) hash.get(name);
  }

  static Tuple getTuple(String name) {
    return (Tuple) hash.get(name);
  }

  static ArrayList getArrayList(String name) {
    return (ArrayList) hash.get(name);
  }
}
