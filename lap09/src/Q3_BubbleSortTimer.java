import java.util.*;

public class Q3_BubbleSortTimer {
    public static int[] nodup_array(int n) {
        int[] a = new int[n];
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(list.size());
            a[i] = list.remove(index);
        }
        return a;
    }

    public static int[] bubble(int[] arr) {
        int n = arr.length;
        int temp;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] a = nodup_array(10000);
            long start = System.nanoTime();
            bubble(a);
            long end = System.nanoTime();
            System.out.println(end - start);
        }
        System.out.println("-----");
    }
}
