
import java.util.*;

public class M08_BSTRangedSum {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        String[] parts = sc.nextLine().trim().split("\\s+");
        String[] lr = sc.nextLine().trim().split("\\s+");
        int L = Integer.parseInt(lr[0]);
        int R = Integer.parseInt(lr[1]);

        Node root = buildTree(parts, n);
        int sum = rangeSum(root, L, R);
        System.out.println("Sum: " + sum);
    }

    static Node buildTree(String[] parts, int n) {
        if (n == 0 || parts[0].equals("-1")) {
            return null;
        }
        Node root = new Node(Integer.parseInt(parts[0]));
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < n) {
            Node cur = q.poll();
            if (i < n && !parts[i].equals("-1")) {
                cur.left = new Node(Integer.parseInt(parts[i]));
                q.offer(cur.left);
            }
            i++;
            if (i < n && !parts[i].equals("-1")) {
                cur.right = new Node(Integer.parseInt(parts[i]));
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    static int rangeSum(Node root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val < L) {
            return rangeSum(root.right, L, R);
        }
        if (root.val > R) {
            return rangeSum(root.left, L, R);
        }
        return root.val + rangeSum(root.left, L, R) + rangeSum(root.right, L, R);
    }
}
