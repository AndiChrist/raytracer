package io.github.andichrist;

public interface NTuple {

  boolean isPoint();
  boolean isVector();

  double getX();
  double getY();
  double getZ();
  double getW();

  double get(String key);
  double get(int key);

}
