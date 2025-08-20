
import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {

        int val;
        char color; // 'B' 或 'R'

        Node(int v, char c) {
            val = v;
            color = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        if (n == 0) {
            System.out.println("RB Valid");
            return;
        }
        String[] parts = sc.nextLine().trim().split("\\s+");
        Node[] tree = new Node[n];

        for (int i = 0; i < n; i++) {
            int idx = i * 2;
            int val = Integer.parseInt(parts[idx]);
            char color;
            if (val == -1) {
                color = 'B'; 
            }else {
                color = parts[idx + 1].charAt(0);
            }
            tree[i] = new Node(val, color);
        }

        // 根節點檢查
        if (tree[0].val != -1 && tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 紅紅檢查
        for (int i = 0; i < n; i++) {
            Node cur = tree[i];
            if (cur.val == -1 || cur.color == 'B') {
                continue;
            }
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i + 2;
            if (leftIdx < n && tree[leftIdx].val != -1 && tree[leftIdx].color == 'R') {
                System.out.println("RedRedViolation at index " + leftIdx);
                return;
            }
            if (rightIdx < n && tree[rightIdx].val != -1 && tree[rightIdx].color == 'R') {
                System.out.println("RedRedViolation at index " + rightIdx);
                return;
            }
        }

        // 黑高檢查
        int bh = checkBlackHeight(tree, 0);
        if (bh == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    static int checkBlackHeight(Node[] tree, int idx) {
        if (idx >= tree.length || tree[idx].val == -1) {
            return 1;
        }
        int left = checkBlackHeight(tree, 2 * idx + 1);
        if (left == -1) {
            return -1;
        }
        int right = checkBlackHeight(tree, 2 * idx + 2);
        if (right == -1) {
            return -1;
        }
        if (left != right) {
            return -1;
        }
        return left + (tree[idx].color == 'B' ? 1 : 0);
    }
}
