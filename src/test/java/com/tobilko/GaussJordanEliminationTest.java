package com.tobilko;

import com.tobilko.lab5.GaussJordanElimination;
import org.junit.Test;

/**
 * Created by Andrew Tobilko on 11/22/17.
 */
public final class GaussJordanEliminationTest {

    @Test
    public void test1() {
        double[][] matrix = {
                {0, 1, 1},
                {2, 4, -2},
                {0, 3, 15}
        };
        double[] b = {4, 2, 36};

        test(matrix, b);
    }

    @Test
    public void test2() {
        double[][] matrix = {
                {1, -3, 1},
                {2, -8, 8},
                {-6, 3, -15}
        };
        double[] b = {4, -2, 9};

        test(matrix, b);
    }

    @Test
    public void test3() {
        double[][] matrix = {
                {2, -3, -1, 2, 3},
                {4, -4, -1, 4, 11},
                {2, -5, -2, 2, -1},
                {0, 2, 1, 0, 4},
                {-4, 6, 0, 0, 7},
        };
        double[] b = {4, 4, 9, -6, 5};

        test(matrix, b);
    }

    @Test
    public void test4() {
        double[][] matrix = {
                {2, -3, -1, 2, 3},
                {4, -4, -1, 4, 11},
                {2, -5, -2, 2, -1},
                {0, 2, 1, 0, 4},
                {-4, 6, 0, 0, 7},
        };
        double[] b = {4, 4, 9, -5, 5};

        test(matrix, b);
    }

    @Test
    public void test5() {
        double[][] matrix = {
                {2, -1, 1},
                {3, 2, -4},
                {-6, 3, -3},
        };
        double[] b = {1, 4, 2};

        test(matrix, b);
    }

    @Test
    public void test6() {
        double[][] matrix = {
                {1, -1, 2},
                {4, 4, -2},
                {-2, 2, -4},
        };
        double[] b = {-3, 1, 6};

        test(matrix, b);
    }

    private void test(double[][] A, double[] b) {
        final GaussJordanElimination gaussian = new GaussJordanElimination(A, b);

        if (gaussian.isFeasible()) {
            printResultWithTitle(gaussian.primal(), "Solution to Ax = b");
        } else {
            printResultWithTitle(gaussian.dual(), "Certificate of infeasibility");
        }
    }

    private void printResultWithTitle(double[] result, String title) {
        System.out.println(title);

        for (double i : result) {
            System.out.printf("%10.3f\n", i);
        }
    }

}
