
import java.util.*;

public class BSTKthElement {

    static class TreeNode {

        int data;
        TreeNode left, right;
        int size; // 子樹節點數（含自己）

        TreeNode(int data) {
            this.data = data;
            this.size = 1;
        }
    }

    static class BST {

        TreeNode root;

        // 更新節點大小（子樹節點數）
        private int size(TreeNode node) {
            return node == null ? 0 : node.size;
        }

        private void updateSize(TreeNode node) {
            if (node != null) {
                node.size = 1 + size(node.left) + size(node.right);
            }
        }

        // 插入節點並更新大小
        public void insert(int val) {
            root = insert(root, val);
        }

        private TreeNode insert(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }

            if (val < node.data) {
                node.left = insert(node.left, val);
            } else if (val > node.data) {
                node.right = insert(node.right, val);
            }
            updateSize(node);
            return node;
        }

        // 刪除節點並更新大小
        public void delete(int val) {
            root = delete(root, val);
        }

        private TreeNode delete(TreeNode node, int val) {
            if (node == null) {
                return null;
            }

            if (val < node.data) {
                node.left = delete(node.left, val);
            } else if (val > node.data) {
                node.right = delete(node.right, val);
            } else { // 找到節點刪除
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }

                // 找右子樹最小節點替代
                TreeNode minNode = minNode(node.right);
                node.data = minNode.data;
                node.right = delete(node.right, minNode.data);
            }
            updateSize(node);
            return node;
        }

        private TreeNode minNode(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // 找第k小元素
        public int kthSmallest(int k) {
            if (k <= 0 || k > size(root)) {
                throw new IllegalArgumentException("k不合法");
            }
            return kthSmallest(root, k);
        }

        private int kthSmallest(TreeNode node, int k) {
            int leftSize = size(node.left);
            if (k == leftSize + 1) {
                return node.data; 
            }else if (k <= leftSize) {
                return kthSmallest(node.left, k); 
            }else {
                return kthSmallest(node.right, k - leftSize - 1);
            }
        }

        // 找第k大元素 (第k大 = 第 (size - k + 1) 小)
        public int kthLargest(int k) {
            int n = size(root);
            if (k <= 0 || k > n) {
                throw new IllegalArgumentException("k不合法");
            }
            return kthSmallest(root, n - k + 1);
        }

        // 找第k小到第j小之間所有元素（包含k和j）
        public List<Integer> rangeKthElements(int k, int j) {
            if (k > j) {
                throw new IllegalArgumentException("k必須 <= j");
            }
            if (k <= 0 || j > size(root)) {
                throw new IllegalArgumentException("k或j不合法");
            }

            List<Integer> result = new ArrayList<>();
            inorderRange(root, result, k, j);
            return result;
        }

        private int count = 0;

        private void inorderRange(TreeNode node, List<Integer> result, int k, int j) {
            if (node == null) {
                return;
            }
            inorderRange(node.left, result, k, j);
            count++;
            if (count >= k && count <= j) {
                result.add(node.data);
            }
            if (count > j) {
                return;
            }
            inorderRange(node.right, result, k, j);
        }

        // 中序列印
        public void inorderPrint() {
            inorderPrint(root);
            System.out.println();
        }

        private void inorderPrint(TreeNode node) {
            if (node == null) {
                return;
            }
            inorderPrint(node.left);
            System.out.print(node.data + " ");
            inorderPrint(node.right);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            bst.insert(v);
        }

        System.out.print("BST中序：");
        bst.inorderPrint();

        System.out.println("第3小元素: " + bst.kthSmallest(3)); // 應該是 40
        System.out.println("第2大元素: " + bst.kthLargest(2));  // 應該是 70

        System.out.println("第2小到第5小元素: " + bst.rangeKthElements(2, 5)); // [30,40,50,60]

        System.out.println("\n插入55後:");
        bst.insert(55);
        bst.inorderPrint();

        System.out.println("刪除30後:");
        bst.delete(30);
        bst.inorderPrint();

        System.out.println("第3小元素: " + bst.kthSmallest(3));
    }
}
