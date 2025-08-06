
public class TreeNodeBasicDemo {

    static class TreeNode {

        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void displayNode() {
            System.out.println("節點值: " + data);
            System.out.println("左子節點: " + (left != null ? left.data : "null"));
            System.out.println("右子節點: " + (right != null ? right.data : "null"));
        }
    }

    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static int countLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);

        System.out.println("=== 樹的基本資訊 ===");
        System.out.println("根節點資訊:");
        root.displayNode();

        System.out.println("\n=== 樹的統計資訊 ===");
        System.out.println("樹的高度: " + getHeight(root));
        System.out.println("節點總數: " + countNodes(root));
        System.out.println("葉節點數: " + countLeaves(root));

        System.out.println("\n=== 各節點詳細資訊 ===");
        System.out.println("左子節點 (5) 資訊:");
        root.left.displayNode();

        System.out.println("\n右子節點 (15) 資訊:");
        root.right.displayNode();
    }
}
