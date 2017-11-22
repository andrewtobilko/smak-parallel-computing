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

    private void test(double[][] A, double[] b) {
        final GaussJordanElimination gaussian = new GaussJordanElimination(A, b);

        System.out.println("Solution to Ax = b");
        printResult(gaussian.extractSolution());
    }

    private void printResult(double[] result) {
        for (double i : result) {
            System.out.printf("%10.3f\n", i);
        }
    }

}
