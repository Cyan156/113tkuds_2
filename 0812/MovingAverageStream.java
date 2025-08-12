
import java.util.*;

public class MovingAverageStream {

    private int size;
    private Queue<Integer> window;
    private long sum;

    private PriorityQueue<Integer> maxHeap; // 左半邊
    private PriorityQueue<Integer> minHeap; // 右半邊
    private Map<Integer, Integer> delayed;  // 延遲刪除紀錄

    private Deque<Integer> minDeque; // 單調遞增
    private Deque<Integer> maxDeque; // 單調遞減

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new ArrayDeque<>();
        this.sum = 0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.delayed = new HashMap<>();

        this.minDeque = new ArrayDeque<>();
        this.maxDeque = new ArrayDeque<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        // Heap：放進對應半邊
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
        balanceHeaps();

        // Deque：維護最小值與最大值
        while (!minDeque.isEmpty() && minDeque.peekLast() > val) {
            minDeque.pollLast();
        }
        minDeque.offerLast(val);

        while (!maxDeque.isEmpty() && maxDeque.peekLast() < val) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(val);

        // 如果超過視窗大小，移除最舊元素
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;

            // Heap 延遲刪除
            delayed.put(removed, delayed.getOrDefault(removed, 0) + 1);
            if (removed <= maxHeap.peek()) {
                pruneHeap(maxHeap);
            } else {
                pruneHeap(minHeap);
            }
            balanceHeaps();

            // 更新 Deque
            if (removed == minDeque.peekFirst()) {
                minDeque.pollFirst();
            }
            if (removed == maxDeque.peekFirst()) {
                maxDeque.pollFirst();
            }
        }

        return sum * 1.0 / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }

    public int getMin() {
        return minDeque.peekFirst();
    }

    public int getMax() {
        return maxDeque.peekFirst();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            pruneHeap(maxHeap);
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            pruneHeap(minHeap);
        }
    }

    private void pruneHeap(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.666...
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
