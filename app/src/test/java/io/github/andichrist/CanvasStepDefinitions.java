package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.andichrist.gfx.PPM;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanvasStepDefinitions {

  private Canvas c;

  @Given("c ← canvas\\({int}, {int})")
  public void cCanvas(int width, int height) {
    c = new Canvas(width, height);
  }

  @Then("width = {int}")
  public void getWidth(int arg0) {
    assertEquals(c.getWidth(), arg0);
  }

  @And("height = {int}")
  public void getHeight(int arg0) {
    assertEquals(c.getHeight(), arg0);
  }

  @And("every pixel of c is color\\({int}, {int}, {int})")
  public void everyPixelOfCIsColor(int red, int green, int blue) {
    var color = new Color(red, green, blue, 1);

    for (int y = 0; y < c.getHeight(); y++) {
      for (int x = 0; x < c.getWidth(); x++) {
        var pixelColor = c.getColor(x, y);

        assertEquals(color.getRed(), pixelColor.getRed());
        assertEquals(color.getGreen(), pixelColor.getGreen());
        assertEquals(color.getBlue(), pixelColor.getBlue());
      }
    }
  }
/*
  @And("red ← color\\({int}, {int}, {int})")
  public void redColor(int red, int green, int blue) {
    colorRed = new Color(red, green, blue, 1);
  }
*/
  @When("write_pixel\\(c, {int}, {int}, {word})")
  public void write_pixelCRed(int x, int y, String colorName) {
    var pixelColor = (Tuple) ObjectCache.get(colorName);

    var r = pixelColor.x() > 1.0 ? 1.0 : pixelColor.x() < 0 ? 0 : pixelColor.x();
    var g = pixelColor.y() > 1.0 ? 1.0 : pixelColor.y() < 0 ? 0 : pixelColor.y();
    var b = pixelColor.z() > 1.0 ? 1.0 : pixelColor.z() < 0 ? 0 : pixelColor.z();

    var color = new Color(mapDoubleTo255(r), mapDoubleTo255(g), mapDoubleTo255(b));

    c.setColor(x, y, color);
  }

  public static int mapDoubleTo255(double value) {
    if (value < 0.0) {
      return 0;
    } else if (value > 1.0) {
      return 255;
    } else {
      return (int) Math.ceil(value * 255);
    }
  }

  @Then("pixel_at\\({word}, {int}, {int}) = {word}")
  public void pixel_atCRed(String filename, int x, int y, String colorName) throws IOException {
    var red = (Tuple) ObjectCache.get(colorName);

    var image = PPM.read(filename + ".ppm");

    var pixelColor = image.getColor(x, y);

    assertEquals(mapDoubleTo255(red.x()), pixelColor.getRed());
    assertEquals(mapDoubleTo255(red.y()), pixelColor.getGreen());
    assertEquals(mapDoubleTo255(red.z()), pixelColor.getBlue());
  }

  @When("ppm ← canvas_to_ppm\\({word})")
  public void ppmCanvas_to_ppm(final String filename) throws IOException {
    PPM.write(c, filename + ".ppm");
  }


  @Then("lines {int}-{int} of {word} are:")
  public void linesOfPpmAre(int arg0, int arg1, String filename, String text) throws IOException {
    var lines = text.split("\n");

    var result = PPM.readPPM("c.ppm");
    for (int i = 0; i < lines.length; i++) {
      assertEquals(result.get(i + arg0 - 1).replace("  ", " ").trim(), lines[i]);
    }
  }


  @When("every pixel of {word} is set to color\\({double}, {double}, {double})")
  public void everyPixelOfCIsSetToColor(String filename, double red, double green, double blue) throws IOException {
    var color = new Color(mapDoubleTo255(red), mapDoubleTo255(green), mapDoubleTo255(blue));

    for (int y = 0; y < c.getHeight(); y++) {
      for (int x = 0; x < c.getWidth(); x++) {
        c.setColor(x, y, color);
      }
    }
  }

  @Then("ppm ends with a newline character")
  public void ppmEndsWithANewlineCharacter() throws IOException {
    var result = PPM.readPPM("c.ppm");
    assertEquals("", result.get(result.size()-1));
  }
}
