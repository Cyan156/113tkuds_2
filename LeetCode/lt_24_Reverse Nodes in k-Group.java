
/**
 * Definition for singly-linked list.
 */

class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        // 虛擬頭節點
        ListNode dummy = new ListNode(0, head);
        ListNode prevGroup = dummy;
        ListNode curr = head;

        int count = 0;
        // 計算節點總數
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        curr = dummy.next;

        while (count >= k) {
            // 標記本次需要反轉的起點與結尾
            ListNode prev = null;
            ListNode tail = curr;

            // 反轉 k 個節點
            for (int i = 0; i < k; i++) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }

            // 連接前一組與反轉後的頭節點
            prevGroup.next = prev;
            tail.next = curr;

            // 更新 prevGroup 為本次反轉的尾節點
            prevGroup = tail;
            count -= k;
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用虛擬頭節點(dummy)方便操作。
2. 先計算鏈表節點總數 count。
3. 遍歷整個鏈表，每次取 k 個節點進行反轉：
   - 使用三指標 prev, curr, nextNode 進行 in-place 反轉。
4. 將反轉後的節點連接到前一組 (prevGroup)。
5. 更新 prevGroup 為反轉後的尾節點，準備下一組反轉。
6. 如果剩餘節點不足 k 個，保持原順序。
7. 時間複雜度：O(n)
8. 空間複雜度：O(1)，僅使用固定指標，符合 follow-up 要求
 */
