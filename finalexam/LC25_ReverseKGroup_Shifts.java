
import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            return;
        }

        String[] tokens = line.split(" ");
        ListNode dummy = new ListNode(0), curr = dummy;
        for (String t : tokens) {
            curr.next = new ListNode(Integer.parseInt(t));
            curr = curr.next;
        }

        ListNode reversed = reverseKGroup(dummy.next, k);
        curr = reversed;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            ListNode node = prev;
            for (int i = 0; i < k && node != null; i++) {
                node = node.next;
            }
            if (node == null) {
                break;
            }

            ListNode start = prev.next, nextGroup = node.next;
            node.next = null;
            prev.next = reverseList(start);
            start.next = nextGroup;
            prev = start;
        }

        return dummy.next;
    }

    static ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
