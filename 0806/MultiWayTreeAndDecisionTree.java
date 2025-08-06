// 檔名：MultiWayTreeAndDecisionTree.java

import java.util.*;

class MultiWayTreeNode {

    String value;
    List<MultiWayTreeNode> children;

    MultiWayTreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    void addChild(MultiWayTreeNode child) {
        children.add(child);
    }
}

public class MultiWayTreeAndDecisionTree {

    // 深度優先搜尋（DFS）
    public static void dfs(MultiWayTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        for (MultiWayTreeNode child : node.children) {
            dfs(child);
        }
    }

    // 廣度優先搜尋（BFS）
    public static void bfs(MultiWayTreeNode root) {
        if (root == null) {
            return;
        }
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode current = queue.poll();
            System.out.print(current.value + " ");
            queue.addAll(current.children);
        }
    }

    // 計算樹的高度
    public static int getHeight(MultiWayTreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxHeight = 0;
        for (MultiWayTreeNode child : node.children) {
            maxHeight = Math.max(maxHeight, getHeight(child));
        }
        return maxHeight + 1;
    }

    // 列出每個節點的度數（子節點數）
    public static void printDegrees(MultiWayTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println("節點 " + node.value + " 的度數為: " + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printDegrees(child);
        }
    }

    // 模擬簡單的決策樹（猜數字）
    public static void runGuessingGame() {
        MultiWayTreeNode root = new MultiWayTreeNode("你猜的是偶數還是奇數？");

        MultiWayTreeNode even = new MultiWayTreeNode("偶數");
        MultiWayTreeNode odd = new MultiWayTreeNode("奇數");

        even.addChild(new MultiWayTreeNode("是 2 嗎？"));
        even.addChild(new MultiWayTreeNode("是 4 嗎？"));

        odd.addChild(new MultiWayTreeNode("是 1 嗎？"));
        odd.addChild(new MultiWayTreeNode("是 3 嗎？"));

        root.addChild(even);
        root.addChild(odd);

        System.out.println("== 猜數字遊戲（樹狀決策）==");
        dfs(root);
        System.out.println();
    }

    // 測試範例
    public static void main(String[] args) {
        // 建立多路樹
        MultiWayTreeNode root = new MultiWayTreeNode("A");
        MultiWayTreeNode b = new MultiWayTreeNode("B");
        MultiWayTreeNode c = new MultiWayTreeNode("C");
        MultiWayTreeNode d = new MultiWayTreeNode("D");
        MultiWayTreeNode e = new MultiWayTreeNode("E");
        MultiWayTreeNode f = new MultiWayTreeNode("F");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);

        System.out.println("DFS 遍歷：");
        dfs(root);
        System.out.println("\n\nBFS 遍歷：");
        bfs(root);

        System.out.println("\n\n樹的高度：" + getHeight(root));
        System.out.println("\n節點度數：");
        printDegrees(root);

        System.out.println("\n模擬決策樹遊戲：");
        runGuessingGame();
    }
}
