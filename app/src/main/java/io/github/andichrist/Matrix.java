package io.github.andichrist;

import java.util.*;
import java.util.List;

public class Matrix {
    double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(List<List<Double>> testData) {
        this(testData.size(), testData.get(0).size(), testData);
    }

    public Matrix(int numRows, int numCols, List<List<Double>> testData) {
        double[][] rows = new double[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            List<Double> rowDoubles = testData.get(row);
            for (int col = 0; col < numCols; col++) {
                rows[row][col] = rowDoubles.get(col);
            }
        }

        matrix = rows;
    }

    public static double minor(Matrix matrix, int row, int column) {
        Matrix subMatrix = subMatrix(matrix, row, column);
        return determinant(subMatrix.getMatrix(), subMatrix.getWidth());
    }

    public static double cofactor(Matrix matrix, int row, int column) {
        Matrix subMatrix = subMatrix(matrix, row, column);
        return determinant(subMatrix.getMatrix(), subMatrix.getWidth())
            * ((row + column) % 2 == 0 ? 1 : -1);
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix transpose() {
        return transpose(this);
    }

    public static Matrix transpose(Matrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        double[][] result = new double[width][height];

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                result[col][row] = matrix.get(row, col);
            }
        }

        return new Matrix(result);
    }

    public double get(int x, int y) {
        return this.matrix[x][y];
    }

    public int getWidth() {
        return matrix.length;
    }

    public int getHeight() {
        return matrix[0].length;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        double[][] result = new double[a.getWidth()][a.getHeight()];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                double cell = 0;
                for (int i = 0; i < result.length; i++) {
                    cell += a.get(row, i) * b.get(i, col);
                }
                result[row][col] = cell;
            }
        }

        return new Matrix(result);
    }

    public static NTuple multiply(Matrix a, NTuple b) {
        double[] result = new double[a.getWidth()];
        for (int row = 0; row < result.length; row++) {
            double cell = 0;
            for (int i = 0; i < result.length; i++) {
                cell += a.get(row, i) * b.get(i);
            }
            result[row] = cell;
        }

        return new TupleFactory().create("Tuple", result[0], result[1], result[2], result[3]);
    }

    public double determinant() {
        return determinant(getMatrix(), getWidth());
    }

    // Laplace-Entwicklung
    public static double determinant(double[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }

        double determinant = 0;
        int sign = 1;

        for (int i = 0; i < n; i++) {
            double[][] subMatrix = new double[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0, col = 0; k < n; k++) {
                    if (k != i) {
                        subMatrix[j - 1][col] = matrix[j][k];
                        col++;
                    }
                }
            }
            determinant += sign * matrix[0][i] * determinant(subMatrix, n - 1);
            sign *= -1;
        }

        return determinant;
    }

    public static Matrix subMatrix(Matrix matrix, int row, int column) {
        double[][] result = new double[matrix.getWidth() - 1][matrix.getHeight() - 1];

        int ri = 0, rj = 0;
        for (int i = 0; i < matrix.getWidth(); i++) {
            if (i == row) continue;
            for (int j = 0; j < matrix.getHeight(); j++) {
                if (j == column) continue;
                result[ri][rj++] = matrix.get(i, j);
            }
            rj = 0;
            ri++;
        }

        return new Matrix(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        Arrays.stream(matrix).forEach((row) -> {
            result.append("[");
            Arrays.stream(row).forEach((el) -> result.append(" ").append(el).append(" "));
            result.append("]").append("\n");
        });

        return result.toString();
    }

    public boolean isInvertible() {
        return determinant() != 0;
    }

    public Matrix inverse() {
        return inverse(this);
    }

    private static Matrix inverse(Matrix matrix) {
        if (!matrix.isInvertible())
            return null;

        double[][] matrix2 = new double[matrix.getWidth()][matrix.getHeight()];

        for (int row = 0; row < matrix.getWidth(); row++) {
            for (int column = 0; column < matrix.getHeight(); column++) {
                double c = cofactor(matrix, row, column);
                matrix2[column][row] =c / determinant(matrix.getMatrix(), matrix.getWidth());
            }
        }

        return new Matrix(matrix2);
    }

    public static class MatrixComparator implements Comparator<Matrix> {
        @Override
        public int compare(Matrix expected, Matrix actual) {
            if (compareMatrices(expected, actual)) {
                return 0;
            }
            return 1;
        }

        private static final double epsilon = 1e-5; // Toleranz für den Vergleich

        private static boolean compareMatrices(Matrix expected, Matrix actual) {
            double[][] expectedData = expected.matrix;
            double[][] actualData = actual.matrix;
            if (expectedData.length != actualData.length || expectedData[0].length != actualData[0].length) {
                return false; // Matrizengrößen stimmen nicht überein
            }

            for (int i = 0; i < expectedData.length; i++) {
                for (int j = 0; j < expectedData[i].length; j++) {
                    if (Math.abs(expectedData[i][j] - actualData[i][j]) > MatrixComparator.epsilon) {
                        return false; // Elemente unterscheiden sich um mehr als epsilon
                    }
                }
            }

            return true; // Matrizen sind ähnlich genug
        }
    }

}
