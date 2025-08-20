
import java.util.*;

public class M03_TopKConvenience {

    static class Item {

        String name;
        int qty;

        Item(String n, int q) {
            name = n;
            qty = q;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        // Min-Heap，比較依照 qty 升序，若相同則依字典序
        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) {
                    return a.qty - b.qty; // 小的優先

                                }return b.name.compareTo(a.name); // 為了穩定性，可反向
            }
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            Item it = new Item(name, qty);
            pq.offer(it);
            if (pq.size() > K) {
                pq.poll(); // 保持大小 K

                    }}

        List<Item> res = new ArrayList<>(pq);
        // 轉成高到低排序，若數值相同，字典序升序
        res.sort((a, b) -> {
            if (b.qty != a.qty) {
                return b.qty - a.qty;
            }
            return a.name.compareTo(b.name);
        });

        for (Item it : res) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}
