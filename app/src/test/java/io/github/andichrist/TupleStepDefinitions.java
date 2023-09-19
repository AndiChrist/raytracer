package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.github.andichrist.MathOperations.DELTA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TupleStepDefinitions {

  @Given("{word} ← tuple\\({double}, {double}, {double}, {double})")
  public void aTuple(String name, double x, double y, double z, double w) {
    var tuple = new Tuple(x, y, z, w);
    ObjectCache.set(name, tuple);
  }

  @Then("{word}.red = {double}")
  @Then("{word}.x = {double}")
  public void getX(String name, double value) {
    var object = ObjectCache.get(name);
    var x = 0.0;
    if (object instanceof Tuple t) {
      x = t.x();
    } else if (object instanceof Vector v) {
      x = v.x();
    } else if (object instanceof Color c) {
      x = c.x();
    }
    assertEquals(x, value, DELTA);
  }

  @Then("{word}.green = {double}")
  @Then("{word}.y = {double}")
  public void getY(String name, double value) {
    var object = ObjectCache.get(name);
    var y = 0.0;
    if (object instanceof Tuple t) {
      y = t.y();
    } else if (object instanceof Vector v) {
      y = v.y();
    } else if (object instanceof Color c) {
      y = c.y();    }
    assertEquals(y, value, DELTA);
  }

  @Then("{word}.blue = {double}")
  @Then("{word}.z = {double}")
  public void getZ(String name, double value) {
    var object = ObjectCache.get(name);
    var z = 0.0;
    if (object instanceof Tuple t) {
      z = t.z();
    } else if (object instanceof Vector v) {
      z = v.z();
    } else if (object instanceof Color c) {
      z = c.z();
    }
    assertEquals(z, value, DELTA);
  }

  @Then("{word}.w = {double}")
  public void getW(String name, double value) {
    var object = ObjectCache.get(name);
    var w = 0.0;
    if (object instanceof Tuple t) {
      w = t.w();
    } else if (object instanceof Vector v) {
      w = 1.0;
    } else if (object instanceof Color c) {
      w = 0.0;
    }
    assertEquals(w, value, DELTA);
  }

  @And("{word} is a point")
  public void aIsAPoint(String name) {
    var tuple = ObjectCache.getTuple(name);
    assertEquals(tuple.w(), 1.0, DELTA);
  }

  @And("{word} is not a point")
  public void aIsNotAPoint(String name) {
    var tuple = ObjectCache.getTuple(name);
    assertEquals(tuple.w(), 0.0, DELTA);
  }

  @And("{word} is a vector")
  public void aIsAVector(String name) {
    var tuple = ObjectCache.getTuple(name);
    assertEquals(tuple.w(), 0.0, DELTA);
  }

  @And("{word} is not a vector")
  public void aIsNotAVector(String name) {
    var tuple = ObjectCache.getTuple(name);
    assertEquals(tuple.w(), 1.0, DELTA);
  }

  @Then("{word} + {word} = tuple\\({double}, {double}, {double}, {double})")
  public void aATuple(String name1, String name2, double x, double y, double z, double w) {
    var tuple1 = ObjectCache.getTuple(name1);
    var tuple2 = ObjectCache.getTuple(name2);

    var t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    var expected = ObjectCache.getTuple("expected");
    var actual = tuple1.add(tuple2);

    assertEquals(expected, actual);
  }

  @Given("{word} ← point\\({double}, {double}, {double})")
  public void pPoint(String name, double x, double y, double z) {
    var point = new Point(x, y, z);
    ObjectCache.set(name, point);
  }

  @Given("{word} ← vector\\({double}, {double}, {double})")
  public void vVector(String name, double x, double y, double z) {
    var vector = new Vector(x, y, z);
    ObjectCache.set(name, vector);
  }

  @Then("{word} - {word} = vector\\({double}, {double}, {double})")
  public void pPVector(String name1, String name2, double x, double y, double z) {
    var expected = new Vector(x, y, z);
    Vector actual;

    if (name1.startsWith("p") && name2.startsWith("p")) {
      var p1 = ObjectCache.getPoint(name1);
      var p2 = ObjectCache.getPoint(name2);

      Point point = p1.subtract(p2);
      actual = new Vector(point.x(), point.y(), point.z());
    } else {
      var v1 = ObjectCache.getVector(name1);
      var v2 = ObjectCache.getVector(name2);

      actual = v1.subtract(v2);
    }

    assertEquals(expected, actual);
  }

  @Then("{word} - {word} = point\\({double}, {double}, {double})")
  public void subtractVector(String name0, String name1, double x, double y, double z) {
    var expected = new Point(x, y, z);

    var point = ObjectCache.getPoint(name0);
    var vector = ObjectCache.getVector(name1);

    var actual = point.subtract(vector);

    assertEquals(expected, actual);
  }

  @Then("{word} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleNeg(String name, double x, double y, double z, double w) {
    var t = new Tuple(x, y, z, w);
    ObjectCache.set(name, t);

    var a = ObjectCache.getTuple("a");
    var b = ObjectCache.getTuple(name);

    var actual = a.add(b);

    assertTrue(actual.isZero());
    assertEquals(Tuple.ZERO, actual);
  }

  @Then("{word} * {double} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleMult(String name, double value, double x, double y, double z, double w) {
    var t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    var a = ObjectCache.getTuple(name);
    var expected = ObjectCache.getTuple("expected");

    var actual = a.multiply(value);

    assertEquals(expected, actual);
  }

  @Then("{word} ÷ {double} = tuple\\({double}, {double}, {double}, {double})")
  public void aTupleDiv(String name, double arg0, double x, double y, double z, double w) {
    var t = new Tuple(x, y, z, w);
    ObjectCache.set("expected", t);

    var a = ObjectCache.getTuple(name);
    var expected = ObjectCache.getTuple("expected");

    var actual = a.divide(arg0);

    assertEquals(expected, actual);
  }

  @Then("magnitude\\({word}) = {double}")
  public void magnitudeV(String name, double expected) {
    var v = ObjectCache.getVector(name);

    var actual = v.magnitude();

    assertEquals(expected, actual, DELTA);
  }

  @Then("magnitude\\({word}) = √{double}")
  public void magnitudeV2(String name, double value) {
    var v = ObjectCache.getVector(name);

    var expected = Math.sqrt(value);
    var actual = v.magnitude();

    assertEquals(expected, actual, DELTA);
  }

  @Then("normalize\\({word}) = vector\\({double}, {double}, {double})")
  public void normalizeVVector(String name, double x, double y, double z) {
    var expected = new Vector(x, y, z);

    var v = ObjectCache.getVector(name);
    var actual = v.normalize();

    assertEquals(expected, actual);
  }

  @Then("normalize\\({word}) = approximately vector\\({double}, {double}, {double})")
  public void normalizeVApproximatelyVector(String name, double x, double y, double z) {
    var expected = new Vector(x, y, z);

    var v = ObjectCache.getVector(name);
    var actual = v.normalize();

    assertEquals(expected.x(), actual.x(), DELTA);
    assertEquals(expected.y(), actual.y(), DELTA);
    assertEquals(expected.z(), actual.z(), DELTA);
  }

  @When("{word} ← normalize\\({word})")
  public void normNormalizeV(String norm, String name) {
    var v = ObjectCache.getVector(name);
    var actual = v.normalize();

    ObjectCache.set(norm, actual);
  }

  @Then("dot\\({word}, {word}) = {double}")
  public void dotAB(String arg0, String arg1, double expected) {
    var v1 = ObjectCache.getVector(arg0);
    var v2 = ObjectCache.getVector(arg1);

    var actual = v1.dot(v2);

    assertEquals(expected, actual, DELTA);
  }

  @Then("cross\\({word}, {word}) = vector\\({double}, {double}, {double})")
  public void crossABVector(String arg0, String arg1, double x, double y, double z) {
    var expected = new Vector(x, y, z);

    var a = ObjectCache.getVector(arg0);
    var b = ObjectCache.getVector(arg1);

    var actual = a.cross(b);

    assertEquals(expected, actual);
  }

  @Given("{word} ← color\\({double}, {double}, {double})")
  public void color(String name, double x, double y, double z) {
    var color = new Color(x, y, z);
    ObjectCache.set(name, color);
  }


  @Then("{word} + {word} = color\\({double}, {double}, {double})")
  public void cPlusColor(String name1, String name2, double red, double green, double blue) {
    var expected =  new Color(red, green, blue);

    var a = ObjectCache.getColor(name1);
    var b = ObjectCache.getColor(name2);

    var actual = a.add(b);

    assertEquals(expected, actual);
  }

  @Then("{word} - {word} = color\\({double}, {double}, {double})")
  public void cMinusColor(String name1, String name2, double red, double green, double blue) {
    var expected =  new Color(red, green, blue);

    var a = ObjectCache.getColor(name1);
    var b = ObjectCache.getColor(name2);

    var actual = a.subtract(b);

    assertEquals(expected, actual);
  }

  // avoid "undefined step reference"
  @Then("{word} * {word} = color\\({double}, {double}, {double})")
  public void cMultColor(String name1, String name2, double red, double green, double blue) {
    var expected = new Color(red, green, blue);

    var a = ObjectCache.getColor(name1);
    var b = ObjectCache.getColor(name2);
    Color actual;

    if (b != null) {
      actual = a.multiply(b);
    }
    else {
      var value = Double.parseDouble(name2);
      actual = a.multiply(value);
    }

    assertEquals(expected, actual);
  }

}
