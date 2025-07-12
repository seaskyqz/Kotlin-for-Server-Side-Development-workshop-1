public class Q2_ZigZag2DArray {
    public static int[][] q1_1(int n) {
        int[][] a = new int[n][n];

        int val = 1;

        for (int row = 0; row < n; row++) {
            int colLimit = n - row - 1;
            for (int col = 0; col < colLimit; col++) {
                a[row][col] = val++;
            }
            if (colLimit < n) {
                a[row][colLimit] = -1; // ใส่ -1 บนเส้นทแยง
                for (int j = colLimit + 1; j < n; j++) {
                    a[row][j] = 0;
                }
            }
        }

        // เติมแถวสุดท้ายด้านล่างเป็น pattern เดิม
        for (int col = 0; col < n; col++) {
            if (col == 0) {
                a[n - 1][col] = -1;
            } else {
                a[n - 1][col] = 0;
            }
        }

        return a;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] a = q1_1(n);
        for (int[] row : a) {
            for (int num : row) {
                System.out.printf("%3d ", num);
            }
            System.out.println();
        }
    }
}
