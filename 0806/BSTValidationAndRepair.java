
import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> invalid = new ArrayList<>();
        findInvalidNodes(root, Long.MIN_VALUE, Long.MAX_VALUE, invalid);
        return invalid;
    }

    private static void findInvalidNodes(TreeNode node, long min, long max, List<TreeNode> invalid) {
        if (node == null) {
            return;
        }
        if (node.val <= min || node.val >= max) {
            invalid.add(node);
        }
        findInvalidNodes(node.left, min, node.val, invalid);
        findInvalidNodes(node.right, node.val, max, invalid);
    }

    public static void recoverBST(TreeNode root) {
        TreeNode[] nodes = new TreeNode[2];
        TreeNode[] prev = new TreeNode[1];
        inorderRecover(root, prev, nodes);
        if (nodes[0] != null && nodes[1] != null) {
            int temp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = temp;
        }
    }

    private static void inorderRecover(TreeNode node, TreeNode[] prev, TreeNode[] nodes) {
        if (node == null) {
            return;
        }
        inorderRecover(node.left, prev, nodes);
        if (prev[0] != null && node.val < prev[0].val) {
            if (nodes[0] == null) {
                nodes[0] = prev[0];
                nodes[1] = node;
            } else {
                nodes[1] = node;
            }
        }
        prev[0] = node;
        inorderRecover(node.right, prev, nodes);
    }

    public static int minDeletionsToBST(TreeNode root) {
        return countInvalid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static int countInvalid(TreeNode node, long min, long max) {
        if (node == null) {
            return 0;
        }
        if (node.val <= min || node.val >= max) {
            return 1 + countInvalid(node.left, min, max) + countInvalid(node.right, min, max);
        }
        return countInvalid(node.left, min, node.val) + countInvalid(node.right, node.val, max);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println(isValidBST(root));
        List<TreeNode> invalids = findInvalidNodes(root);
        for (TreeNode n : invalids) {
            System.out.print(n.val + " ");
        }
        System.out.println();
        recoverBST(root);
        System.out.println(isValidBST(root));
        System.out.println(minDeletionsToBST(root));
    }
}
