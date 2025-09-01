import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 小的一半
    private PriorityQueue<Integer> minHeap; // 大的一半
    private Map<Integer, Integer> delayed;  // 延遲刪除
    private int smallSize, largeSize;       // 真實大小（扣掉延遲刪除的元素）

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        smallSize = 0;
        largeSize = 0;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        
        for (int i = 0; i < n; i++) {
            addNum(nums[i]);
            if (i >= k) {
                removeNum(nums[i - k]); // 移除滑出視窗的數
            }
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            smallSize++;
        } else {
            minHeap.offer(num);
            largeSize++;
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= maxHeap.peek()) {
            smallSize--;
            if (num == maxHeap.peek()) prune(maxHeap);
        } else {
            largeSize--;
            if (num == minHeap.peek()) prune(minHeap);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (smallSize > largeSize + 1) {
            minHeap.offer(maxHeap.poll());
            smallSize--;
            largeSize++;
            prune(maxHeap);
        } else if (smallSize < largeSize) {
            maxHeap.offer(minHeap.poll());
            largeSize--;
            smallSize++;
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) delayed.remove(num);
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return (double) maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums1, 3)));
        // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums2, 2)));
        // [1.5, 2.5, 3.5]
    }
}
