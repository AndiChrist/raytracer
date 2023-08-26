package io.github.andichrist.game;

import io.github.andichrist.Canvas;
import io.github.andichrist.Point;
import io.github.andichrist.TupleHelper;
import io.github.andichrist.Vector;
import io.github.andichrist.gfx.PPM;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Game2 {
  public static void main(String[] args) throws IOException {

    Point start = new Point(0, 1, 0);

    // projectile starts one unit above the origin.
    // velocity is normalized to 1 unit/tick.
    Vector a = (Vector) TupleHelper.normalize(new Vector(1,1.8,0), Vector.class);
    Vector velocity = (Vector) TupleHelper.multiply(11.25, a, Vector.class);

    Projectile projectile = new Projectile(start, velocity);

    // gravity -0.1 unit/tick, and wind is -0.01 unit/tick.
    // e ← environment(vector(0, -0.1, 0), vector(-0.01, 0, 0))“
    Vector gravity = new Vector(0, -0.1, 0);
    Vector wind = new Vector(-0.01, 0, 0);
    Environment environment = new Environment(
            gravity, wind
    );

    Canvas c = new Canvas(900, 550);

    while (projectile.getPosition().getY() > 0) {
      projectile.tick(environment, projectile);

      //System.out.println("projectile = " + projectile.getPosition());

      int x = (int) projectile.getPosition().getX();
      int y = (int) projectile.getPosition().getY();
      if (x > 0 && y > 0 && y <= c.getHeight() && x <= c.getWidth())
        c.setColor(x, c.getHeight()-y, Color.RED);
    }

    PPM.write(c, "projectile.ppm");
  }
}
