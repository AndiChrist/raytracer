package io.github.andichrist;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public record Matrix(double[][] matrix) {

    public Matrix(List<List<Double>> testData) {
        this(transformTestData(testData));
    }

    private static double[][] transformTestData(List<List<Double>> testData) {
        int numRows = testData.size();
        int numCols = testData.get(0).size();
        double[][] rows = new double[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            List<Double> rowDoubles = testData.get(row);
            for (int col = 0; col < numCols; col++) {
                rows[row][col] = rowDoubles.get(col);
            }
        }

        return rows;
    }

    public double minor(int row, int column) {
        return subMatrix(row, column).determinant();
    }

    public double cofactor(int row, int column) {
        return subMatrix(row, column).determinant()
            * ((row + column) % 2 == 0 ? 1 : -1);
    }

    public Matrix transpose() {
        double[][] result = new double[this.getWidth()][this.getHeight()];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[col][row] = this.get(row, col);
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

    public Matrix multiply(Matrix matrix) {
        double[][] result = new double[this.getWidth()][this.getHeight()];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                double cell = 0;
                for (int i = 0; i < result.length; i++) {
                    cell += this.get(row, i) * matrix.get(i, col);
                }
                result[row][col] = cell;
            }
        }

        return new Matrix(result);
    }

    public Tuple multiply(Tuple tuple) {
        double[] result = new double[this.getWidth()];
        for (int row = 0; row < result.length; row++) {
            double cell = 0;
            for (int i = 0; i < result.length; i++) {
                cell += this.get(row, i) * tuple.get(i);
            }
            result[row] = cell;
        }

        return new Tuple(result[0], result[1], result[2], result[3]);
    }

    // Laplace-Entwicklung
    public double determinant() {
        int n = getWidth();
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
            determinant += sign * matrix[0][i] * new Matrix(subMatrix).determinant();
            sign *= -1;
        }

        return determinant;
    }

    public Matrix subMatrix(int row, int column) {
        double[][] result = new double[this.getWidth() - 1][this.getHeight() - 1];

        int ri = 0, rj = 0;
        for (int i = 0; i < this.getWidth(); i++) {
            if (i == row) continue;
            for (int j = 0; j < this.getHeight(); j++) {
                if (j == column) continue;
                result[ri][rj++] = this.get(i, j);
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
        if (!this.isInvertible())
            return null;

        double[][] matrix2 = new double[this.getWidth()][this.getHeight()];

        for (int row = 0; row < this.getWidth(); row++) {
            for (int column = 0; column < this.getHeight(); column++) {
                double c = this.cofactor(row, column);
                matrix2[column][row] = c / this.determinant();
            }
        }

        return new Matrix(matrix2);
    }


    public Matrix translation(int x, int y, int z) {
        matrix[0][3] = x;
        matrix[1][3] = y;
        matrix[2][3] = z;

        return new Matrix(matrix);
    }

    public Matrix scaling(int x, int y, int z) {
        matrix[0][0] = x;
        matrix[1][1] = y;
        matrix[2][2] = z;

        return new Matrix(matrix);
    }

    public static Matrix rotation_x(double r) {
        return new Matrix(new double[][]{
            {1, 0, 0, 0},
            {0, Math.cos(r), -Math.sin(r), 0},
            {0, Math.sin(r), Math.cos(r), 0},
            {0, 0, 0, 1}
        });
    }

    public static Matrix rotation_y(double r) {
        return new Matrix(new double[][]{
            {Math.cos(r), 0, Math.sin(r), 0},
            {0, 1, 0, 0},
            {-Math.sin(r), 0, Math.cos(r), 0},
            {0, 0, 0, 1}
        });
    }

    public static Matrix rotation_z(double r) {
        return new Matrix(new double[][]{
            {Math.cos(r), -Math.sin(r), 0, 0},
            {Math.sin(r), Math.cos(r), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        });
    }


    public static Matrix shearing(int xy, int xz, int yx, int yz, int zx, int zy) {
        return new Matrix(new double[][]{
            {1, xy, xz, 0},
            {yx, 1, yz, 0},
            {zx, zy, 1, 0},
            {0, 0, 0, 1}
        });
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

    public static Matrix identityMatrix() {
        double[][] iMatrix = new double[4][4];
        for (int i = 0; i < 4; i++) {
            iMatrix[i][i] = 1;
        }

        return new Matrix(iMatrix);
    }
}
