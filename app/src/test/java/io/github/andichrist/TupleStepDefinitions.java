package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TupleStepDefinitions {

  @Given("{word} ← tuple\\({double}, {double}, {double}, {double})")
  public void aTuple(String name, double x, double y, double z, double w) {
    final Tuple tuple = new Tuple(x, y, z, w);
    ObjectCache.set(name, tuple);
  }

  @Then("{word}.red = {double}")
  @Then("{word}.x = {double}")
  public void getX(String name, double value) {
    Tuple tuple = (Tuple) ObjectCache.get(name);
    assertEquals(tuple.x(), value, 0.1);
  }

  @Then("{word}.green = {double}")
  @Then("{word}.y = {double}")
  public void getY(String name, double value) {
    Tuple tuple = (Tuple) ObjectCache.get(name);
    assertEquals(tuple.y(), value, 0.1);
  }

  @Then("{word}.blue = {double}")
  @Then("{word}.z = {double}")
  public void getZ(String name, double value) {
    Tuple tuple = (Tuple) ObjectCache.get(name);
    assertEquals(tuple.z(), value, 0.1);
  }

  @Then("{word}.w = {double}")
  public void getW(String name, double value) {
    Tuple tuple = (Tuple) ObjectCache.get(name);
    assertEquals(tuple.w(), value, 0.1);
  }

  @And("{word} is a point")
  public void aIsAPoint(String name) {
    Tuple tuple = (Tuple) ObjectCache.get(name);
    assertEquals(tuple.w(), Tuple.POINT, 0.1);
  }

  @And("{word} is not a point")
  public void aIsNotAPoint(String name) {
    Tuple tuple = (Tuple) ObjectCache.get(name);
    assertEquals(tuple.w(), Tuple.VECTOR, 0.1);
  }

  @And("{word} is a vector")
  public void aIsAVector(String name) {
    assertEquals(((Tuple) ObjectCache.get(name)).w(), 0.0, 0.1);
  }

  @And("{word} is not a vector")
  public void aIsNotAVector(String name) {
    assertEquals(((Tuple) ObjectCache.get(name)).w(), 1.0, 0.1);
  }

  @Then("{word} + {word} = tuple\\({double}, {double}, {double}, {double})")
  public void aATuple(String name1, String name2, double x, double y, double z, double w) {
    Tuple tuple1 = (Tuple) ObjectCache.get(name1);
    Tuple tuple2 = (Tuple) ObjectCache.get(name2);

    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    Tuple expected = (Tuple) ObjectCache.get("expected");
    Tuple actual = tuple1.add(tuple2);

    assertEquals(expected, actual);
  }

  @Given("{word} ← point\\({double}, {double}, {double})")
  public void pPoint(String name, double x, double y, double z) {
    Tuple point = Tuple.newPoint(x, y, z);
    ObjectCache.set(name, point);
  }

  @Given("{word} ← vector\\({double}, {double}, {double})")
  public void vVector(String name, double x, double y, double z) {
    Tuple vector = Tuple.newVector(x, y, z);
    ObjectCache.set(name, vector);
  }

  @Then("{word} - {word} = vector\\({double}, {double}, {double})")
  public void pPVector(String name1, String name2, double x, double y, double z) {
    Tuple expected = Tuple.newVector(x, y, z);

    Tuple obj1 = (Tuple) ObjectCache.get(name1);
    Tuple obj2 = (Tuple) ObjectCache.get(name2);

    // TODO correct this
    Tuple p1 = null;
    Tuple p2 = null;
    Tuple v1 = null;
    Tuple v2 = null;

    Tuple actual = null;

    if (obj1.isPoint()) {
      p1 = obj1;
    }
    else if (obj1.isVector()) {
      v1 = obj1;
    }

    if (obj2.isPoint()) {
      p2 = obj2;
    }
    else if (obj2.isVector()) {
      v2 = obj2;
    }

    if (p1 != null && p2 != null)
      actual = p1.subtract(p2);
    else if (v1 != null && v2 != null)
      actual = v1.subtract(v2);

    assertTrue(actual.isVector());
    assertEquals(expected, actual);
  }

  @Then("{word} - {word} = point\\({double}, {double}, {double})")
  public void subtractVector(String name0, String name1, double x, double y, double z) {
    Tuple expected = Tuple.newPoint(x, y, z);

    Tuple p = (Tuple) ObjectCache.get(name0);
    Tuple v = (Tuple) ObjectCache.get(name1);

    Tuple actual = p.subtract(v);

    assertEquals(expected, actual);
  }

  @Then("{word} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleNeg(String name, double x, double y, double z, double w) {
    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set(name, t);

    Tuple a = (Tuple) ObjectCache.get("a");
    Tuple b = (Tuple) ObjectCache.get(name);

    Tuple actual = a.add(b);

    assertTrue(actual.isZero());
    assertEquals(Tuple.ZERO, actual);
  }

  @Then("{word} * {double} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleMult(String name, double value, double x, double y, double z, double w) {
    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    Tuple a = (Tuple) ObjectCache.get(name);
    Tuple expected = (Tuple) ObjectCache.get("expected");

    Tuple actual = a.multiply(value);

    assertEquals(expected, actual);
  }

  @Then("{word} .\\/. {double} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleDiv(String name, double arg0, double x, double y, double z, double w) {
    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    Tuple a = (Tuple) ObjectCache.get(name);
    Tuple expected = (Tuple) ObjectCache.get("expected");

    Tuple actual = a.divide(arg0);

    assertEquals(expected, actual);
  }

  @Then("magnitude\\({word}) = {double}")
  public void magnitudeV(String name, double expected) {
    Tuple v = (Tuple) ObjectCache.get(name);

    double actual = v.magnitude();

    assertEquals(expected, actual, 1);
  }

  @Then("magnitude\\({word}) = √{double}")
  public void magnitudeV2(String name, double value) {
    Tuple v = (Tuple) ObjectCache.get(name);

    double expected = Math.sqrt(value);
    double actual = v.magnitude();

    assertEquals(expected, actual, 1);
  }

  @Then("normalize\\({word}) = vector\\({double}, {double}, {double})")
  public void normalizeVVector(String name, double x, double y, double z) {
    Tuple expected = Tuple.newVector(x, y, z);

    Tuple v = (Tuple) ObjectCache.get(name);
    Tuple actual = v.normalize();

    assertEquals(expected, actual);
  }

  @Then("normalize\\({word}) = approximately vector\\({double}, {double}, {double})")
  public void normalizeVApproximatelyVector(String name, double x, double y, double z) {
    Tuple expected = Tuple.newVector(x, y, z);

    Tuple v = (Tuple) ObjectCache.get(name);
    Tuple actual = v.normalize();

    assertEquals(expected.x(), actual.x(), 0.00001);
    assertEquals(expected.y(), actual.y(), 0.00001);
    assertEquals(expected.z(), actual.z(), 0.00001);
  }

  @When("{word} ← normalize\\({word})")
  public void normNormalizeV(String norm, String name) {
    Tuple v = (Tuple) ObjectCache.get(name);
    Tuple actual = v.normalize();

    ObjectCache.set(norm, actual);
  }

  @Then("dot\\({word}, {word}) = {double}")
  public void dotAB(String arg0, String arg1, double expected) {
    Tuple v1 = (Tuple) ObjectCache.get(arg0);
    Tuple v2 = (Tuple) ObjectCache.get(arg1);

    double actual = v1.dot(v2);

    assertEquals(expected, actual, 1);
  }

  @Then("cross\\({word}, {word}) = vector\\({double}, {double}, {double})")
  public void crossABVector(String arg0, String arg1, double x, double y, double z) {
    Tuple expected = Tuple.newVector(x, y, z);

    Tuple a = (Tuple) ObjectCache.get(arg0);
    Tuple b = (Tuple) ObjectCache.get(arg1);

    Tuple actual = a.cross(b);

    assertEquals(expected, actual);
  }

  @Given("{word} ← color\\({double}, {double}, {double})")
  public void color(String name, double x, double y, double z) {
    Tuple color = Tuple.newColor(x, y, z);
    ObjectCache.set(name, color);
  }


  @Then("{word} + {word} = color\\({double}, {double}, {double})")
  public void cPlusColor(String name1, String name2, double red, double green, double blue) {
    Tuple expected =  Tuple.newColor(red, green, blue);

    Tuple a = (Tuple) ObjectCache.get(name1);
    Tuple b = (Tuple) ObjectCache.get(name2);

    Tuple actual = a.add(b);

    assertEquals(expected, actual);
  }

  @Then("{word} - {word} = color\\({double}, {double}, {double})")
  public void cMinusColor(String name1, String name2, double red, double green, double blue) {
    Tuple expected =  Tuple.newColor(red, green, blue);

    Tuple a = (Tuple) ObjectCache.get(name1);
    Tuple b = (Tuple) ObjectCache.get(name2);

    Tuple actual = a.subtract(b);

    assertEquals(expected, actual);
  }
/*
  @Then("{word} * {double} = color\\({double}, {double}, {double})")
  public void cMultScalarColor(String name, double value, double red, double green, double blue) {
    final Color expected = (Color) new TupleFactory().create("Color", red, green, blue, 0);

    Color c = (Color) TupleHelper.get(name);

    Color actual = (Color) TupleHelper.multiply(value, c, Color.class);

    assertEquals(expected, actual);
  }
*/

  // avoid "undefined step reference"
  @Then("{word} * {word} = color\\({double}, {double}, {double})")
  public void cMultColor(String name1, String name2, double red, double green, double blue) {
    Tuple expected = Tuple.newColor(red, green, blue);

    Tuple a = (Tuple) ObjectCache.get(name1);
    Tuple b = (Tuple) ObjectCache.get(name2);
    Tuple actual;

    if (b != null) {
      actual = a.multiply(b);
    }
    else {
      double value = Double.parseDouble(name2);
      actual = a.multiply(value);
    }

    assertEquals(expected, actual);
  }

}
