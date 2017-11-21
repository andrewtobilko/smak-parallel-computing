package com.tobilko.lab5;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Andrew Tobilko on 11/20/17.
 */
public class GaussJordanEliminationTest {

    // 3-by-3 nonsingular system
    @Test
    public void test1() {
        double[][] A = {
                {0, 1, 1},
                {2, 4, -2},
                {0, 3, 15}
        };
        double[] b = {4, 2, 36};
        test(A, b);
    }

    // 3-by-3 nonsingular system
    @Test
    public void test2() {
        double[][] A = {
                {1, -3, 1},
                {2, -8, 8},
                {-6, 3, -15}
        };
        double[] b = {4, -2, 9};
        test(A, b);
    }

    // 5-by-5 singular: no solutions
    // y = [ -1, 0, 1, 1, 0 ]
    @Test
    public void test3() {
        double[][] A = {
                {2, -3, -1, 2, 3},
                {4, -4, -1, 4, 11},
                {2, -5, -2, 2, -1},
                {0, 2, 1, 0, 4},
                {-4, 6, 0, 0, 7},
        };
        double[] b = {4, 4, 9, -6, 5};
        test(A, b);
    }

    // 5-by-5 singluar: infinitely many solutions
    @Test
    public void test4() {
        double[][] A = {
                {2, -3, -1, 2, 3},
                {4, -4, -1, 4, 11},
                {2, -5, -2, 2, -1},
                {0, 2, 1, 0, 4},
                {-4, 6, 0, 0, 7},
        };
        double[] b = {4, 4, 9, -5, 5};
        test(A, b);
    }


    // 3-by-3 singular: no solutions
    // y = [ 1, 0, 1/3 ]
    @Test
    public void test5() {
        double[][] A = {
                {2, -1, 1},
                {3, 2, -4},
                {-6, 3, -3},
        };
        double[] b = {1, 4, 2};
        test(A, b);
    }

    // 3-by-3 singular: infinitely many solutions
    @Test
    public void test6() {
        double[][] A = {
                {1, -1, 2},
                {4, 4, -2},
                {-2, 2, -4},
        };
        double[] b = {-3, 1, 6};
        test(A, b);
    }

    public void test(double[][] A, double[] b) {
        GaussJordanElimination gaussian = new GaussJordanElimination(A, b);
        if (gaussian.isFeasible()) {
            System.out.println("Solution to Ax = b");
            double[] x = gaussian.primal();
            for (int i = 0; i < x.length; i++) {
                System.out.printf("%10.6f\n", x[i]);
            }
        } else {
            System.out.println("Certificate of infeasibility");
            double[] y = gaussian.dual();
            for (int j = 0; j < y.length; j++) {
                System.out.printf("%10.6f\n", y[j]);
            }
        }
        System.out.println();
    }

}
