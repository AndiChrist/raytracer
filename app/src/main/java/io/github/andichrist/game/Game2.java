package io.github.andichrist.game;

import io.github.andichrist.Canvas;
import io.github.andichrist.gfx.PPM;

import java.awt.*;
import java.io.IOException;

import static io.github.andichrist.Tuple.point;
import static io.github.andichrist.Tuple.vector;

public interface Game2 {
  static void main(String[] args) throws IOException {

    var start = point(0, 1, 0);

    // projectile starts one unit above the origin.
    // velocity is normalized to 1 unit/tick.
    var a = vector(1,1.8,0).normalize();
    var velocity = a.multiply(11.25);

    var projectile = new Projectile(start, velocity);

    // gravity -0.1 unit/tick, and wind is -0.01 unit/tick.
    // e ← environment(vector(0, -0.1, 0), vector(-0.01, 0, 0))“
    var gravity = vector(0, -0.1, 0);
    var wind = vector(-0.01, 0, 0);
    var environment = new Environment(gravity, wind);

    var c = new Canvas(900, 550);

    while (projectile.position().y() > 0) {
      projectile = projectile.tick(environment, projectile);

      var x = (int) projectile.position().x();
      var y = (int) projectile.position().y();
      if (x > 0 && y > 0 && y <= c.getHeight() && x <= c.getWidth())
        c.setColor(x, c.getHeight()-y, Color.RED);
    }

    PPM.write(c, "projectile.ppm");
  }
}
