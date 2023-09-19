package io.github.andichrist;

public sealed interface MathOperations<T> permits Point, Vector, Color, Tuple {

  double DELTA = 1e-5;

  T add(T other);
  T subtract(T other);
  T multiply(double scalar);
  T multiply(T other);
  T divide(double scalar);
  double magnitude();
  T normalize();
  double dot(T other);
  T cross(T other);
}

