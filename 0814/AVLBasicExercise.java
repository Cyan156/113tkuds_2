public class AVLBasicExercise {

    // 節點類別
    static class AVLNode {
        int data;
        AVLNode left, right;

        public AVLNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // 根節點
    private AVLNode root;

    // 插入節點（標準 BST）
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) node.left = insertNode(node.left, data);
        else if (data > node.data) node.right = insertNode(node.right, data);

        return node;
    }

    // 搜尋節點
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        return data < node.data ? searchNode(node.left, data) : searchNode(node.right, data);
    }

    // 計算樹高度
    public int height() {
        return getHeight(root);
    }

    private int getHeight(AVLNode node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // 驗證是否為 AVL 樹
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(AVLNode node) {
        if (node == null) return 0;

        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);

        if (leftHeight == -1 || rightHeight == -1) return -1;

        int balance = leftHeight - rightHeight;
        if (balance < -1 || balance > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 中序列印（方便查看樹結構）
    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }

    // main 測試
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        System.out.println("=== 插入節點 ===");
        int[] values = {10, 20, 30, 25, 15};
        for (int v : values) {
            tree.insert(v);
            System.out.print("當前樹: ");
            tree.printInOrder();
        }

        System.out.println("\n=== 搜尋節點 ===");
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 35: " + tree.search(35));

        System.out.println("\n樹高度: " + tree.height());
        System.out.println("是否為有效 AVL 樹: " + tree.isValidAVL());
    }
}
