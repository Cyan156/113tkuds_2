public class AVLTree {
    private AVLNode root;

    // 插入節點
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) node.left = insertNode(node.left, data);
        else if (data > node.data) node.right = insertNode(node.right, data);
        else return node;

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && data < node.left.data) return AVLRotations.rightRotate(node);
        if (balance < -1 && data > node.right.data) return AVLRotations.leftRotate(node);
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

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

    // 刪除節點
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;

        if (data < node.data) node.left = deleteNode(node.left, data);
        else if (data > node.data) node.right = deleteNode(node.right, data);
        else {
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) node = null;
                else {
                    node.data = temp.data;
                    node.left = temp.left;
                    node.right = temp.right;
                    node.height = temp.height;
                }
            } else {
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        if (node == null) return node;

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && node.left.getBalance() >= 0) return AVLRotations.rightRotate(node);
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        if (balance < -1 && node.right.getBalance() <= 0) return AVLRotations.leftRotate(node);
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 列印樹狀結構
    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    // main 測試方法
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("=== 插入節點 ===");
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) {
            System.out.println("插入: " + v);
            tree.insert(v);
            System.out.print("當前樹: ");
            tree.printTree();
        }

        System.out.println("\n=== 搜尋節點 ===");
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 35: " + tree.search(35));

        System.out.println("\n=== 刪除節點 ===");
        int[] deleteValues = {10, 40};
        for (int v : deleteValues) {
            System.out.println("刪除: " + v);
            tree.delete(v);
            System.out.print("當前樹: ");
            tree.printTree();
        }
    }
}
