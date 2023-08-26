package io.github.andichrist;

import java.util.HashMap;
import java.util.Map;

public interface TupleCache {
  Map<String, NTuple> hash = new HashMap<>();

  static void set(String name, NTuple tuple) {
    hash.put(name, tuple);
  }

  static NTuple get(String a) {
    return hash.get(a);
  }
}