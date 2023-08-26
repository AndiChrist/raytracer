package io.github.andichrist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArrayStepdefs {

  // a ← array(1, 2, 3)
  @Given("a ← array\\({int}, {int}, {int})")
  public void aIsArray(int arg0, int arg1, int arg2) {
    ArrayCache.set("a", new int[]{arg0, arg1, arg2});
  }

  @And("b ← array\\({int}, {int}, {int})")
  public void bArray(int arg0, int arg1, int arg2) {
    ArrayCache.set("b", new int[]{arg0, arg1, arg2});
  }

  @When("{word} ← {word} + {word}")
  public void cAB(String name0, String name1, String name2) {
    final int[] concat = ArrayHandler.concat(name1, name2);
    ArrayCache.set(name0, concat);
  }

  @Then("{word} = array\\({int}, {int}, {int}, {int}, {int}, {int})")
  public void cArray(String name, int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    int[] expected = new int[]{ arg0, arg1, arg2, arg3, arg4, arg5 };

    assertArrayEquals(expected, ArrayCache.get(name));
  }

}
