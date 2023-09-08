package io.github.andichrist.game;

import io.github.andichrist.Tuple;

public record Environment(Tuple gravity, Tuple wind) {
  public Environment {
    if (!gravity.isVector())
      throw new IllegalArgumentException("gravity should be a VECTOR");
    if (!wind.isVector())
      throw new IllegalArgumentException("wind should be a VECTOR");
  }
}
