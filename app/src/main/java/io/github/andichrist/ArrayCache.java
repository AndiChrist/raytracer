package io.github.andichrist;

import java.util.HashMap;
import java.util.Map;

public interface ArrayCache {
  Map<String, int[]> hash = new HashMap<>();

  static void set(String a, int[] ints) {
    hash.put(a, ints);
  }

  static int[] get(String a) {
    return hash.get(a);
  }
}
