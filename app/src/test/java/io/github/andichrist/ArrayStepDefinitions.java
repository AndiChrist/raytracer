package io.github.andichrist;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayStepDefinitions {

  @Given("{word} ← array\\({int}, {int}, {int})")
  public void aIsArray(String name, int arg0, int arg1, int arg2) {
    ObjectCache.set(name, new int[]{arg0, arg1, arg2});
  }

  @When("{word} ← {word} + {word}")
  public void cAB(String name0, String name1, String name2) {
    var concat = ArrayHandler.concat(name1, name2);
    ObjectCache.set(name0, concat);
  }

  @Then("{word} = array\\({int}, {int}, {int}, {int}, {int}, {int})")
  public void cArray(String name, int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    var expected = new int[]{ arg0, arg1, arg2, arg3, arg4, arg5 };
    var actual = (int[]) ObjectCache.get(name);

    assertArrayEquals(expected, actual);
  }

}
