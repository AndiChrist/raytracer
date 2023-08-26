package io.github.andichrist;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MatricesStepdefs {

    @Given("the following {int}x{int} matrix {word}:")
    public void theFollowingMatrix(int width, int height, String name, DataTable table) {
        Matrix matrix = new Matrix(width, height, table.asLists(Double.class));
        ObjectCache.set(name, matrix);
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
        Matrix actual = Matrix.multiply(A, B);

        assertEquals(expected.getWidth(), x);
        assertEquals(expected.getHeight(), y);
        assertEquals(expected, actual);
    }

    @Then("{word} * {word} = tuple\\({int}, {int}, {int}, {int})")
    public void aBTuple(String matrixName, String tupleName, int x, int y, int z, int w) {
        Tuple tuple = (Tuple) ObjectCache.get(tupleName);
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        //NTuple expected = new Tuple(x, y, z, w);
        Tuple expected = (Tuple) new TupleFactory().create("Tuple", x, y, z, w);
        Tuple actual = (Tuple) Matrix.multiply(matrix, tuple);

        assertEquals(expected, actual);

    }

    @Then("{word} * identity_matrix = {word}")
    public void aIdentity_matrixA(String matrixA, String matrixB) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixA);
        Matrix expected = (Matrix) ObjectCache.get(matrixB);
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        double[][] iMatrix = new double[width][height];
        for (int i = 0; i < width; i++) {
            iMatrix[i][i] = 1;
        }

        Matrix identity_matrix = new Matrix(iMatrix);

        Matrix actual = Matrix.multiply(matrix, identity_matrix);

        assertEquals(expected, actual);
    }

    @Then("identity_matrix * {word} = {word}")
    public void identity_matrixAA(String tupleNameA, String tupleNameB) {
        Tuple tuple = (Tuple) ObjectCache.get(tupleNameA);
        Tuple expected = (Tuple) ObjectCache.get(tupleNameB);

        int width = 4;
        int height = 4;

        double[][] iMatrix = new double[width][height];
        for (int i = 0; i < width; i++) {
            iMatrix[i][i] = 1;
        }

        Matrix identity_matrix = new Matrix(iMatrix);
        Tuple actual = (Tuple) Matrix.multiply(identity_matrix, tuple);

        assertEquals(expected, actual);
    }

    @Then("transpose\\({word}) is the following matrix:")
    public void transposeAIsTheFollowingMatrix(String matrixName, DataTable table) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);
        Matrix actual = Matrix.transpose(matrix);

        Matrix expected = new Matrix(table.asLists(Double.class));

        assertEquals(expected, actual);
    }


    @Given("{word} ← transpose\\(identity_matrix)")
    public void aTransposeIdentity_matrix(String matrixName) {

        double[][] iMatrix = new double[4][4];
        for (int i = 0; i < 4; i++) {
            iMatrix[i][i] = 1;
        }

        Matrix identity_matrix = new Matrix(iMatrix);
        ObjectCache.set(matrixName, Matrix.transpose(identity_matrix));
    }

    @Then("{word} = identity_matrix")
    public void aIdentity_matrix(String matrixName) {
        Matrix identity_matrix = (Matrix) ObjectCache.get(matrixName);

        double[][] iMatrix = new double[4][4];
        for (int i = 0; i < 4; i++) {
            iMatrix[i][i] = 1;
        }

        Matrix actual = new Matrix(iMatrix);

        assertEquals(actual, identity_matrix);
    }

    @Then("determinant\\({word}) = {int}")
    public void determinantA(String matrixName, int actual) {
        Matrix matrix = (Matrix) ObjectCache.get(matrixName);

        double determinant = matrix.determinant();

        assertEquals(actual, (int) determinant);
    }
}
