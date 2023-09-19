package io.github.andichrist.game;

import io.github.andichrist.Point;
import io.github.andichrist.Vector;

import static io.github.andichrist.Tuple.*;

public interface Game {
  static void main(String[] args) {

    // projectile starts one unit above the origin.
    // velocity is normalized to 1 unit/tick.
    // p ← projectile(point(0, 1, 0), normalize(vector(1, 1, 0)))
    var projectile = new Projectile(
        new Point(0, 1, 0),
        new Vector(1,1,0).normalize()
    );

    // gravity -0.1 unit/tick, and wind is -0.01 unit/tick.
    // e ← environment(vector(0, -0.1, 0), vector(-0.01, 0, 0))“
    var environment = new Environment(
        new Vector(0, -0.1, 0),
        new Vector(-0.01, 0, 0)
    );

    while (projectile.position().y() > 0) {
      projectile = projectile.tick(environment, projectile);

      System.out.println("projectile = " + projectile.position());
    }
  }
}
