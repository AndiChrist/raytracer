package io.github.andichrist;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayHandler {
  public static int[] concat(String a, String b) {
    return IntStream.concat(
        Arrays.stream((int[])ObjectCache.get(a)),
        Arrays.stream((int[])ObjectCache.get(b))
    ).toArray();
  }
}
