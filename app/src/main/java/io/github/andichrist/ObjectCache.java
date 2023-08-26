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
}
