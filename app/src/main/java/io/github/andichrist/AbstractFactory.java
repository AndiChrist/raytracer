package io.github.andichrist;

public interface AbstractFactory<T> {
  T create(String tupleType, double x, double y, double z, double w);
}
