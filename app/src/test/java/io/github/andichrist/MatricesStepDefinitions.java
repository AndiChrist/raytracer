package io.github.andichrist;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static io.github.andichrist.Matrix.identity;
import static org.junit.jupiter.api.Assertions.*;

public class MatricesStepDefinitions {

    @Given("the following 2x2/3x3/4x4 matrix {word}:")
    public void theFollowingXXMatrix(String name, DataTable table) {
        theFollowingMatrix(name, table);
    }

    @Given("the following matrix {word}:")
    public void theFollowingMatrix(String name, DataTable table) {
        var matrix = new Matrix(table.asLists(Double.class));
        ObjectCache.set(name, matrix);
    }

    @Then("{word}[{int},{int}] = {double}")
    public void checkMatrix(String name, int x, int y, double expected) {
        var actual = ((Matrix) ObjectCache.get(name)).get(x, y);

        assertEquals(expected, actual);
    }

    @Then("{word} equals {word}")
    public void aEqualsB(String matrixNameA, String matrixNameB) {
        var expected = (Matrix) ObjectCache.get(matrixNameA);
        var actual = (Matrix) ObjectCache.get(matrixNameB);

        assertEquals(expected, actual);
    }


    @Then("{word} not equals {word}")
    public void aNotEqualsB(String matrixNameA, String matrixNameB) {
        var expected = (Matrix) ObjectCache.get(matrixNameA);
        var actual = (Matrix) ObjectCache.get(matrixNameB);

        assertNotEquals(expected, actual);
    }


    @Then("{word} * {word} is the following {int}x{int} matrix:")
    public void aBIsTheFollowingXMatrix(String matrixNameA, String matrixNameB, int x, int y, DataTable table) {
        var A = (Matrix) ObjectCache.get(matrixNameA);
        var B = (Matrix) ObjectCache.get(matrixNameB);

        var expected = new Matrix(table.asLists(Double.class));
        var actual = A.multiply(B);

        assertEquals(expected.getWidth(), x);
        assertEquals(expected.getHeight(), y);
        assertEquals(expected, actual);
    }

    @Then("{word} * {word} = tuple\\({int}, {int}, {int}, {int})")
    public void aBTuple(String matrixName, String tupleName, int x, int y, int z, int w) {
        var matrix = (Matrix) ObjectCache.get(matrixName);
        var tuple = (Tuple) ObjectCache.get(tupleName);

        var expected = new Tuple(x, y, z, w);
        var actual = matrix.multiply(tuple);

        assertEquals(expected, actual);

    }

    @Then("{word} * identity_matrix = {word}")
    public void aIdentity_matrixA(String matrixA, String matrixB) {
        var matrix = (Matrix) ObjectCache.get(matrixA);
        var expected = (Matrix) ObjectCache.get(matrixB);

        var actual = matrix.multiply(identity());

        assertEquals(expected, actual);
    }

    @Then("identity_matrix * {word} = {word}")
    public void identity_matrixAA(String tupleNameA, String tupleNameB) {
        var tuple = (Tuple) ObjectCache.get(tupleNameA);
        var expected = (Tuple) ObjectCache.get(tupleNameB);

        var actual = identity().multiply(tuple);

        assertEquals(expected, actual);
    }

    @Then("transpose\\({word}) is the following matrix:")
    public void transposeAIsTheFollowingMatrix(String matrixName, DataTable table) {
        var matrix = (Matrix) ObjectCache.get(matrixName);
        var actual = matrix.transpose();

        var expected = new Matrix(table.asLists(Double.class));

        assertEquals(expected, actual);
    }


    @Given("{word} ← transpose\\(identity_matrix)")
    public void aTransposeIdentity_matrix(String matrixName) {
        var matrix = identity().transpose();
        ObjectCache.set(matrixName, matrix);
    }

    @Then("{word} = identity_matrix")
    public void aIdentity_matrix(String matrixName) {
        var expected = (Matrix) ObjectCache.get(matrixName);

        assertEquals(expected, identity());
    }

    @Then("determinant\\({word}) = {int}")
    public void determinantA(String matrixName, int actual) {
        var matrix = (Matrix) ObjectCache.get(matrixName);

        var determinant = matrix.determinant();

        assertEquals((int) determinant, actual);
    }

    @Then("submatrix\\({word}, {int}, {int}) is the following {int}x{int} matrix:")
    public void submatrixAIsTheFollowingXMatrix(String matrixName, int matrixX, int matrixY, int subX, int subY, DataTable table) {
        var matrix = (Matrix) ObjectCache.get(matrixName);
        var expected = new Matrix(table.asLists(Double.class));

        var actual = matrix.subMatrix(matrixX, matrixY);

        assertEquals(subX, actual.getWidth());
        assertEquals(subY, actual.getHeight());

        assertEquals(expected, actual);
    }

    @And("{word} ← submatrix\\({word}, {int}, {int})")
    public void bSubmatrixA(String matrixNameB, String matrixNameA, int matrixX, int matrixY) {
        var matrixA = (Matrix) ObjectCache.get(matrixNameA);
        var matrixB = matrixA.subMatrix(matrixX, matrixY);
        ObjectCache.set(matrixNameB, matrixB);
    }

    @And("minor\\({word}, {int}, {int}) = {int}")
    public void minorA(String matrixName, int matrixX, int matrixY, int expected) {
        var matrix = (Matrix) ObjectCache.get(matrixName);

        var actual = matrix.minor(matrixX, matrixY);

        assertEquals(expected, actual);
    }

    @And("cofactor\\({word}, {int}, {int}) = {int}")
    public void cofactorA(String matrixName, int matrixX, int matrixY, int expected) {
        var matrix = (Matrix) ObjectCache.get(matrixName);

        var actual = matrix.cofactor(matrixX, matrixY);

        assertEquals(expected, actual);
    }

    @And("{word} is invertible")
    public void aIsInvertible(String matrixName) {
        var matrix = (Matrix) ObjectCache.get(matrixName);

        assertTrue(matrix.isInvertible());
    }


    @And("{word} is not invertible")
    public void aIsNotInvertible(String matrixName) {
        var matrix = (Matrix) ObjectCache.get(matrixName);

        assertFalse(matrix.isInvertible());
    }

    @And("{word} ← inverse\\({word})")
    public void bInverseA(String matrixNameB, String matrixNameA) {
        var matrixA = (Matrix) ObjectCache.get(matrixNameA);

        var matrixB = matrixA.inverse();

        ObjectCache.set(matrixNameB, matrixB);
    }

    @And("{word}[{int},{int}] = {int}\\/{int}")
    public void checkContent(String matrixName, int row, int col, int numerator, int denominator) {
        var matrix = (Matrix) ObjectCache.get(matrixName);

        var expected = (double) numerator / denominator;
        var actual = matrix.get(row, col);

        assertEquals(expected, actual);

    }

    @And("^([A-Za-z]+) is the following (\\d+)x(\\d+) matrix:$")
    public void bIsTheFollowingXMatrix(String matrixName, int rows, int cols, DataTable table) {
        var matrixActual = (Matrix) ObjectCache.get(matrixName);
        var matrixExpected = new Matrix(table.asLists(Double.class));

        assertEquals(rows, matrixActual.getWidth());
        assertEquals(cols, matrixActual.getHeight());

        Matrix.MatrixComparator comparator = new Matrix.MatrixComparator();
        assertEquals(0, comparator.compare(matrixExpected, matrixActual), "Matrices are not equal");
    }

    @And("^inverse\\(([A-Za-z]+)\\) is the following (\\d+)x(\\d+) matrix:$")
    public void inverseIsTheFollowingXMatrix(String matrixName, int rows, int cols, DataTable table) {
        var matrixActual = (Matrix) ObjectCache.get(matrixName);
        var matrixExpected = new Matrix(table.asLists(Double.class));

        assertEquals(rows, matrixActual.getWidth());
        assertEquals(cols, matrixActual.getHeight());

        Matrix.MatrixComparator comparator = new Matrix.MatrixComparator();
        assertEquals(0, comparator.compare(matrixExpected, matrixActual.inverse()), "Matrices are not equal");
    }

    @And("matrix {word} ← {word} * {word}")
    public void matrixMultiplication(String matrixNameC, String matrixNameA, String matrixNameB) {
        var matrixA = (Matrix) ObjectCache.get(matrixNameA);
        var matrixB = (Matrix) ObjectCache.get(matrixNameB);

        var matrixC = matrixA.multiply(matrixB);

        ObjectCache.set(matrixNameC, matrixC);
    }

    @Then("{word} * inverse\\({word}) = {word}")
    public void cInverseBA(String matrixNameC, String matrixNameB, String matrixNameA) {
        var matrixA = (Matrix) ObjectCache.get(matrixNameA);
        var matrixB = (Matrix) ObjectCache.get(matrixNameB);
        var matrixC = (Matrix) ObjectCache.get(matrixNameC);

        var actual = matrixC.multiply(matrixB.inverse());

        Matrix.MatrixComparator comparator = new Matrix.MatrixComparator();
        assertEquals(0, comparator.compare(matrixA, actual), "Matrices are not equal");
    }
}
