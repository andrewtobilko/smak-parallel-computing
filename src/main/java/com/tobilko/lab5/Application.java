package com.tobilko.lab5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
public final class Application {

    public static void main(String[] args) {
        // TODO: 11/17/17 Solve a system by using the Gauss-Jordan elimination method.

        // 1. Write the augmented matrix of the system.
        final int[][] leftMatrix = {
                {1, 1, 1},
                {2, 3, 5},
                {4, 0, 5}
        };
        final int[] rightMatrix = {5, 8, 2};

        printAugmentedMatrix(leftMatrix, rightMatrix,"before");


        // 2. Use row operations to transform the augmented matrix in the form described below, which is
        // called the reduced row echelon form (RREF).

        for (int rowIndex = 1; rowIndex < leftMatrix.length; ++rowIndex) {
            for (int columnIndex = 0; columnIndex < leftMatrix[rowIndex].length; ++columnIndex) {
                leftMatrix[rowIndex][columnIndex] = leftMatrix[rowIndex][columnIndex] - leftMatrix[rowIndex - 1][0] * 2;
            }
            printAugmentedMatrix(leftMatrix, rightMatrix,"step " + rowIndex);
        }


        printAugmentedMatrix(leftMatrix,rightMatrix, "after");
    }

    public static void printAugmentedMatrix(int[][] matrix, int[] row, String title) {
        System.out.println(title);

        for (int i = 0; i < matrix.length; ++i) {
            System.out.printf("%s\t%d\n", getRowString(matrix[i]), row[i]);
        }
    }

    private static String getRowString(int[] row) {
        return IntStream.of(row).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

}
