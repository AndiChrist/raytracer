package io.github.andichrist.game;

import io.github.andichrist.Canvas;
import io.github.andichrist.Matrix;
import io.github.andichrist.Tuple;
import io.github.andichrist.gfx.PPM;
import javafx.scene.paint.Color;

import java.io.IOException;

public interface Clock {
  static void main(String[] args) throws IOException {
    var origin = Tuple.point(0, 0, 0);

    var c = new Canvas(500, 500);
    var radius = c.getWidth() * 3 / 8;

    // given: position of twelve o'clock
    var twelve = Tuple.point(0, 0, 1);
    // compute y-axis rotation for each hour
    for (int i = 1; i <= 12; i++) {
      var r = Matrix.rotate_y(i * Math.PI / 6);
      // compute position of "i" o'clock by rotating twelve o'clock
      var time = r.multiply(twelve).add(origin);

      c.setColor(
          (c.getWidth() / 2) + (int) (time.x() * radius),
          (c.getWidth() / 2) + (int) (time.z() * radius),
          Color.RED);
    }
    PPM.write(c, "clock.ppm");
  }
}
