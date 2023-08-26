package io.github.andichrist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.andichrist.gfx.PPM;
import java.io.IOException;
import java.util.List;

import javafx.scene.paint.Color;

public class CanvasStepdefs {

  private Canvas c;
  private Color color;

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
    Color color = new Color(red, green, blue, 1);

    for (int y = 0; y < c.getHeight(); y++) {
      for (int x = 0; x < c.getWidth(); x++) {
        Color pixelColor = c.getColor(x, y);

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
    io.github.andichrist.Color red = (io.github.andichrist.Color) ObjectCache.get(colorName);

    double r = red.getX() > 1.0 ? 1.0 : red.getX() < 0 ? 0 : red.getX();
    double g = red.getY() > 1.0 ? 1.0 : red.getY() < 0 ? 0 : red.getY();
    double b = red.getZ() > 1.0 ? 1.0 : red.getZ() < 0 ? 0 : red.getZ();

    color = new Color(r, g, b, 1.0);
    c.setColor(x, y, color);
  }

  @Then("pixel_at\\({word}, {int}, {int}) = red")
  public void pixel_atCRed(String filename, int x, int y) throws IOException {
    Canvas image = PPM.read(filename + ".ppm");

    Color pixelColor = image.getColor(x, y);

    assertEquals(color.getRed(), pixelColor.getRed());
    assertEquals(color.getGreen(), pixelColor.getGreen());
    assertEquals(color.getBlue(), pixelColor.getBlue());
  }

  @When("ppm ← canvas_to_ppm\\({word})")
  public void ppmCanvas_to_ppm(final String filename) throws IOException {
    PPM.write(c, filename + ".ppm");
  }


  @Then("lines {int}-{int} of {word} are:")
  public void linesOfPpmAre(int arg0, int arg1, String filename, String text) throws IOException {
    final String[] lines = text.split("\n");

    List<String> result = PPM.readPPM("c.ppm");
    for (int i = 0; i < lines.length; i++) {
      assertEquals(result.get(i + arg0 - 1).replace("  ", " ").trim(), lines[i]);
    }
  }


  @When("every pixel of {word} is set to color\\({double}, {double}, {double})")
  public void everyPixelOfCIsSetToColor(String filename, double red, double green, double blue) throws IOException {
    Color color = new Color(red, green, blue, 1);

    for (int y = 0; y < c.getHeight(); y++) {
      for (int x = 0; x < c.getWidth(); x++) {
        c.setColor(x, y, color);
      }
    }
  }

  @Then("ppm ends with a newline character")
  public void ppmEndsWithANewlineCharacter() throws IOException {
    List<String> result = PPM.readPPM("c.ppm");
    assertEquals("", result.get(result.size()-1));
  }
}
