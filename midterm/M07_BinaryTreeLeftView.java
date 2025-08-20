
import java.util.*;

public class M07_BinaryTreeLeftView {

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
        if (n == 0) {
            System.out.println("LeftView:");
            return;
        }

        Node root = buildTree(parts, n);
        List<Integer> view = leftView(root);

        System.out.print("LeftView:");
        for (int v : view) {
            System.out.print(" " + v);
        }
        System.out.println();
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

    static List<Integer> leftView(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (i == 0) {
                    res.add(cur.val);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return res;
    }
}
