package io.github.andichrist;

import java.util.HashMap;
import java.util.Map;

public interface MatrixCache {
  // cache operations
  Map<String, Matrix> hash = new HashMap<>();

  static void set(String name, Matrix matrix) {
    hash.put(name, matrix);
  }

  static Matrix get(String name) {
    return hash.get(name);
  }
}