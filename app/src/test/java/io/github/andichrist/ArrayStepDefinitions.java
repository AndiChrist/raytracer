package io.github.andichrist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayStepDefinitions {

  int[] a;
  int[] b;
  int[] c;

  @Given("a ← array\\({int}, {int}, {int})")
  public void aIsArray(int arg0, int arg1, int arg2) {
    a = new int[]{arg0, arg1, arg2};
  }

  @Given("b ← array\\({int}, {int}, {int})")
  public void bIsArray(int arg0, int arg1, int arg2) {
    b = new int[]{arg0, arg1, arg2};
  }

  @When("c ← a + b")
  public void cAB() {
    c = IntStream.concat(
        Arrays.stream(a),
        Arrays.stream(b)
    ).toArray();
  }

  @Then("c = array\\({int}, {int}, {int}, {int}, {int}, {int})")
  public void cArray(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    var expected = new int[]{ arg0, arg1, arg2, arg3, arg4, arg5 };

    assertArrayEquals(expected, c);
  }

}
