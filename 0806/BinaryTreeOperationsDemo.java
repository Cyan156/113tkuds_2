
public class BinaryTreeOperationsDemo {

    static class TreeNode {

        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static boolean search(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }
        return search(root.left, target) || search(root.right, target);
    }

    public static int sumAllNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.data + sumAllNodes(root.left) + sumAllNodes(root.right);
    }

    public static int findMax(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("空樹沒有最大值");
        }
        int max = root.data;
        if (root.left != null) {
            max = Math.max(max, findMax(root.left));
        }
        if (root.right != null) {
            max = Math.max(max, findMax(root.right));
        }
        return max;
    }

    public static int findMin(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("空樹沒有最小值");
        }
        int min = root.data;
        if (root.left != null) {
            min = Math.min(min, findMin(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, findMin(root.right));
        }
        return min;
    }

    public static boolean isSameTree(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        return (tree1.data == tree2.data)
                && isSameTree(tree1.left, tree2.left)
                && isSameTree(tree1.right, tree2.right);
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return (left.data == right.data)
                && isSymmetricHelper(left.left, right.right)
                && isSymmetricHelper(left.right, right.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);

        System.out.println("=== 二元樹基本操作 ===");
        System.out.println("搜尋 7: " + search(root, 7));
        System.out.println("搜尋 20: " + search(root, 20));
        System.out.println("所有節點總和: " + sumAllNodes(root));
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));

        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(15);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(7);
        root2.right.left = new TreeNode(12);
        root2.right.right = new TreeNode(18);

        System.out.println("\n=== 樹的比較 ===");
        System.out.println("兩棵樹是否相同: " + isSameTree(root, root2));

        TreeNode symmetricRoot = new TreeNode(1);
        symmetricRoot.left = new TreeNode(2);
        symmetricRoot.right = new TreeNode(2);
        symmetricRoot.left.left = new TreeNode(3);
        symmetricRoot.left.right = new TreeNode(4);
        symmetricRoot.right.left = new TreeNode(4);
        symmetricRoot.right.right = new TreeNode(3);

        System.out.println("對稱樹測試: " + isSymmetric(symmetricRoot));
        System.out.println("原樹是否對稱: " + isSymmetric(root));
    }
}
