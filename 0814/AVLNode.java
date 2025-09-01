public class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }

    // 計算平衡因子
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    // 更新節點高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    // main 測試方法
    public static void main(String[] args) {
        // 建立節點
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.right = new AVLNode(40);

        // 更新高度
        root.left.updateHeight();
        root.right.updateHeight();
        root.updateHeight();

        // 輸出節點資訊
        System.out.println("根節點: " + root.data + ", 高度: " + root.height + ", 平衡因子: " + root.getBalance());
        System.out.println("左子節點: " + root.left.data + ", 高度: " + root.left.height + ", 平衡因子: " + root.left.getBalance());
        System.out.println("右子節點: " + root.right.data + ", 高度: " + root.right.height + ", 平衡因子: " + root.right.getBalance());
    }
}
