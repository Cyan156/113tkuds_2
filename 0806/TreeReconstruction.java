
public class TreeReconstruction {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe) {
            return null;
        }
        int rootVal = pre[ps];
        int index = is;
        while (in[index] != rootVal) {
            index++;
        }
        int leftSize = index - is;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, index - 1);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, index + 1, ie);
        return root;
    }

    static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe) {
            return null;
        }
        int rootVal = post[pe];
        int index = is;
        while (in[index] != rootVal) {
            index++;
        }
        int leftSize = index - is;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, index - 1);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, index + 1, ie);
        return root;
    }

    static TreeNode buildFromLevelOrder(int[] levelOrder) {
        if (levelOrder.length == 0) {
            return null;
        }
        TreeNode[] nodes = new TreeNode[levelOrder.length];
        for (int i = 0; i < levelOrder.length; i++) {
            nodes[i] = new TreeNode(levelOrder[i]);
        }
        for (int i = 0; i < levelOrder.length; i++) {
            if (2 * i + 1 < levelOrder.length) {
                nodes[i].left = nodes[2 * i + 1];
            }
            if (2 * i + 2 < levelOrder.length) {
                nodes[i].right = nodes[2 * i + 2];
            }
        }
        return nodes[0];
    }

    static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val) {
            return false;
        }
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelorder = {3, 9, 20, -1, -1, 15, 7}; // -1 表示空位不處理

        TreeNode t1 = buildFromPreIn(preorder, inorder);
        TreeNode t2 = buildFromPostIn(postorder, inorder);
        TreeNode t3 = buildFromLevelOrder(new int[]{1, 2, 3, 4, 5, 6, 7});

        printInorder(t1);
        System.out.println();
        printInorder(t2);
        System.out.println();
        printInorder(t3);
    }
}
