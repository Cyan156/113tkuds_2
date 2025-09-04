
import java.util.*;

public class LC23_MergeKLists_Hospitals {

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
        List<ListNode> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String[] tokens = sc.nextLine().trim().split(" ");
            ListNode dummy = new ListNode(0), curr = dummy;
            for (String t : tokens) {
                int v = Integer.parseInt(t);
                if (v == -1) {
                    break;
                }
                curr.next = new ListNode(v);
                curr = curr.next;
            }
            lists.add(dummy.next);
        }

        ListNode merged = mergeKLists(lists);
        ListNode curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode l : lists) {
            if (l != null) {
                pq.offer(l);
            }
        }
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        return dummy.next;
    }
}
