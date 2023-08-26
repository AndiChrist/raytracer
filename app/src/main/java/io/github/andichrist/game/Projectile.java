package io.github.andichrist.game;

import io.github.andichrist.Point;
import io.github.andichrist.TupleHelper;
import io.github.andichrist.Vector;

public class Projectile {

  private Point position;
  private Vector velocity;

  public Projectile(Point position, Vector velocity) {
    this.position = position;
    this.velocity = velocity;
  }

  public Projectile tick(Environment env, Projectile proj) {
    position = (Point) TupleHelper.add(proj.getPosition(), proj.getVelocity(), Point.class);
    velocity = (Vector) TupleHelper.add(proj.getVelocity(),
        TupleHelper.add(env.getGravity(), env.getWind(), Vector.class),
        Vector.class
    );

    return  new Projectile(position, velocity);
  }


  public Point getPosition() {
    return position;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public Vector getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

}
