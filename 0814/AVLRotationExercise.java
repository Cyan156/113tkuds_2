public class AVLRotationExercise {

    // 節點類別
    static class AVLNode {
        int data;
        AVLNode left, right;
        int height;

        public AVLNode(int data) {
            this.data = data;
            this.height = 1;
        }

        // 更新高度
        public void updateHeight() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            this.height = Math.max(leftHeight, rightHeight) + 1;
        }

        // 計算平衡因子
        public int getBalance() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            return leftHeight - rightHeight;
        }
    }

    // 右旋
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 左旋
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // 左右旋
    public static AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 右左旋
    public static AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // 中序列印
    public static void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    // main 測試
    public static void main(String[] args) {
        System.out.println("=== AVL 旋轉操作測試 ===");

        // 測試右旋 (Left-Left)
        AVLNode root1 = new AVLNode(30);
        root1.left = new AVLNode(20);
        root1.left.left = new AVLNode(10);
        root1.updateHeight();
        System.out.println("\n右旋前 (LL): ");
        printInOrder(root1);

        root1 = rightRotate(root1);
        System.out.println("\n右旋後 (LL): ");
        printInOrder(root1);

        // 測試左旋 (Right-Right)
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);
        root2.updateHeight();
        System.out.println("\n\n左旋前 (RR): ");
        printInOrder(root2);

        root2 = leftRotate(root2);
        System.out.println("\n左旋後 (RR): ");
        printInOrder(root2);

        // 測試左右旋 (Left-Right)
        AVLNode root3 = new AVLNode(30);
        root3.left = new AVLNode(10);
        root3.left.right = new AVLNode(20);
        root3.updateHeight();
        System.out.println("\n\n左右旋前 (LR): ");
        printInOrder(root3);

        root3 = leftRightRotate(root3);
        System.out.println("\n左右旋後 (LR): ");
        printInOrder(root3);

        // 測試右左旋 (Right-Left)
        AVLNode root4 = new AVLNode(10);
        root4.right = new AVLNode(30);
        root4.right.left = new AVLNode(20);
        root4.updateHeight();
        System.out.println("\n\n右左旋前 (RL): ");
        printInOrder(root4);

        root4 = rightLeftRotate(root4);
        System.out.println("\n右左旋後 (RL): ");
        printInOrder(root4);
    }
}
