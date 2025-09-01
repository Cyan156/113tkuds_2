import java.util.*;

public class KthSmallestElement {

    // 方法 1：大小為 K 的 Max Heap
    public static int kthSmallestUsingMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 保持 heap 大小為 K
            }
        }
        return maxHeap.peek();
    }

    // 方法 2：Min Heap 提取 K 次
    public static int kthSmallestUsingMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
        }
        for (int i = 1; i < k; i++) {
            minHeap.poll(); // 移除最小的
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int t = 0; t < testArrays.length; t++) {
            int[] arr = testArrays[t];
            int k = ks[t];

            System.out.println("陣列: " + Arrays.toString(arr) + ", K=" + k);

            long start1 = System.nanoTime();
            int result1 = kthSmallestUsingMaxHeap(arr, k);
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            int result2 = kthSmallestUsingMinHeap(arr, k);
            long end2 = System.nanoTime();

            System.out.println("方法 1（Max Heap 大小為 K）: " + result1 +
                               "，耗時: " + (end1 - start1) + " ns");
            System.out.println("方法 2（Min Heap 提取 K 次）: " + result2 +
                               "，耗時: " + (end2 - start2) + " ns");
            System.out.println();
        }
    }
}
