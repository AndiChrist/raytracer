package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.github.andichrist.Ray.intersect;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayStepDefinitions {

  @When("{word} ← ray\\({word}, {word})")
  public void r_ray_origin_direction(String rayName, String originName, String directionName) {
    var origin = (Tuple) ObjectCache.get(originName);
    var direction = (Tuple) ObjectCache.get(directionName);

    var r = new Ray(origin, direction);

    ObjectCache.set(rayName, r);
  }

  @Then("{word}.origin = {word}")
  public void r_origin_origin(String rayName, String originName) {
    var r = (Ray) ObjectCache.get(rayName);
    var origin = (Tuple) ObjectCache.get(originName);

    assertEquals(r.origin(), origin);
  }

  @Then("{word}.direction = {word}")
  public void r_direction_direction(String rayName, String directionName) {
    var r = (Ray) ObjectCache.get(rayName);
    var direction = (Tuple) ObjectCache.get(directionName);

    assertEquals(r.direction(), direction);
  }

  @Given("{word} ← ray\\(point\\({double}, {double}, {double}), vector\\({double}, {double}, {double}))")
  public void rRayPointVector(String rayName, double px, double py, double pz, double vx, double vy, double vz) {
    var point = Tuple.point(px, py, pz);
    var vector = Tuple.vector(vx, vy, vz);

    var r = new Ray(point, vector);

    ObjectCache.set(rayName, r);
  }


  @Then("position\\({word}, {double}) = point\\({double}, {double}, {double})")
  public void positionRPoint(String rayName, double pos, double px, double py, double pz) {
    var point = Tuple.point(px, py, pz);
    var r = (Ray) ObjectCache.get(rayName);

    assertEquals(r.position(pos), point);
  }

  @And("s ← sphere\\()")
  public void sSphere() {
    var s = Ray.sphere();

    ObjectCache.set("sphere" + s.id(), s);
  }

  @When("{word} ← intersect\\({word}, {word})")
  public void xsIntersectSR(String intersectsName, String sphereName, String rayName) {
    var s = (Sphere) ObjectCache.get(sphereName + 1);
    var r = (Ray) ObjectCache.get(rayName);

    var xs = intersect(s, r);

    ObjectCache.set(intersectsName, xs);
  }

  @Then("{word}.count = {int}")
  public void xsCount(String intersectsName, int count) {
    var xs = (double[]) ObjectCache.get(intersectsName);

    assertEquals(xs.length, count);
  }

  @And("{word}[{int}] = {double}")
  public void xs(String intersectsName, int index, double value) {
    var xs = (double[]) ObjectCache.get(intersectsName);

    assertEquals(xs[index], value);
  }
}
