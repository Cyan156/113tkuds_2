
import java.util.*;

public class TreePathProblems {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 找出從根節點到所有葉節點的路徑
    public static List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        path.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            backtrack(node.left, path, result);
            backtrack(node.right, path, result);
        }

        path.remove(path.size() - 1); // 回溯
    }

    // 2. 判斷是否存在和為目標值的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出和最大的根到葉路徑
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        int left = maxRootToLeafSum(root.left);
        int right = maxRootToLeafSum(root.right);

        return root.val + Math.max(left, right);
    }

    // 4. 計算任意兩節點間的最大路徑和（直徑）
    static class MaxPathSumResult {

        int maxSum = Integer.MIN_VALUE;
    }

    public static int maxPathSum(TreeNode root) {
        MaxPathSumResult res = new MaxPathSumResult();
        dfsMaxPath(root, res);
        return res.maxSum;
    }

    private static int dfsMaxPath(TreeNode node, MaxPathSumResult res) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, dfsMaxPath(node.left, res));
        int right = Math.max(0, dfsMaxPath(node.right, res));

        // 更新最大路徑和（通過當前節點的情況）
        res.maxSum = Math.max(res.maxSum, node.val + left + right);

        // 回傳給父節點的最大單邊路徑
        return node.val + Math.max(left, right);
    }

    // 測試範例
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("1. 所有路徑:");
        for (List<Integer> path : allPaths(root)) {
            System.out.println(path);
        }

        System.out.println("2. 是否存在和為 22 的路徑: " + hasPathSum(root, 22));
        System.out.println("3. 最大根到葉節點路徑和: " + maxRootToLeafSum(root));
        System.out.println("4. 樹的最大路徑和（直徑）: " + maxPathSum(root));
    }
}
