package io.github.andichrist;

import java.util.HashMap;
import java.util.Map;

public interface ObjectCache {
  Map<String, Object> hash = new HashMap<>();

  static void set(String name, Object tuple) {
    hash.put(name, tuple);
  }

  static Object get(String a) {
    return hash.get(a);
  }

  static Sphere getSphere(String a) {
    return (Sphere) get(a);
  }

  static Ray getRay(String a) {
    return (Ray) get(a);
  }

  static Intersection getIntersection(String a) {
    return (Intersection) get(a);
  }

  static Matrix getMatrix(String a) {
    return (Matrix) get(a);
  }

  static Tuple getVector(String a) {
    return (Tuple) get(a);
  }
  static Tuple getPoint(String a) {
    return (Tuple) get(a);
  }
  static Tuple getColor(String a) {
    return (Tuple) get(a);
  }

  static Tuple getTuple(String a) {
    return (Tuple) get(a);
  }

}
