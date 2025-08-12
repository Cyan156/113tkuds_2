
import java.util.*;

public class StockMaximizer {

    public int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K <= 0) {
            return 0;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int n = prices.length;
        int buy = 0, sell = 0;

        while (buy < n) {
            // 找到下一個波谷（買入點）
            while (buy < n - 1 && prices[buy + 1] <= prices[buy]) {
                buy++;
            }
            sell = buy + 1;

            // 找到對應的波峰（賣出點）
            while (sell < n && (sell == n - 1 || prices[sell + 1] >= prices[sell])) {
                sell++;
            }

            // 有獲利才放進 heap
            if (sell < n && prices[sell - 1] > prices[buy]) {
                maxHeap.offer(prices[sell - 1] - prices[buy]);
            }
            buy = sell;
        }

        // 取 K 筆最大利潤
        int profit = 0;
        while (K > 0 && !maxHeap.isEmpty()) {
            profit += maxHeap.poll();
            K--;
        }
        return profit;
    }

    public static void main(String[] args) {
        StockMaximizer sm = new StockMaximizer();

        int[] prices1 = {2, 4, 1};
        System.out.println(sm.maxProfit(prices1, 2)); // 2

        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println(sm.maxProfit(prices2, 2)); // 7

        int[] prices3 = {1, 2, 3, 4, 5};
        System.out.println(sm.maxProfit(prices3, 2)); // 4
    }
}
