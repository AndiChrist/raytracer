package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationStepDefinitions {

  @Given("{word} ← translation\\({int}, {int}, {int})")
  public void transformTranslation(String transformName, int x, int y, int z) {
    Matrix translationMatrix = Matrix.identity().translate(x, y, z);

    ObjectCache.set(transformName, translationMatrix);
  }

  @Then("transform * {word} = point\\({int}, {int}, {int})")
  public void transformPoint(String pointName, int x, int y, int z) {
    Matrix transform = (Matrix) ObjectCache.get("transform");
    Tuple point = (Tuple) ObjectCache.get(pointName);

    Tuple expectedPoint = Tuple.newPoint(x, y, z);

    Tuple actual = transform.multiply(point);

    assertEquals(expectedPoint, actual);
  }

  @Then("inv * {word} = point\\({double}, {double}, {double})")
  public void invPoint(String pointName, double x, double y, double z) {
    Matrix transform = (Matrix) ObjectCache.get("inv");
    Tuple point = (Tuple) ObjectCache.get(pointName);

    Tuple expectedPoint = Tuple.newPoint(x, y, z);

    Tuple actual = transform.multiply(point);

    assertEquals(expectedPoint, actual);
  }

  @Then("transform * {word} = {word}")
  public void transformVV(String vectorNameA, String vectorNameB) {
    Matrix transform = (Matrix) ObjectCache.get("transform");
    Tuple vectorA = (Tuple) ObjectCache.get(vectorNameA);
    Tuple vectorB = (Tuple) ObjectCache.get(vectorNameB);

    Tuple expected = transform.multiply(vectorA);

    assertEquals(expected, vectorB);
  }

  @Given("{word} ← scaling\\({int}, {int}, {int})")
  public void transformScaling(String transformName, int x, int y, int z) {
    Matrix translationMatrix = Matrix.identity().scale(x, y, z);

    ObjectCache.set(transformName, translationMatrix);
  }

  @Then("{word} * {word} = vector\\({int}, {int}, {int})")
  public void transformVVector(String matrixName, String vectorName, int x, int y, int z) {
    Matrix matrix = (Matrix) ObjectCache.get(matrixName);
    Tuple vector = (Tuple) ObjectCache.get(vectorName);
    Tuple actual = Tuple.newVector(x, y, z);

    Tuple expected = matrix.multiply(vector);

    assertEquals(expected, actual);
  }

  @Then("half_quarter * {word} = point\\({double}, {double}, {double})")
  public void half_quarterPPoint(String pointName, double x, double y, double z) {
    Matrix matrix = (Matrix) ObjectCache.get("half_quarter");
    Tuple p = (Tuple) ObjectCache.get(pointName);

    Tuple expected = Tuple.newPoint(x, y, z);
    Tuple actual = matrix.multiply(p);

    assertEquals(expected, actual);
  }

  @Then("full_quarter * {word} = point\\({double}, {double}, {double})")
  public void full_quarterPPoint(String pointName, double x, double y, double z) {
    Matrix matrix = (Matrix) ObjectCache.get("full_quarter");
    Tuple p = (Tuple) ObjectCache.get(pointName);

    Tuple expected = Tuple.newPoint(x, y, z);
    Tuple actual = matrix.multiply(p);

    assertEquals(expected, actual);
  }

  @And("{word} ← rotation_x\\({double})")
  public void rotation_x(String matrixName, double value) {
    Matrix matrix = Matrix.rotate_x(value);

    ObjectCache.set(matrixName, matrix);
  }

  @And("{word} ← rotation_y\\({double})")
  public void rotation_y(String matrixName, double value) {
    Matrix matrix = Matrix.rotate_y(value);

    ObjectCache.set(matrixName, matrix);
  }

  @And("{word} ← rotation_z\\({double})")
  public void rotation_z(String matrixName, double value) {
    Matrix matrix = Matrix.rotate_z(value);

    ObjectCache.set(matrixName, matrix);
  }

  @Given("{word} ← shearing\\({int}, {int}, {int}, {int}, {int}, {int})")
  public void transformShearing(String matrixName, int xy, int xz, int yx, int yz, int zx, int zy) {
    Matrix matrix = Matrix.shear(xy, xz, yx, yz, zx, zy);

    ObjectCache.set(matrixName, matrix);
  }

  @And("point {word} ← {word} * {word}")
  public void matrixMultiplication(String p1Name, String AName, String pName) {
    Matrix matrixA = (Matrix) ObjectCache.get(AName);
    Tuple pointP = (Tuple) ObjectCache.get(pName);

    Tuple p1 = matrixA.multiply(pointP);

    ObjectCache.set(p1Name, p1);
  }

  @Then("{word} = point\\({double}, {double}, {double})")
  public void p2_point(String pointName, double x, double y, double z) {
    Tuple actual = (Tuple) ObjectCache.get(pointName);
    Tuple expected = Tuple.newPoint(x, y, z);

    assertEquals(expected, actual);
  }

  @When("{word} ← {word} * {word} * {word}")
  public void tCBA(String TName, String CName, String BName, String AName) {
    Matrix C = (Matrix) ObjectCache.get(CName);
    Matrix B = (Matrix) ObjectCache.get(BName);
    Matrix A = (Matrix) ObjectCache.get(AName);

    Matrix T = C.multiply(B).multiply(A);

    ObjectCache.set(TName, T);

  }

  @Then("T * {word} = point\\({double}, {double}, {double})")
  public void tPPoint(String pointName, double x, double y, double z) {
    Matrix transform = (Matrix) ObjectCache.get("T");
    Tuple point = (Tuple) ObjectCache.get(pointName);

    Tuple expected = Tuple.newPoint(x, y, z);
    Tuple actual = transform.multiply(point);

    assertEquals(expected, actual);
  }
}
