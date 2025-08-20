
import java.util.*;

public class M01_BuildHeap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.nextLine().trim();
        int n = Integer.parseInt(sc.nextLine().trim());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        buildHeap(arr, type.equals("max"));

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static void buildHeap(int[] arr, boolean isMax) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, isMax);
        }
    }

    static void heapify(int[] arr, int n, int i, boolean isMax) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (isMax) {
            if (left < n && arr[left] > arr[extreme]) {
                extreme = left;
            }
            if (right < n && arr[right] > arr[extreme]) {
                extreme = right;
            }
        } else {
            if (left < n && arr[left] < arr[extreme]) {
                extreme = left;
            }
            if (right < n && arr[right] < arr[extreme]) {
                extreme = right;
            }
        }

        if (extreme != i) {
            int tmp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = tmp;
            heapify(arr, n, extreme, isMax);
        }
    }
}
