package io.github.andichrist.game;

import io.github.andichrist.Point;
import io.github.andichrist.Vector;

public record Projectile(Point position, Vector velocity) {

  public Projectile tick(Environment env, Projectile proj) {
    Point position = proj.position().add(proj.velocity());
    Vector velocity = proj.velocity().add(env.gravity().add(env.wind()));

    return  new Projectile(position, velocity);
  }
}
