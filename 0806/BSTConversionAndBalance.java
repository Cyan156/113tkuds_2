

public class BSTConversionAndBalance {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ===== 1. 將BST轉換為排序的雙向鏈結串列 =====
    static class DoublyListNode {

        int val;
        DoublyListNode prev, next;

        DoublyListNode(int val) {
            this.val = val;
        }
    }

    TreeNode prevNode = null;
    DoublyListNode head = null;

    public DoublyListNode bstToDoublyList(TreeNode root) {
        prevNode = null;
        head = null;
        inorderToDoublyList(root);
        return head;
    }

    private void inorderToDoublyList(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderToDoublyList(node.left);

        DoublyListNode curr = new DoublyListNode(node.val);
        if (prevNode == null) {
            head = curr;
        } else {
            DoublyListNode prev = head;
            while (prev.next != null) {
                prev = prev.next;
            }
            prev.next = curr;
            curr.prev = prev;
        }

        inorderToDoublyList(node.right);
    }

    // ===== 2. 將排序陣列轉換為平衡的BST =====
    public TreeNode sortedArrayToBST(int[] nums) {
        return arrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode arrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = arrayToBST(nums, left, mid - 1);
        root.right = arrayToBST(nums, mid + 1, right);
        return root;
    }

    // ===== 3. 檢查BST是否平衡，並計算平衡因子 =====
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private int checkBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = checkBalance(node.left);
        int right = checkBalance(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    // ===== 4. 將BST中每個節點的值改為所有大於等於該節點值的總和 =====
    private int sum = 0;

    public void convertBSTToGreaterTree(TreeNode root) {
        sum = 0;
        reverseInorder(root);
    }

    private void reverseInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        reverseInorder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInorder(node.left);
    }

    // ===== 測試用 main 方法 =====
    public static void main(String[] args) {
        BSTConversionAndBalance demo = new BSTConversionAndBalance();

        // 測試：排序陣列轉平衡BST
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = demo.sortedArrayToBST(sortedArr);

        // 檢查是否平衡
        System.out.println("是否平衡：" + demo.isBalanced(bst));

        // 轉換為 greater tree
        demo.convertBSTToGreaterTree(bst);
        printInorder(bst);  // 測試中序輸出應為轉換後的累加值

        // BST 轉換為雙向鏈結串列
        DoublyListNode listHead = demo.bstToDoublyList(bst);
        System.out.print("雙向鏈結串列：");
        while (listHead != null) {
            System.out.print(listHead.val + " ");
            listHead = listHead.next;
        }
    }

    // 工具函數：中序印出 BST
    static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
}
