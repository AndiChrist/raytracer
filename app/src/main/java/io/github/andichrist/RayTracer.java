package io.github.andichrist;

import io.github.andichrist.gfx.Pixel;
import java.util.function.Consumer;
import javafx.scene.paint.Color;

public class RayTracer {

  public static final int DEPTH = 2;
  public static final double ε = 10e-10;

  public static void trace(Consumer<Pixel> pixelPainter) {
/*
    RayScene rayScene = RayScene.getInstance();

    // eye aka. "camera"
    Eye eye = rayScene.getEye();

    for (int i = 0; i < rayScene.getWidth(); i++) {
      for (int j = 0; j < rayScene.getHeight(); j++) {

        // parametric line equation: p(t)=e + t(s-e)
        // e: support vector aka. "eye position" (point on the line)
        // s-e: direction vector (vector on the line)
        // t=0: we're at view point
        // t=1: position on the screen
        // t>1: objects in scene
        Ray ray = new Ray(eye.getPosition(), eye.getDirection(i, j), ε);

        Color color = ColorUtil.castPrimary(ray, DEPTH);

        // accept pixel for pixel
        pixelPainter.accept(new Pixel(i, j, color));

      }
    }
    */
  }
}
