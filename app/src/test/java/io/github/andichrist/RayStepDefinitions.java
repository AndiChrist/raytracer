package io.github.andichrist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
}
