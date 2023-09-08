package io.github.andichrist;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class MatricesStepDefinitions {

    @Given("the following 2x2/3x3/4x4 matrix {word}:")
    public void theFollowingXXMatrix(String name, DataTable table) {
        theFollowingMatrix(name, table);
    }

    @Given("the following matrix {word}:")
    public void theFollowingMatrix(String name, DataTable table) {
        Matrix matrix = new Matrix(table.asLists(Double.class));
        ObjectCache.set(name, matrix);
    }

    @Then("{word}[{int},{int}] = {double}")
    public void checkMatrix(String name, int x, int y, double expected) {
        double actual = ((Matrix) ObjectCache.get(name)).get(x, y);

        assertEquals(expected, actual);
    }

    @Then("{word} equals {word}")
    public void aEqualsB(String matrixNameA, String matrixNameB) {
        Matrix matrixA = (Matrix) ObjectCache.get(matrixNameA);
        Matrix matrixB = (Matrix) ObjectCache.get(matrixNameB);
        assertEquals(matrixA, matrixB);
    }


    @Then("{word} not equals {word}")
    public void aNotEqualsB(String matrixNameA, String matrixNameB) {
        Matrix matrixA = (Matrix) ObjectCache.get(matrixNameA);
        Matrix matrixB = (Matrix) ObjectCache.get(matrixNameB);
        assertNotEquals(matrixA, matrixB);
    }


    @Then("{word} * {word} is the following {int}x{int} matrix:")
    public void aBIsTheFollowingXMatrix(String matrixNameA, String matrixNameB, int x, int y, DataTable table) {
        Matrix A = (Matrix) ObjectCache.get(matrixNameA);
        Matrix B = (Matrix) ObjectCache.get(matrixNameB);

        Matrix expected = new Matrix(table.asLists(Double.class));
        Matrix actual = A.multiply(B);

        assertEquals(expected.getWidth(), x);
        assertEquals(expected.getHeight(), y);
        assertEquals(expected, actual);
    }

    @Then("{word} * {word} = tuple\\({int}, {int}, {int}, {int})")
    public void aBTuple(String matrixName, String tupleName, int x, int y, int z, int w) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);
        Tuple tuple = (Tuple) ObjectCache.get(tupleName);

        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = matrix.multiply(tuple);

        assertEquals(expected, actual);

    }

    @Then("{word} * identity_matrix = {word}")
    public void aIdentity_matrixA(String matrixA, String matrixB) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixA);
        Matrix expected = (Matrix) ObjectCache.get(matrixB);

        Matrix actual = matrix.multiply(Matrix.identity());

        assertEquals(expected, actual);
    }

    @Then("identity_matrix * {word} = {word}")
    public void identity_matrixAA(String tupleNameA, String tupleNameB) {
        Tuple tuple = (Tuple) ObjectCache.get(tupleNameA);
        Tuple expected = (Tuple) ObjectCache.get(tupleNameB);

        Tuple actual = Matrix.identity().multiply(tuple);

        assertEquals(expected, actual);
    }

    @Then("transpose\\({word}) is the following matrix:")
    public void transposeAIsTheFollowingMatrix(String matrixName, DataTable table) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);
        Matrix actual = matrix.transpose();

        Matrix expected = new Matrix(table.asLists(Double.class));

        assertEquals(expected, actual);
    }


    @Given("{word} ← transpose\\(identity_matrix)")
    public void aTransposeIdentity_matrix(String matrixName) {
        Matrix matrix = Matrix.identity().transpose();
        ObjectCache.set(matrixName, matrix);
    }

    @Then("{word} = identity_matrix")
    public void aIdentity_matrix(String matrixName) {
        Matrix identity_matrix = (Matrix) ObjectCache.get(matrixName);

        assertEquals(identity_matrix, Matrix.identity());
    }

    @Then("determinant\\({word}) = {int}")
    public void determinantA(String matrixName, int actual) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        double determinant = matrix.determinant();

        assertEquals(actual, (int) determinant);
    }

    @Then("submatrix\\({word}, {int}, {int}) is the following {int}x{int} matrix:")
    public void submatrixAIsTheFollowingXMatrix(String matrixName, int matrixX, int matrixY, int subX, int subY, DataTable table) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);
        Matrix expected = new Matrix(table.asLists(Double.class));

        Matrix actual = matrix.subMatrix(matrixX, matrixY);

        assertEquals(subX, actual.getWidth());
        assertEquals(subY, actual.getHeight());

        assertEquals(expected, actual);
    }

    @And("{word} ← submatrix\\({word}, {int}, {int})")
    public void bSubmatrixA(String matrixNameB, String matrixNameA, int matrixX, int matrixY) {
        Matrix matrixA = (Matrix) ObjectCache.get(matrixNameA);
        Matrix matrixB = matrixA.subMatrix(matrixX, matrixY);
        ObjectCache.set(matrixNameB, matrixB);
    }

    @And("minor\\({word}, {int}, {int}) = {int}")
    public void minorA(String matrixName, int matrixX, int matrixY, int expectedMinor) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        double minor = matrix.minor(matrixX, matrixY);

        assertEquals(expectedMinor, minor);
    }

    @And("cofactor\\({word}, {int}, {int}) = {int}")
    public void cofactorA(String matrixName, int matrixX, int matrixY, int expectedCofactor) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        double cofactor = matrix.cofactor(matrixX, matrixY);

        assertEquals(expectedCofactor, cofactor);
    }

    @And("{word} is invertible")
    public void aIsInvertible(String matrixName) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        assertTrue(matrix.isInvertible());
    }


    @And("{word} is not invertible")
    public void aIsNotInvertible(String matrixName) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        assertFalse(matrix.isInvertible());
    }

    @And("{word} ← inverse\\({word})")
    public void bInverseA(String matrixNameB, String matrixNameA) {
        Matrix matrixA = (Matrix) ObjectCache.get(matrixNameA);

        Matrix matrixB = matrixA.inverse();

        ObjectCache.set(matrixNameB, matrixB);
    }

    @And("{word}[{int},{int}] = {int}\\/{int}")
    public void checkContent(String matrixName, int row, int col, int numerator, int denominator) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        double expected = (double) numerator / denominator;
        double actual = matrix.get(row, col);

        assertEquals(expected, actual);

    }

    @And("^([A-Za-z]+) is the following (\\d+)x(\\d+) matrix:$")
    public void bIsTheFollowingXMatrix(String matrixName, int rows, int cols, DataTable table) {
        Matrix matrixActual = (Matrix) ObjectCache.get(matrixName);
        Matrix matrixExpected = new Matrix(table.asLists(Double.class));

        assertEquals(rows, matrixActual.getWidth());
        assertEquals(cols, matrixActual.getHeight());

        Matrix.MatrixComparator comparator = new Matrix.MatrixComparator();
        assertEquals(0, comparator.compare(matrixExpected, matrixActual), "Matrices are not equal");
    }

    @And("^inverse\\(([A-Za-z]+)\\) is the following (\\d+)x(\\d+) matrix:$")
    public void inverseIsTheFollowingXMatrix(String matrixName, int rows, int cols, DataTable table) {
        Matrix matrixActual = (Matrix) ObjectCache.get(matrixName);
        Matrix matrixExpected = new Matrix(table.asLists(Double.class));

        assertEquals(rows, matrixActual.getWidth());
        assertEquals(cols, matrixActual.getHeight());

        Matrix.MatrixComparator comparator = new Matrix.MatrixComparator();
        assertEquals(0, comparator.compare(matrixExpected, matrixActual.inverse()), "Matrices are not equal");
    }

    @And("matrix {word} ← {word} * {word}")
    public void matrixMultiplication(String matrixNameC, String matrixNameA, String matrixNameB) {
        Matrix matrixA = (Matrix) ObjectCache.get(matrixNameA);
        Matrix matrixB = (Matrix) ObjectCache.get(matrixNameB);

        Matrix matrixC = matrixA.multiply(matrixB);

        ObjectCache.set(matrixNameC, matrixC);
    }

    @Then("{word} * inverse\\({word}) = {word}")
    public void cInverseBA(String matrixNameC, String matrixNameB, String matrixNameA) {
        Matrix matrixA = (Matrix) ObjectCache.get(matrixNameA);
        Matrix matrixB = (Matrix) ObjectCache.get(matrixNameB);
        Matrix matrixC = (Matrix) ObjectCache.get(matrixNameC);

        Matrix actual = matrixC.multiply(matrixB.inverse());

        Matrix.MatrixComparator comparator = new Matrix.MatrixComparator();
        assertEquals(0, comparator.compare(matrixA, actual), "Matrices are not equal");
    }
}
