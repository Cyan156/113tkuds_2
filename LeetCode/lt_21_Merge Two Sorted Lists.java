
/**
 * Definition for singly-linked list.
 */

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立虛擬頭節點(dummy)方便操作
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 遍歷兩個鏈表，逐個比較
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next; // 移動尾指標
        }

        // 若其中一個鏈表還有剩餘節點，直接接到尾端
        if (list1 != null) {
            tail.next = list1;
        }
        if (list2 != null) {
            tail.next = list2;
        }

        return dummy.next; // 返回合併後的頭節點
    }
}

/*
解題思路：
1. 使用虛擬頭節點(dummy)來簡化操作，避免額外判斷頭節點。
2. 使用一個尾指標(tail)來構建新的鏈表。
3. 同時遍歷 list1 和 list2：
   - 比較當前節點值，將較小節點接到 tail.next。
   - 尾指標移動到剛接上的節點。
4. 遍歷結束後，如果某個鏈表還有剩餘節點，直接接到 tail.next。
5. 返回 dummy.next 作為合併後的鏈表頭。
時間複雜度：O(n + m)，n 和 m 為兩個鏈表長度
空間複雜度：O(1)，使用原鏈表節點，不額外分配
 */
