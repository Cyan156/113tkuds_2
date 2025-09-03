
/**
 * Definition for singly-linked list.
 */
class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立虛擬頭節點(dummy)，方便刪除頭節點
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;

        // first 指標先走 n+1 步，保持與 second 的距離為 n
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 同時移動 first 與 second，直到 first 到尾端
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // second.next 就是要刪除的節點，跳過它
        second.next = second.next.next;

        return dummy.next; // 返回新的頭節點
    }
}

/*
解題思路：
1. 建立虛擬頭節點(dummy)，方便刪除頭節點。
2. 使用雙指標 (first, second)：
   - first 指標先走 n+1 步，保持與 second 的距離為 n。
   - 同時移動 first 與 second，直到 first 到尾端。
3. 此時 second.next 即為要刪除的節點，直接跳過。
4. 返回 dummy.next 作為新的頭節點。

時間複雜度：O(sz)，遍歷一次鏈表
空間複雜度：O(1)，只使用兩個指標
 */
