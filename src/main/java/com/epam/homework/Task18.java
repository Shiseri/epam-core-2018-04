package com.epam.homework;

import java.util.*;

public class Task18 {

    /**
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
     * Гарантируется что после удаления в матрице останется хотя бы один элемент.
     * <a href="https://github.com/elefus/epam-core-04-2018/wiki/Представление-матриц">Представление матриц</a>
     *
     * Формат входных данных:
     * N - целое число (0 < N < 10)
     * N ^ 2 целых чисел (Integer.MIN_VALUE < element < Integer.MAX_VALUE)
     *
     * Формат выходных данных:
     * В результате выполнения задания в выходной поток должна быть выведена преобразованная матрица.
     * Так как матрица в результате преобразования может перестать быть квадратной - несколько изменяется формат её представления:
     * N (целое число) - количество строк
     * M (целое число) - количество столбцов
     * N * M целых чисел, являющихся элементами матрицы
     *
     * ---------------------------------------------------------------------------------------------------
     * Примеры выполнения задания:
     *
     * Входные данные:
     *  3
     *  2  1 -3
     * -2  3  2
     * -1  0  0
     *
     * Выходные данные:
     *  2
     *  2
     *  2 -3
     * -1  0
     *
     *
     *
     * Входные данные:
     *  4
     *  3 -2 -4  1
     *  1  4  4  2
     * -1  0 -3  1
     *  0  2  1  3
     *
     * Выходные данные:
     *  3
     *  2
     *  3  1
     * -1  1
     *  0  3
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int[][] cleanMatrix = clean(matrix, getMax(matrix));

        System.out.println(cleanMatrix.length);
        System.out.println(cleanMatrix[0].length);
        print(cleanMatrix);
    }

    private static int[][] readMatrix(Scanner scanner) {
        int dimension = scanner.nextInt();
        int[][] matrix = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }

    private static int[][] clean(int[][] matrix, int el) {
        RowsAndColumns rowsAndColumnsToRemove = getRowsAndColumnsToRemove(matrix, el);
        Set<Integer> rowsToRemove = rowsAndColumnsToRemove.getRows();
        Set<Integer> columnsToRemove = rowsAndColumnsToRemove.getColumns();
        LinkedList<Integer> cleanMatrixAsQueue = new LinkedList<>();

        for (int rowN = 0; rowN < matrix.length; rowN++) {
            if (rowsToRemove.contains(rowN)) {
                continue;
            }

            for (int columnN = 0; columnN < matrix[0].length; columnN++) {
                if (columnsToRemove.contains(columnN)) {
                    continue;
                }

                cleanMatrixAsQueue.add(matrix[rowN][columnN]);
            }
        }

        return queueToMatrix(cleanMatrixAsQueue, matrix.length - rowsToRemove.size(), matrix[0].length - columnsToRemove.size());
    }

    private static RowsAndColumns getRowsAndColumnsToRemove(int[][] matrix, int el) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == el) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }

        return new RowsAndColumns(rows, columns);
    }

    private static int[][] queueToMatrix(Queue<Integer> queue, int m, int n) throws IllegalArgumentException {
//        if (queue.size() != m * n) {
//            throw new IllegalArgumentException("queue.size() != m * n");
//        }

        int[][] cleanMatrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cleanMatrix[i][j] = queue.poll();
            }
        }

        return cleanMatrix;
    }

    private static int getMax(int[][] matrix) {
        int maxEl = matrix[0][0];

        for (int[] row : matrix) {
            for (int el : row) {
                if (el > maxEl) {
                    maxEl = el;
                }
            }
        }

        return maxEl;
    }

    private static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int el : row) {
                System.out.printf("%4d", el);
            }
            System.out.println();
        }
    }

    static class RowsAndColumns {
        private Set<Integer> rows;
        private Set<Integer> columns;

        RowsAndColumns(Set<Integer> rows, Set<Integer> columns) {
            this.rows = rows;
            this.columns = columns;
        }

        Set<Integer> getRows() {
            return rows;
        }

        Set<Integer> getColumns() {
            return columns;
        }
    }
}
