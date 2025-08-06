
import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {

        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    private static class SumCount {

        int sum;
        int count;

        SumCount(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    private static SumCount sumAndCount(TreeNode root) {
        if (root == null) {
            return new SumCount(0, 0);
        }
        SumCount left = sumAndCount(root.left);
        SumCount right = sumAndCount(root.right);
        return new SumCount(root.data + left.sum + right.sum, 1 + left.count + right.count);
    }

    public static double averageValue(TreeNode root) {
        SumCount sc = sumAndCount(root);
        if (sc.count == 0) {
            return 0.0;
        }
        return (double) sc.sum / sc.count;
    }

    public static int findMax(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int leftMax = findMax(root.left);
        int rightMax = findMax(root.right);
        return Math.max(root.data, Math.max(leftMax, rightMax));
    }

    public static int findMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int leftMin = findMin(root.left);
        int rightMin = findMin(root.right);
        return Math.min(root.data, Math.min(leftMin, rightMin));
    }

    public static int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return maxWidth;
    }

    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (mustBeLeaf) {
                    return false;
                }
                queue.offer(node.left);
            } else {
                mustBeLeaf = true;
            }
            if (node.right != null) {
                if (mustBeLeaf) {
                    return false;
                }
                queue.offer(node.right);
            } else {
                mustBeLeaf = true;
            }
        }
        return true;
    }

    public static TreeNode buildSampleTree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildSampleTree();
        System.out.println("節點總和: " + sumAndCount(root).sum);
        System.out.println("節點數量: " + sumAndCount(root).count);
        System.out.printf("節點平均值: %.2f\n", averageValue(root));
        System.out.println("最大值節點: " + findMax(root));
        System.out.println("最小值節點: " + findMin(root));
        System.out.println("樹的最大寬度: " + maxWidth(root));
        System.out.println("是否為完全二元樹: " + (isCompleteTree(root) ? "是" : "否"));
    }
}
