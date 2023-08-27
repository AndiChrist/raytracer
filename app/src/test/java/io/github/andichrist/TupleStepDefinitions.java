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
    final NTuple tuple = new TupleFactory().create("Tuple", x, y, z, w);
    ObjectCache.set(name, tuple);
  }

  @Then("{word}.{word} = {double}")
  public void get(String name, String key, double value) {
    assertEquals(((NTuple) ObjectCache.get(name)).get(key), value, 0.1);
  }

  @And("{word} is a point")
  public void aIsAPoint(String name) {
    assertEquals(((Tuple) ObjectCache.get(name)).getW(), 1.0, 0.1);
  }

  @And("{word} is not a point")
  public void aIsNotAPoint(String name) {
    assertEquals(((Tuple) ObjectCache.get(name)).getW(), 0.0, 0.1);
  }

  @And("{word} is a vector")
  public void aIsAVector(String name) {
    assertEquals(((Tuple) ObjectCache.get(name)).getW(), 0.0, 0.1);
  }

  @And("{word} is not a vector")
  public void aIsNotAVector(String name) {
    assertEquals(((Tuple) ObjectCache.get(name)).getW(), 1.0, 0.1);
  }

  @Then("{word} + {word} = tuple\\({double}, {double}, {double}, {double})")
  public void aATuple(String name1, String name2, double x, double y, double z, double w) {
    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    Tuple expected = (Tuple) ObjectCache.get("expected");
    Tuple actual = (Tuple) TupleHelper.add(name1, name2, Tuple.class);

    assertEquals(expected, actual);
  }

  @Given("{word} ← point\\({double}, {double}, {double})")
  public void pPoint(String name, double x, double y, double z) {
    final NTuple tuple = new TupleFactory().create("Point", x, y, z, Point.POINT);
    ObjectCache.set(name, tuple);
  }

  @Given("{word} ← vector\\({double}, {double}, {double})")
  public void vVector(String name, double x, double y, double z) {
    final NTuple tuple = new TupleFactory().create("Vector", x, y, z, Vector.VECTOR);
    ObjectCache.set(name, tuple);
  }

  @Then("{word} - {word} = vector\\({double}, {double}, {double})")
  public void pPVector(String name1, String name2, double x, double y, double z) {
    Vector expected = new Vector(x, y, z);

    NTuple p1 = (NTuple) ObjectCache.get(name1);
    NTuple p2 = (NTuple) ObjectCache.get(name2);

    Vector actual = (Vector) TupleHelper.subtract(p1, p2, Vector.class);

    assertTrue(expected.isVector());
    assertTrue(actual.isVector());

    assertEquals(expected, actual);
  }

  @Then("{word} - {word} = point\\({double}, {double}, {double})")
  public void subtractVector(String name0, String name1, double x, double y, double z) {
    Point expected = new Point(x, y, z);

    Point p = (Point) ObjectCache.get(name0);
    Vector v = (Vector) ObjectCache.get(name1);

    Point actual = (Point) TupleHelper.subtract(p, v, Point.class);

    assertTrue(p.isPoint());
    assertTrue(v.isVector());

    assertEquals(expected, actual);
  }

  @Then("{word} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleNeg(String name, double x, double y, double z, double w) {
    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set(name, t);

    Tuple a = (Tuple) ObjectCache.get("a");
    Tuple b = (Tuple) ObjectCache.get(name);

    Tuple actual = (Tuple) TupleHelper.add(a, b, Tuple.class);

    assertTrue(actual.isZero());
    assertEquals(Tuple.ZERO, actual);
  }

  @Then("{word} * {double} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleMult(String name, double arg, double x, double y, double z, double w) {
    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    Tuple a = (Tuple) ObjectCache.get(name);
    Tuple expected = (Tuple) ObjectCache.get("expected");

    Tuple actual = (Tuple) TupleHelper.multiply(arg, a, Tuple.class);

    assertEquals(expected, actual);
  }

  @Then("{word} .\\/. {double} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleDiv(String name, double arg0, double x, double y, double z, double w) {
    Tuple t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    Tuple a = (Tuple) ObjectCache.get(name);
    Tuple expected = (Tuple) ObjectCache.get("expected");

    Tuple actual = (Tuple) TupleHelper.divide(arg0, a);

    assertEquals(expected, actual);
  }

  @Then("magnitude\\({word}) = {double}")
  public void magnitudeV(String name, double expected) {
    NTuple v = (NTuple) ObjectCache.get(name);

    double actual = TupleHelper.magnitude(v);

    assertEquals(expected, actual, 1);
  }

  @Then("magnitude\\({word}) = √{double}")
  public void magnitudeV2(String name, double value) {
    Vector v = (Vector) ObjectCache.get(name);

    double expected = Math.sqrt(value);
    double actual = TupleHelper.magnitude(v);

    assertEquals(expected, actual, 1);
  }

  @Then("normalize\\({word}) = vector\\({double}, {double}, {double})")
  public void normalizeVVector(String name, double x, double y, double z) {
    Vector expected = new Vector(x, y, z);

    NTuple v = (NTuple) ObjectCache.get(name);
    Vector actual = (Vector) TupleHelper.normalize(v, Vector.class);

    assertEquals(expected, actual);
  }

  @Then("normalize\\({word}) = approximately vector\\({double}, {double}, {double})")
  public void normalizeVApproximatelyVector(String name, double x, double y, double z) {
    Vector expected = new Vector(x, y, z);

    NTuple v = (NTuple) ObjectCache.get(name);
    Vector actual = (Vector) TupleHelper.normalize(v, Vector.class);

    assertEquals(expected.getX(), actual.getX(), 0.00001);
    assertEquals(expected.getY(), actual.getY(), 0.00001);
    assertEquals(expected.getZ(), actual.getZ(), 0.00001);
    assertEquals(expected.getW(), actual.getW(), 0.00001);
  }

  @When("{word} ← normalize\\({word})")
  public void normNormalizeV(String norm, String name) {
    NTuple v = (NTuple) ObjectCache.get(name);
    Vector actual = (Vector) TupleHelper.normalize(v, Vector.class);

    ObjectCache.set(norm, actual);
  }

  @Then("dot\\({word}, {word}) = {double}")
  public void dotAB(String arg0, String arg1, double expected) {
    NTuple v1 = (NTuple) ObjectCache.get(arg0);
    NTuple v2 = (NTuple) ObjectCache.get(arg1);

    double actual = TupleHelper.dot(v1, v2);

    assertEquals(expected, actual, 1);
  }

  @Then("cross\\({word}, {word}) = vector\\({double}, {double}, {double})")
  public void crossABVector(String arg0, String arg1, double x, double y, double z) {
    Vector expected = new Vector(x, y, z);

    Vector a = (Vector) ObjectCache.get(arg0);
    Vector b = (Vector) ObjectCache.get(arg1);

    NTuple actual = TupleHelper.cross(a, b);

    assertEquals(expected, actual);
  }

  @Given("{word} ← color\\({double}, {double}, {double})")
  public void color(String name, double x, double y, double z) {
    final NTuple tuple = new TupleFactory().create("Color", x, y, z, 0);
    ObjectCache.set(name, tuple);
  }


  @Then("{word} + {word} = color\\({double}, {double}, {double})")
  public void cPlusColor(String name1, String name2, double red, double green, double blue) {
    final Color expected = (Color) new TupleFactory().create("Color", red, green, blue, 0);

    Color a = (Color) ObjectCache.get(name1);
    Color b = (Color) ObjectCache.get(name2);

    Color actual = (Color) TupleHelper.add(a, b, Color.class);

    assertEquals(expected, actual);
  }

  @Then("{word} - {word} = color\\({double}, {double}, {double})")
  public void cMinusColor(String name1, String name2, double red, double green, double blue) {
    final Color expected = (Color) new TupleFactory().create("Color", red, green, blue, 0);

    Color a = (Color) ObjectCache.get(name1);
    Color b = (Color) ObjectCache.get(name2);

    Color actual = (Color) TupleHelper.subtract(a, b, Color.class);

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
    final Color expected = (Color) new TupleFactory().create("Color", red, green, blue, 0);

    Color a = (Color) ObjectCache.get(name1);
    Color b = (Color) ObjectCache.get(name2);
    if (b != null) {
      Color actual = (Color) TupleHelper.multiply(a, b, Color.class);
      assertEquals(expected, actual);
    }
    else {
      double value = Double.parseDouble(name2);
      Color actual = (Color) TupleHelper.multiply(value, a, Color.class);
      assertEquals(expected, actual);
    }
  }

}
