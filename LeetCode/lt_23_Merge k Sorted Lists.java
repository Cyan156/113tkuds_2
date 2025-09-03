
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 */
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 優先隊列 (小根堆)，自定義比較器按節點值排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 將所有非空鏈表頭節點加入堆中
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        // 建立虛擬頭節點(dummy)
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll(); // 取出最小節點
            tail.next = node;
            tail = tail.next;

            // 如果節點有下一個，加入堆中
            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用小根堆 (PriorityQueue) 來管理 k 條鏈表的節點，每次取最小節點。
2. 將所有非空鏈表的頭節點加入小根堆。
3. 建立虛擬頭節點(dummy)，用 tail 來構建新的鏈表。
4. 每次從堆中取出最小節點，連接到 tail，若該節點有下一個節點，加入堆。
5. 重複直到堆為空。
6. 返回 dummy.next 作為合併後的鏈表頭。

時間複雜度：O(N log k)，N 為總節點數，k 為鏈表數量
空間複雜度：O(k)，堆中最多存 k 個節點
 */
