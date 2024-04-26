/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author edgar
 */
public class MyArrays<T> extends ArrayList<T> {
     // Método para imprimir un arreglo de enteros
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Método para imprimir una matriz de enteros
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    // Método para verificar si dos arreglos de enteros son iguales
    public static boolean arraysEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // Método para verificar si dos matrices de enteros son iguales
    public static boolean matricesEquals(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            return false;
        }
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

      public static String deepToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
      public static String toString(int[] array) {
        return Arrays.toString(array);
    }

      public static int[] copyOf(int[] original, int newLength) {
        int[] copy = new int[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    // Método para copiar una matriz de enteros
    public static int[][] copyOf(int[][] original, int newLength) {
        int[][] copy = new int[newLength][];
        for (int i = 0; i < Math.min(original.length, newLength); i++) {
            copy[i] = copyOf(original[i], original[i].length);
        }
        return copy;
    }
}
