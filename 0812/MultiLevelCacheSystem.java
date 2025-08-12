
import java.util.*;

public class MultiLevelCacheSystem {

    enum Level {
        L1, L2, L3
    }

    class CacheItem {

        int key;
        String value;
        int frequency;
        long lastAccessTime;
        Level level;

        CacheItem(int key, String value, Level level) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
            this.lastAccessTime = System.nanoTime();
            this.level = level;
        }

        double score() {
            // 依頻率、時間戳記、成本算分數（頻率正向，時間負向，成本負向）
            int cost = costMap.get(level);
            // 權重可以調整
            double wFreq = 1.0, wTime = 0.000000001, wCost = 10.0;
            return frequency * wFreq - lastAccessTime * wTime - cost * wCost;
        }

        void accessed() {
            frequency++;
            lastAccessTime = System.nanoTime();
        }
    }

    private final Map<Integer, CacheItem> cacheMap = new HashMap<>();

    private final Map<Level, Integer> capacityMap = Map.of(Level.L1, 2, Level.L2, 5, Level.L3, 10);
    private final Map<Level, Integer> costMap = Map.of(Level.L1, 1, Level.L2, 3, Level.L3, 10);

    private final Map<Level, PriorityQueue<CacheItem>> heaps = new HashMap<>();

    public MultiLevelCacheSystem() {
        for (Level l : Level.values()) {
            heaps.put(l, new PriorityQueue<>(
                    (a, b) -> Double.compare(a.score(), b.score()) // Min Heap，score低者優先被踢
            ));
        }
    }

    public String get(int key) {
        CacheItem item = cacheMap.get(key);
        if (item == null) {
            return null;
        }

        // 更新存取紀錄
        updateItemAccess(item);

        return item.value;
    }

    public void put(int key, String value) {
        CacheItem item = cacheMap.get(key);
        if (item != null) {
            item.value = value;
            updateItemAccess(item);
        } else {
            // 新增預設放L3
            item = new CacheItem(key, value, Level.L3);
            cacheMap.put(key, item);
            heaps.get(Level.L3).offer(item);
            balanceLevels(Level.L3);
        }
    }

    private void updateItemAccess(CacheItem item) {
        // 移除舊節點再加回heap更新排序
        PriorityQueue<CacheItem> heap = heaps.get(item.level);
        heap.remove(item);
        item.accessed();

        // 先放回heap
        heap.offer(item);

        // 嘗試提升層級
        promote(item);
    }

    private void promote(CacheItem item) {
        if (item.level == Level.L1) {
            return;
        }

        Level higherLevel = item.level == Level.L3 ? Level.L2 : Level.L1;
        PriorityQueue<CacheItem> higherHeap = heaps.get(higherLevel);
        PriorityQueue<CacheItem> currentHeap = heaps.get(item.level);

        // 比較分數，如果升級後比目前層最低分更高，則升級
        if (higherHeap.size() < capacityMap.get(higherLevel)
                || item.score() > higherHeap.peek().score()) {
            currentHeap.remove(item);
            item.level = higherLevel;
            higherHeap.offer(item);

            balanceLevels(higherLevel);
            balanceLevels(item.level); // balance 剛離開的層級
        }
    }

    private void balanceLevels(Level level) {
        PriorityQueue<CacheItem> heap = heaps.get(level);
        int capacity = capacityMap.get(level);

        while (heap.size() > capacity) {
            CacheItem evicted = heap.poll();
            // 移到下一層（往下）
            if (level == Level.L3) {
                // L3滿了只能丟掉最差資料
                cacheMap.remove(evicted.key);
            } else {
                Level lowerLevel = level == Level.L1 ? Level.L2 : Level.L3;
                evicted.level = lowerLevel;
                heaps.get(lowerLevel).offer(evicted);
                balanceLevels(lowerLevel);
            }
        }
    }

    // 測試輸出用（顯示三層內容）
    public void printCache() {
        for (Level l : Level.values()) {
            System.out.print(l + ": ");
            PriorityQueue<CacheItem> heap = heaps.get(l);
            List<CacheItem> list = new ArrayList<>(heap);
            list.sort((a, b) -> Double.compare(b.score(), a.score()));
            for (CacheItem ci : list) {
                System.out.print("[" + ci.key + "=" + ci.value + "(f:" + ci.frequency + ")] ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCache();

        System.out.println("Access 1 twice and 2 once");
        cache.get(1);
        Thread.sleep(1);
        cache.get(1);
        Thread.sleep(1);
        cache.get(2);
        cache.printCache();

        System.out.println("Put 4,5,6");
        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printCache();
    }
}
