
import java.util.*;

public class M09_AVLValidate {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] vals = sc.nextLine().split(" ");
        Node root = buildTree(vals);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (checkAVL(root) == -2) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    static Node buildTree(String[] vals) {
        if (vals.length == 0 || vals[0].equals("-1")) {
            return null;
        }
        Node root = new Node(Integer.parseInt(vals[0]));
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            Node cur = q.poll();
            if (i < vals.length && !vals[i].equals("-1")) {
                cur.left = new Node(Integer.parseInt(vals[i]));
                q.add(cur.left);
            }
            i++;
            if (i < vals.length && !vals[i].equals("-1")) {
                cur.right = new Node(Integer.parseInt(vals[i]));
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    static boolean isBST(Node root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    static int checkAVL(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = checkAVL(root.left);
        if (lh == -2) {
            return -2;
        }
        int rh = checkAVL(root.right);
        if (rh == -2) {
            return -2;
        }
        if (Math.abs(lh - rh) > 1) {
            return -2;
        }
        return Math.max(lh, rh) + 1;
    }
}
