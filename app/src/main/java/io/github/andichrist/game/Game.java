package io.github.andichrist.game;

import io.github.andichrist.Tuple;

public class Game {
  public static void main(String[] args) {

    // projectile starts one unit above the origin.
    // velocity is normalized to 1 unit/tick.
    // p ← projectile(point(0, 1, 0), normalize(vector(1, 1, 0)))
    Projectile projectile = new Projectile(
        Tuple.newPoint(0, 1, 0),
        Tuple.newVector(1,1,0).normalize()
    );

    // gravity -0.1 unit/tick, and wind is -0.01 unit/tick.
    // e ← environment(vector(0, -0.1, 0), vector(-0.01, 0, 0))“
    Environment environment = new Environment(
        Tuple.newVector(0, -0.1, 0),
        Tuple.newVector(-0.01, 0, 0)
    );

    while (projectile.position().y() > 0) {
      projectile = projectile.tick(environment, projectile);

      System.out.println("projectile = " + projectile.position());
    }
  }
}
