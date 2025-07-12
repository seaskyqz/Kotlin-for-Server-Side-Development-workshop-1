import java.util.*;

public class Q1_NoDupArray {
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

    public static void main(String[] args) {
        int[] result = nodup_array(10);
        System.out.println(Arrays.toString(result));
    }
}
