package io.github.andichrist.game;

import io.github.andichrist.Tuple;

public record Projectile(Tuple position, Tuple velocity) {

  public Projectile tick(Environment env, Projectile proj) {
    Tuple position = proj.position().add(proj.velocity());
    Tuple velocity = proj.velocity().add(env.gravity().add(env.wind()));

    return new Projectile(position, velocity);
  }
}
