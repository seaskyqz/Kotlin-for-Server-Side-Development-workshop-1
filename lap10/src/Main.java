import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("---------- Q1 ----------");
        int[] b = {24, 13, 26, 1, 2, 27, 38};
        q2_1(b); // ข้อ 1: recursive print

        System.out.println("\n---------- Q2 ----------");
        int[] a2 = {30, 28, 5, 3, 1};
        int[] b2 = {29, 27, 5};
        int[] merged = q2_2(a2, b2); // ข้อ 2: merge descending arrays
        for (int val : merged) {
            System.out.print(val + " ");
        }

        System.out.println("\n\n---------- Q3 ----------");
        for (int i = 0; i < 5; i++) { // ข้อ 3: merge sort + วัดเวลา (5 รอบพอ)
            int[] arr = nodup_array(10000);
            long start = System.nanoTime();
            mergesort(arr);
            long end = System.nanoTime();
            System.out.println("Run " + (i + 1) + ": " + (end - start) + " ns");
        }
    }

    // -----------------------------------------------
    // ✅ Q1: Recursive กรองเลข 1–13 พร้อมพิมพ์ทุก array
    public static void q2_1(int[] b) {
        recursivePrint(b, 1);
    }

    private static void recursivePrint(int[] arr, int target) {
        // พิมพ์ array ปัจจุบัน
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.println("}");

        // ✅ แก้: ต้องให้ target 13 ได้ทำงานด้วย
        if (target > 13) return;

        // กรอง target ออก
        int count = 0;
        for (int val : arr) {
            if (val != target) count++;
        }

        int[] next = new int[count];
        int idx = 0;
        for (int val : arr) {
            if (val != target) next[idx++] = val;
        }

        recursivePrint(next, target + 1);
    }

    // -----------------------------------------------
    // ✅ Q2: Merge descending arrays
    public static int[] q2_2(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] >= b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i < a.length) {
            c[k++] = a[i++];
        }

        while (j < b.length) {
            c[k++] = b[j++];
        }

        return c;
    }

    // -----------------------------------------------
    // ✅ Q3: Merge sort + วัดเวลา

    public static void mergesort(int[] a) {
        divide(a, 0, a.length - 1);
    }

    private static void divide(int[] arr, int i, int j) {
        if (i < j) {
            int m = i + (j - i) / 2;
            divide(arr, i, m);
            divide(arr, m + 1, j);
            merge(arr, i, m, j);
        }
    }

    private static void merge(int[] arr, int i, int m, int j) {
        int n1 = m - i + 1;
        int n2 = j - m;

        int[] A = new int[n1];
        int[] B = new int[n2];

        for (int k = 0; k < n1; k++) A[k] = arr[i + k];
        for (int k = 0; k < n2; k++) B[k] = arr[m + 1 + k];

        int[] C = sort_min_to_max(A, B);
        int q = 0;
        for (int k = i; k <= j; k++) arr[k] = C[q++];
    }

    public static int[] sort_min_to_max(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (true) {
            if (i >= a.length) {
                while (j < b.length) c[k++] = b[j++];
                break;
            }
            if (j >= b.length) {
                while (i < a.length) c[k++] = a[i++];
                break;
            }
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }
        return c;
    }

    // สร้าง array ไม่มีเลขซ้ำ จาก 1 ถึง n
    public static int[] nodup_array(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt(n);
            int temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
        return arr;
    }
}
