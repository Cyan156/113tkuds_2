public class AVLRotations {

    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x; // 新的根節點
    }

    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y; // 新的根節點
    }

    // main 測試方法
    public static void main(String[] args) {
        // 建立簡單樹
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.right = new AVLNode(40);
        root.left.left = new AVLNode(10);

        // 更新高度
        root.left.left.updateHeight();
        root.left.updateHeight();
        root.right.updateHeight();
        root.updateHeight();

        System.out.println("旋轉前根節點: " + root.data + ", 高度: " + root.height);

        // 右旋
        root = rightRotate(root);
        System.out.println("右旋後根節點: " + root.data + ", 高度: " + root.height);

        // 左旋
        root = leftRotate(root);
        System.out.println("左旋後根節點: " + root.data + ", 高度: " + root.height);
    }
}
