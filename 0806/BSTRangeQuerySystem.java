
import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {

        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.data) {
            root.left = insert(root.left, val); 
        }else if (val > root.data) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    // 範圍查詢：回傳範圍內所有節點
    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private static void rangeQueryHelper(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.data > min) {
            rangeQueryHelper(node.left, min, max, result);
        }
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }
        if (node.data < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 範圍計數：計算範圍內節點數量
    public static int rangeCount(TreeNode root, int min, int max) {
        return rangeCountHelper(root, min, max);
    }

    private static int rangeCountHelper(TreeNode node, int min, int max) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.data >= min && node.data <= max) {
            count = 1;
        }
        if (node.data > min) {
            count += rangeCountHelper(node.left, min, max);
        }
        if (node.data < max) {
            count += rangeCountHelper(node.right, min, max);
        }
        return count;
    }

    // 範圍總和：計算範圍內節點值總和
    public static int rangeSum(TreeNode root, int min, int max) {
        return rangeSumHelper(root, min, max);
    }

    private static int rangeSumHelper(TreeNode node, int min, int max) {
        if (node == null) {
            return 0;
        }
        int sum = 0;
        if (node.data >= min && node.data <= max) {
            sum = node.data;
        }
        if (node.data > min) {
            sum += rangeSumHelper(node.left, min, max);
        }
        if (node.data < max) {
            sum += rangeSumHelper(node.right, min, max);
        }
        return sum;
    }

    // 找最接近值的節點
    public static int findClosest(TreeNode root, int target) {
        TreeNode node = root;
        int closest = root.data;
        while (node != null) {
            if (Math.abs(node.data - target) < Math.abs(closest - target)) {
                closest = node.data;
            }
            if (target < node.data) {
                node = node.left;
            } else if (target > node.data) {
                node = node.right;
            } else {
                return node.data;
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {20, 10, 30, 5, 15, 25, 35, 3, 7, 13, 17};
        for (int val : values) {
            root = insert(root, val);
        }

        System.out.println("BST 範圍查詢系統");

        int min = 7, max = 26;
        List<Integer> nodesInRange = rangeQuery(root, min, max);
        System.out.println("範圍 [" + min + ", " + max + "] 內節點: " + nodesInRange);

        int count = rangeCount(root, min, max);
        System.out.println("範圍內節點數量: " + count);

        int sum = rangeSum(root, min, max);
        System.out.println("範圍內節點值總和: " + sum);

        int target = 12;
        int closest = findClosest(root, target);
        System.out.println("最接近 " + target + " 的節點值: " + closest);
    }
}
