
import java.util.*;

// 任務類別
class Task implements Comparable<Task> {

    String name;
    int priority;
    long timestamp; // 用來確保同優先級時先加入的先出

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Task other) {
        // 優先級高的排前面
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }
        // 優先級相同時，先加入的排前面
        return Long.compare(this.timestamp, other.timestamp);
    }

    @Override
    public String toString() {
        return "[" + name + ", priority=" + priority + "]";
    }
}

public class PriorityQueueWithHeap {

    private PriorityQueue<Task> queue;
    private long counter; // 時間戳記計數器

    public PriorityQueueWithHeap() {
        queue = new PriorityQueue<>();
        counter = 0;
    }

    // 添加任務
    public void addTask(String name, int priority) {
        queue.add(new Task(name, priority, counter++));
    }

    // 執行下一個任務
    public Task executeNext() {
        if (queue.isEmpty()) {
            System.out.println("沒有任務可執行");
            return null;
        }
        Task next = queue.poll();
        System.out.println("執行任務: " + next.name);
        return next;
    }

    // 查看下一個要執行的任務
    public Task peek() {
        return queue.peek();
    }

    // 修改任務優先級（需要重建 heap）
    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        boolean found = false;

        while (!queue.isEmpty()) {
            Task task = queue.poll();
            if (task.name.equals(name) && !found) {
                task.priority = newPriority;
                task.timestamp = counter++;
                found = true;
            }
            temp.add(task);
        }

        queue.addAll(temp);

        if (!found) {
            System.out.println("找不到任務: " + name);
        }
    }

    // 測試
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("下一個任務: " + pq.peek()); // 緊急修復

        pq.executeNext(); // 緊急修復
        pq.executeNext(); // 更新
        pq.executeNext(); // 備份

        pq.addTask("資料同步", 2);
        pq.addTask("清理日誌", 4);
        pq.changePriority("資料同步", 6); // 提升優先級
        System.out.println("下一個任務: " + pq.peek()); // 資料同步

        pq.executeNext(); // 資料同步
        pq.executeNext(); // 清理日誌
    }
}
