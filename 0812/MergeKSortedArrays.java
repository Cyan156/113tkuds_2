
import java.util.*;

public class MergeKSortedArrays {

    // 用來記錄 Heap 中的元素狀態
    static class HeapNode implements Comparable<HeapNode> {

        int value;
        int arrayIndex;  // 來自第幾個陣列
        int elementIndex; // 該陣列的第幾個元素

        HeapNode(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(HeapNode other) {
            return Integer.compare(this.value, other.value); // Min Heap
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>();

        // 初始化：每個陣列取第一個元素放進 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new HeapNode(arrays[i][0], i, 0));
            }
        }

        // 從 heap 中取出最小值，然後將同陣列的下一個元素放入 heap
        while (!minHeap.isEmpty()) {
            HeapNode node = minHeap.poll();
            result.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.add(new HeapNode(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] case1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] case2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] case3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(case1)); // [1, 1, 2, 3, 4, 4, 5, 6]
        System.out.println(mergeKSortedArrays(case2)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(mergeKSortedArrays(case3)); // [0, 1]
    }
}
