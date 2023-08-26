package io.github.andichrist.game;

import io.github.andichrist.Vector;

public class Environment {

  private Vector gravity;
  private Vector wind;

  public Environment(Vector gravity, Vector wind) {
    this.gravity = gravity;
    this.wind = wind;
  }

  public Vector getGravity() {
    return gravity;
  }

  public void setGravity(Vector gravity) {
    this.gravity = gravity;
  }

  public Vector getWind() {
    return wind;
  }

  public void setWind(Vector wind) {
    this.wind = wind;
  }
}
