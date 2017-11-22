package com.tobilko.lab5;

import static java.lang.Math.abs;

/**
 * Gauss-Jordan Elimination Method
 * http://pages.pacificcoast.net/~cazelais/251/gauss-jordan.pdf
 *
 * Created by Andrew Tobilko on 11/20/17.
 */
public class GaussJordanElimination {

    private final double EPSILON = 1e-8;

    private final int N;
    private double[][] A;

    public GaussJordanElimination(double[][] matrix, double[] b) {
        N = b.length;

        buildAugmentedMatrix(matrix, b);

        print();
        solve();

        if (!validate(matrix, b)) {
            throw new UnsupportedOperationException("It is not feasible!");
        }
    }

    private void buildAugmentedMatrix(double[][] matrix, double[] b) {
        A = new double[N][N + 1];

        for (int i = 0; i < N; ++i) {
            System.arraycopy(matrix[i], 0, A[i], 0, N);
        }

        for (int i = 0; i < N; ++i) {
            A[i][N] = b[i];
        }
    }

    private void solve() {
        for (int p = 0; p < N; ++p) {

            // find a pivot row
            int max = p;
            for (int i = p + 1; i < N; ++i) {
                if (abs(A[i][p]) > abs(A[max][p])) {
                    max = i;
                }
            }

            swapRowsByIndices(p, max);

            // is matrix singular or nearly singular?
            if (abs(A[p][p]) <= EPSILON) {
                continue;
            }

            // pivot
            pivot(p, p);
        }
    }

    private void swapRowsByIndices(int rowIndex1, int rowIndex2) {
        double[] temp = A[rowIndex1];
        A[rowIndex1] = A[rowIndex2];
        A[rowIndex2] = temp;
    }

    private void pivot(int p, int q) {

        // everything but row p and column q
        for (int i = 0; i < N; i++) {
            double alpha = A[i][q] / A[p][q];
            for (int j = 0; j <= N; j++) {
                if (i != p && j != q) A[i][j] -= alpha * A[p][j];
            }
        }

        // zero out column q
        for (int i = 0; i < N; i++) {
            if (i != p) {
                A[i][q] = 0.0;
            }
        }

        // scale row p (ok to go from q+1 to N, but do this for consistency with simplex pivot)
        for (int j = 0; j <= N; j++) {
            if (j != q) {
                A[p][j] /= A[p][q];
            }
        }

        A[p][q] = 1.0;
    }

    public double[] extractSolution() {
        double[] x = new double[N];

        for (int i = 0; i < N; i++) {
            if (abs(A[i][i]) > EPSILON) {
                x[i] = A[i][N] / A[i][i];
            } else if (abs(A[i][N]) > EPSILON) {
                return null;
            }
        }

        return x;
    }

    private void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%8.3f ", A[i][j]);
            }
            System.out.printf("| %8.3f\n", A[i][N]);
        }
        System.out.println();
    }

    private boolean validate(double[][] A, double[] b) {
        double[] x = extractSolution();

        for (int i = 0; i < N; i++) {
            double sum = .0;

            for (int j = 0; j < N; j++) {
                sum += A[i][j] * x[j];
            }

            if (abs(sum - b[i]) > EPSILON) {
                System.out.printf("b[%d] = %8.3f, sum = %8.3f\n", i, b[i], sum);

                return false;
            }
        }

        return true;
    }

}
