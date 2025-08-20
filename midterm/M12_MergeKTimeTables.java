
import java.util.*;

public class M12_MergeKTimeTables {

    static class Node implements Comparable<Node> {

        int time, listIdx, elemIdx;

        Node(int t, int l, int e) {
            time = t;
            listIdx = l;
            elemIdx = e;
        }

        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = Integer.parseInt(sc.nextLine().trim());
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(sc.nextLine().trim());
            int[] arr = new int[len];
            String[] parts = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < len; j++) {
                arr[j] = Integer.parseInt(parts[j]);
            }
            lists.add(arr);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            if (lists.get(i).length > 0) {
                pq.offer(new Node(lists.get(i)[0], i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            merged.add(cur.time);
            int nextIdx = cur.elemIdx + 1;
            if (nextIdx < lists.get(cur.listIdx).length) {
                pq.offer(new Node(lists.get(cur.listIdx)[nextIdx], cur.listIdx, nextIdx));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(merged.get(i));
        }
        System.out.println();
    }
}
