
public class TreeMirrorAndSymmetry {

    static class TreeNode {

        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 判斷是否對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.data != t2.data) {
            return false;
        }
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    // 產生鏡像樹（在原樹上修改）
    public static void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    // 判斷兩棵樹是否互為鏡像
    public static boolean areMirrors(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.data != t2.data) {
            return false;
        }
        return areMirrors(t1.left, t2.right) && areMirrors(t1.right, t2.left);
    }

    // 判斷t2是否為t1的子樹
    public static boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (isSameTree(t1, t2)) {
            return true;
        }
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    private static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.data != t.data) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    // 輔助：中序走訪列印
    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root1));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        System.out.print("原樹中序: ");
        inorder(root2);
        System.out.println();

        mirror(root2);
        System.out.print("鏡像樹中序: ");
        inorder(root2);
        System.out.println();

        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(3);
        root3.right = new TreeNode(2);

        System.out.println("兩樹是否互為鏡像: " + areMirrors(root2, root3));

        TreeNode bigTree = new TreeNode(5);
        bigTree.left = new TreeNode(3);
        bigTree.right = new TreeNode(8);
        bigTree.left.left = new TreeNode(2);
        bigTree.left.right = new TreeNode(4);
        bigTree.right.left = new TreeNode(7);
        bigTree.right.right = new TreeNode(9);

        TreeNode smallTree = new TreeNode(3);
        smallTree.left = new TreeNode(2);
        smallTree.right = new TreeNode(4);

        System.out.println("smallTree 是 bigTree 的子樹嗎？ " + isSubtree(bigTree, smallTree));
    }
}
