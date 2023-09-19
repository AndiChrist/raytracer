package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationStepDefinitions {

  @Given("{word} ← translation\\({int}, {int}, {int})")
  public void transformTranslation(String transformName, int x, int y, int z) {
    var translationMatrix = Matrix.identity().translate(x, y, z);

    ObjectCache.set(transformName, translationMatrix);
  }

  @Then("transform * {word} = point\\({int}, {int}, {int})")
  public void transformPoint(String pointName, int x, int y, int z) {
    var transform = ObjectCache.getMatrix("transform");
    var point = ObjectCache.getPoint(pointName);

    var expected = new Point(x, y, z);

    var actual = transform.multiply(point);

    assertEquals(expected, actual);
  }

  @Then("inv * {word} = point\\({double}, {double}, {double})")
  public void invPoint(String pointName, double x, double y, double z) {
    var transform = ObjectCache.getMatrix("inv");
    var point = ObjectCache.getPoint(pointName);

    var expected = new Point(x, y, z);

    var actual = transform.multiply(point);

    assertEquals(expected, actual);
  }

  @Then("transform * {word} = {word}")
  public void transformVV(String vectorNameA, String vectorNameB) {
    var transform = ObjectCache.getMatrix("transform");
    var vectorA = ObjectCache.getVector(vectorNameA);

    var expected = transform.multiply(vectorA);
    var actual = ObjectCache.getVector(vectorNameB);

    assertEquals(expected, actual);
  }

  @Given("{word} ← scaling\\({int}, {int}, {int})")
  public void transformScaling(String transformName, int x, int y, int z) {
    var translationMatrix = Matrix.identity().scale(x, y, z);

    ObjectCache.set(transformName, translationMatrix);
  }

  @Then("{word} * {word} = vector\\({int}, {int}, {int})")
  public void transformVVector(String matrixName, String vectorName, int x, int y, int z) {
    var matrix = ObjectCache.getMatrix(matrixName);
    var vector = ObjectCache.getVector(vectorName);

    var expected = matrix.multiply(vector);
    var actual = new Vector(x, y, z);

    assertEquals(expected, actual);
  }

  @Then("half_quarter * {word} = point\\({double}, {double}, {double})")
  public void half_quarterPPoint(String pointName, double x, double y, double z) {
    var matrix = ObjectCache.getMatrix("half_quarter");
    var point = ObjectCache.getPoint(pointName);

    var expected = new Point(x, y, z);
    var actual = matrix.multiply(point);

    assertEquals(expected, actual);
  }

  @Then("full_quarter * {word} = point\\({double}, {double}, {double})")
  public void full_quarterPPoint(String pointName, double x, double y, double z) {
    var matrix = ObjectCache.getMatrix("full_quarter");
    var point = ObjectCache.getPoint(pointName);

    var expected = new Point(x, y, z);
    var actual = matrix.multiply(point);

    assertEquals(expected, actual);
  }

  @And("{word} ← rotation_x\\({double})")
  public void rotation_x(String matrixName, double value) {
    var matrix = Matrix.rotate_x(value);

    ObjectCache.set(matrixName, matrix);
  }

  @And("{word} ← rotation_y\\({double})")
  public void rotation_y(String matrixName, double value) {
    var matrix = Matrix.rotate_y(value);

    ObjectCache.set(matrixName, matrix);
  }

  @And("{word} ← rotation_z\\({double})")
  public void rotation_z(String matrixName, double value) {
    var matrix = Matrix.rotate_z(value);

    ObjectCache.set(matrixName, matrix);
  }

  @Given("{word} ← shearing\\({int}, {int}, {int}, {int}, {int}, {int})")
  public void transformShearing(String matrixName, int xy, int xz, int yx, int yz, int zx, int zy) {
    var matrix = Matrix.shear(xy, xz, yx, yz, zx, zy);

    ObjectCache.set(matrixName, matrix);
  }

  @And("point {word} ← {word} * {word}")
  public void matrixMultiplication(String p1Name, String AName, String pName) {
    var matrixA = ObjectCache.getMatrix(AName);
    var pointP = ObjectCache.getPoint(pName);

    var p1 = matrixA.multiply(pointP);

    ObjectCache.set(p1Name, p1);
  }

  @Then("{word} = point\\({double}, {double}, {double})")
  public void p2_point(String pointName, double x, double y, double z) {
    Point actual;
    // check pointName for parameters (origin or direction)
    if (pointName.contains("origin")) {
      Ray ray = ObjectCache.getRay(pointName.split("\\.")[0]);
      actual = ray.origin();
    } else if (pointName.contains("direction")) {
      Ray ray = ObjectCache.getRay(pointName.split("\\.")[0]);
      Vector direction = ray.direction();
      actual = new Point(direction.x(), direction.y(), direction.z());
    } else {
      actual = ObjectCache.getPoint(pointName);
    }

    var expected = new Point(x, y, z);

    assertEquals(expected, actual);
  }

  @When("{word} ← {word} * {word} * {word}")
  public void tCBA(String TName, String CName, String BName, String AName) {
    var C = ObjectCache.getMatrix(CName);
    var B = ObjectCache.getMatrix(BName);
    var A = ObjectCache.getMatrix(AName);

    var T = C.multiply(B).multiply(A);

    ObjectCache.set(TName, T);
  }

  @Then("T * {word} = point\\({double}, {double}, {double})")
  public void tPPoint(String pointName, double x, double y, double z) {
    var transform = ObjectCache.getMatrix("T");
    var point = ObjectCache.getPoint(pointName);

    var expected = new Point(x, y, z);
    var actual = transform.multiply(point);

    assertEquals(expected, actual);
  }
}
