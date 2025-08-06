
import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {

        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 1. 將每一層的節點分別儲存在不同的List中
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.data);

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            res.add(level);
        }

        return res;
    }

    // 2. 之字形層序走訪（奇數層從左到右，偶數層從右到左）
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (leftToRight) {
                    level.addLast(cur.data);
                } else {
                    level.addFirst(cur.data);
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            res.add(new ArrayList<>(level));
            leftToRight = !leftToRight;
        }

        return res;
    }

    // 3. 只列印每一層的最後一個節點
    public static List<Integer> rightMostNodes(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastValue = 0;

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                lastValue = cur.data;

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(lastValue);
        }

        return res;
    }

    // 4. 垂直層序走訪（按照節點的水平位置分組）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // key: column index, value: list of node values in that column
        Map<Integer, List<Integer>> columnTable = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> columnQueue = new LinkedList<>();

        queue.offer(root);
        columnQueue.offer(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = columnQueue.poll();

            columnTable.putIfAbsent(col, new ArrayList<>());
            columnTable.get(col).add(node.data);

            if (node.left != null) {
                queue.offer(node.left);
                columnQueue.offer(col - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                columnQueue.offer(col + 1);
            }
        }

        for (List<Integer> colNodes : columnTable.values()) {
            res.add(colNodes);
        }

        return res;
    }

    // 測試
    public static void main(String[] args) {
        /*
                 1
               /   \
              2     3
             / \   / \
            4   5 6   7
                   \
                    8
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        System.out.println("1. 每層節點：");
        List<List<Integer>> levelRes = levelOrder(root);
        for (List<Integer> level : levelRes) {
            System.out.println(level);
        }

        System.out.println("\n2. 之字形層序走訪：");
        List<List<Integer>> zigzagRes = zigzagLevelOrder(root);
        for (List<Integer> level : zigzagRes) {
            System.out.println(level);
        }

        System.out.println("\n3. 每層最後一個節點：");
        List<Integer> rightMost = rightMostNodes(root);
        System.out.println(rightMost);

        System.out.println("\n4. 垂直層序走訪：");
        List<List<Integer>> verticalRes = verticalOrder(root);
        for (List<Integer> col : verticalRes) {
            System.out.println(col);
        }
    }
}
