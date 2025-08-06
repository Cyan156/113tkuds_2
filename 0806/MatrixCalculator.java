
public class MatrixCalculator {

    public static int[][] add(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;
        if (colsA != rowsB) {
            throw new IllegalArgumentException("矩陣無法相乘，維度不合");
        }
        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[] findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
                if (val < min) {
                    min = val;
                }
            }
        }
        return new int[]{max, min};
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] m1 = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] m2 = {
            {7, 8, 9},
            {10, 11, 12}
        };
        int[][] m3 = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 m1:");
        printMatrix(m1);
        System.out.println("矩陣 m2:");
        printMatrix(m2);

        System.out.println("\nm1 + m2:");
        printMatrix(add(m1, m2));

        System.out.println("\nm1 * m3:");
        printMatrix(multiply(m1, m3));

        System.out.println("\nm1 轉置:");
        printMatrix(transpose(m1));

        int[] maxMin = findMaxMin(m1);
        System.out.printf("\nm1 最大值：%d，最小值：%d\n", maxMin[0], maxMin[1]);
    }
}
