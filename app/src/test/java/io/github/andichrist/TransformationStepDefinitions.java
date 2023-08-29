package io.github.andichrist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationStepDefinitions {

  @Given("{word} ← translation\\({int}, {int}, {int})")
  public void transformTranslation(String transformName, int x, int y, int z) {
    Matrix translationMatrix = Matrix.identityMatrix();
    translationMatrix.matrix[0][3] = x;
    translationMatrix.matrix[1][3] = y;
    translationMatrix.matrix[2][3] = z;

    ObjectCache.set(transformName, translationMatrix);
  }

  @Then("transform * {word} = point\\({int}, {int}, {int})")
  public void transformPoint(String pointName, int x, int y, int z) {
    Matrix transform = (Matrix) ObjectCache.get("transform");
    Point point = (Point) ObjectCache.get(pointName);

    Point expectedPoint = new Point(x, y, z);

    Point actual = transform.multiply(point);

    assertEquals(expectedPoint, actual);
  }

  @Then("inv * {word} = point\\({int}, {int}, {int})")
  public void invPoint(String pointName, int x, int y, int z) {
    Matrix transform = (Matrix) ObjectCache.get("inv");
    Point point = (Point) ObjectCache.get(pointName);

    Point expectedPoint = new Point(x, y, z);

    Point actual = transform.multiply(point);

    assertEquals(expectedPoint, actual);
  }

  @Then("transform * {word} = {word}")
  public void transformVV(String vectorNameA, String vectorNameB) {
    Matrix transform = (Matrix) ObjectCache.get("transform");
    Vector vectorA = (Vector) ObjectCache.get(vectorNameA);
    Vector vectorB = (Vector) ObjectCache.get(vectorNameB);

    Vector expected = transform.multiply(vectorA);

    assertEquals(expected, vectorB);
  }
}
