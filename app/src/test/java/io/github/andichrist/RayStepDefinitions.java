package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.andichrist.shapes.Sphere;

import java.util.ArrayList;

import static io.github.andichrist.Ray.intersect;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RayStepDefinitions {

  @When("{word} ← ray\\({word}, {word})")
  public void r_ray_origin_direction(String rayName, String originName, String directionName) {
    var origin = ObjectCache.getPoint(originName);
    var direction = ObjectCache.getVector(directionName);

    var ray = new Ray(origin, direction);

    ObjectCache.set(rayName, ray);
  }

  @Then("{word}.origin = {word}")
  public void r_origin_origin(String rayName, String originName) {
    var r = ObjectCache.getRay(rayName);
    var origin = ObjectCache.getPoint(originName);

    assertEquals(r.origin(), origin);
  }

  @Then("{word}.direction = {word}")
  public void r_direction_direction(String rayName, String directionName) {
    var r = ObjectCache.getRay(rayName);
    var direction = ObjectCache.getVector(directionName);

    assertEquals(r.direction(), direction);
  }

  @Given("{word} ← ray\\(point\\({double}, {double}, {double}), vector\\({double}, {double}, {double}))")
  public void rRayPointVector(String rayName, double px, double py, double pz, double vx, double vy, double vz) {
    var point = new Point(px, py, pz);
    var vector = new Vector(vx, vy, vz);

    var r = new Ray(point, vector);

    ObjectCache.set(rayName, r);
  }


  @Then("position\\({word}, {double}) = point\\({double}, {double}, {double})")
  public void positionRPoint(String rayName, double pos, double px, double py, double pz) {
    var point = new Point(px, py, pz);
    var r = ObjectCache.getRay(rayName);

    assertEquals(r.position(pos), point);
  }

  @And("{word} ← sphere\\()")
  public void sSphere(String sphereName) {
    var s = new Sphere();

    ObjectCache.set(sphereName, s);
  }

  @When("{word} ← intersect\\({word}, {word})")
  public void xsIntersectSR(String intersectsName, String sphereName, String rayName) {
    var s = ObjectCache.getSphere(sphereName);
    var r = ObjectCache.getRay(rayName);

    var xs = intersect(s, r);

    ObjectCache.set(intersectsName, xs);
  }

  @Then("{word}.count = {int}")
  public void xsCount(String intersectsName, int count) {
    var xs = ObjectCache.getArrayList(intersectsName);

    assertEquals(xs.size(), count);
  }

  @And("{word} ← intersection\\({double}, {word})")
  public void iIntersectionS(String iName, double t, String sphereName) {
    var s = ObjectCache.getSphere(sphereName);
    var i = new Intersection(t, s);

    ObjectCache.set(iName, i);
  }

  @Then("i.t = {double}")
  public void iTdouble(double t) {
    var i = ObjectCache.getIntersection("i");

    assertEquals(i.t(), t);
  }

  @Then("i.object = {word}")
  public void iTSphere(String sphereName) {
    var i = ObjectCache.getIntersection("i");
    var s = ObjectCache.getSphere(sphereName);

    assertEquals(i.object(), s);
  }

  @Then("xs[{int}].t = {double}")
  public void xsT(int index, double t) {
    var xs = (ArrayList<Intersection>) ObjectCache.getArrayList("xs");

    assertEquals(xs.get(index).t(), t);
  }

  @And("xs[{int}].object = {word}")
  public void iObjectS(int index, String sphereName) {
    var xs = (ArrayList<Intersection>) ObjectCache.getArrayList("xs");
    var s = ObjectCache.getSphere(sphereName);

    assertEquals(xs.get(index).object(), s);
  }

  @And("{word} ← intersections\\({word}, {word})")
  public void xsIntersectionsII(String iArrayName, String i1Name, String i2Name) {
    var i1 = ObjectCache.getIntersection(i1Name);
    var i2 = ObjectCache.getIntersection(i2Name);

    var xs = Ray.intersections(new ArrayList<>(asList(i1, i2)));

    ObjectCache.set(iArrayName, xs);
  }

  @And("{word} ← intersections\\({word}, {word}, {word}, {word})")
  public void xsIntersectionsIIII(String iArrayName, String i1Name, String i2Name, String i3Name, String i4Name) {
    var i1 = ObjectCache.getIntersection(i1Name);
    var i2 = ObjectCache.getIntersection(i2Name);
    var i3 = ObjectCache.getIntersection(i3Name);
    var i4 = ObjectCache.getIntersection(i4Name);

    var xs = Ray.intersections(new ArrayList<>(asList(i1, i2, i3, i4)));

    ObjectCache.set(iArrayName, xs);
  }

  @When("i ← hit\\({word})")
  public void iHitXs(String iArrayName) {
    var xs = (ArrayList<Intersection>) ObjectCache.getArrayList(iArrayName);

    var i = (xs.isEmpty()) ? null : Ray.hit(xs);

    ObjectCache.set("i", i);
  }

  @Then("i = i{int}")
  public void iInt(int index) {
    var i = ObjectCache.getIntersection("i");
    var ix = ObjectCache.getIntersection("i" + index);

    assertEquals(ix, i);
  }

  @Then("{word} is nothing")
  public void iIsNothing(String intersectionName) {
    var i = ObjectCache.getIntersection(intersectionName);

    assertNull(i);
  }

  @Then("xs.t = {double}")
  public void iT(double value) {
    var i = ObjectCache.getIntersection("xs");

    assertEquals(i.t(), value);
  }

  @And("xs.object = {word}")
  public void iObjectS(String sphereName) {
    var s = ObjectCache.getSphere(sphereName);
    var i = ObjectCache.getIntersection("xs");

    assertEquals(i.object(), s);
  }
  
  @When("{word} ← transform\\({word}, {word})")
  public void rTransformRM(String newRayName, String rayName, String matrixName) {
    var r = ObjectCache.getRay(rayName);
    var m = ObjectCache.getMatrix(matrixName);
    var r2 = Ray.transform(r, m);

    ObjectCache.set(newRayName, r2);
  }

  @And("{word}.direction = vector\\({double}, {double}, {double})")
  public void rDirectionVector(String r2Name, double x, double y, double z) {
    var v = new Vector(x, y, z);
    var r2 = ObjectCache.getRay(r2Name);

    assertEquals(r2.direction(), v);
  }
}
