package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationStepDefinitions {

  @Given("{word} ← translation\\({int}, {int}, {int})")
  public void transformTranslation(String transformName, int x, int y, int z) {
    Matrix translationMatrix = Matrix.identityMatrix().translation(x, y, z);

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

  @Then("inv * {word} = point\\({double}, {double}, {double})")
  public void invPoint(String pointName, double x, double y, double z) {
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

  @Given("{word} ← scaling\\({int}, {int}, {int})")
  public void transformScaling(String transformName, int x, int y, int z) {
    Matrix translationMatrix = Matrix.identityMatrix().scaling(x, y, z);

    ObjectCache.set(transformName, translationMatrix);
  }

  @Then("{word} * {word} = vector\\({int}, {int}, {int})")
  public void transformVVector(String matrixName, String vectorName, int x, int y, int z) {
    Matrix matrix = (Matrix) ObjectCache.get(matrixName);
    Vector vector = (Vector) ObjectCache.get(vectorName);
    Vector actual = new Vector(x, y, z);

    Vector expected = matrix.multiply(vector);

    assertEquals(expected, actual);
  }

  @And("{word} ← rotation_x\\(π \\/ {int})")
  public void rotation_x(String matrixName, int divisor) {
    Matrix matrix = Matrix.rotation_x( Math.PI / divisor);

    ObjectCache.set(matrixName, matrix);
  }

  @Then("half_quarter * {word} = point\\({double}, {double}, {double})")
  public void half_quarterPPoint(String pointName, double x, double y, double z) {
    Matrix matrix = (Matrix) ObjectCache.get("half_quarter");
    Point p = (Point) ObjectCache.get(pointName);

    Point expected = new Point(x, y, z);
    Point actual = matrix.multiply(p);

    assertEquals(expected, actual);
  }

  @Then("full_quarter * {word} = point\\({double}, {double}, {double})")
  public void full_quarterPPoint(String pointName, double x, double y, double z) {
    Matrix matrix = (Matrix) ObjectCache.get("full_quarter");
    Point p = (Point) ObjectCache.get(pointName);

    Point expected = new Point(x, y, z);
    Point actual = matrix.multiply(p);

    assertEquals(expected, actual);
  }

  @And("{word} ← rotation_y\\(π \\/ {int})")
  public void rotation_y(String matrixName, int divisor) {
    Matrix matrix = Matrix.rotation_y( Math.PI / divisor);

    ObjectCache.set(matrixName, matrix);
  }

}
