
import java.util.*;

public class MeetingRoomScheduler {

    // Part 1: 計算最少會議室數量
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 按開始時間排序

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 儲存每間會議室的結束時間
        minHeap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            // 如果最早結束的會議室空出來，就重用
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }
        return minHeap.size();
    }

    // Part 2: 只有 N 間會議室，最大化總會議時間
    public int maxMeetingTimeWithNRooms(int[][] intervals, int N) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // 按結束時間排序

        // 每間會議室的最後結束時間
        int[] roomEndTime = new int[N];
        Arrays.fill(roomEndTime, -1);

        int totalTime = 0;

        for (int[] meeting : intervals) {
            for (int i = 0; i < N; i++) {
                if (roomEndTime[i] <= meeting[0]) { // 會議室空閒
                    totalTime += (meeting[1] - meeting[0]);
                    roomEndTime[i] = meeting[1];
                    break;
                }
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        MeetingRoomScheduler scheduler = new MeetingRoomScheduler();

        // 測試案例 1
        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(scheduler.minMeetingRooms(meetings1)); // 2

        // 測試案例 2
        int[][] meetings2 = {{9, 10}, {4, 9}, {4, 17}};
        System.out.println(scheduler.minMeetingRooms(meetings2)); // 2

        // 測試案例 3
        int[][] meetings3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println(scheduler.minMeetingRooms(meetings3)); // 2

        // 測試案例 4 (只有 1 間會議室)
        int[][] meetings4 = {{1, 4}, {2, 3}, {4, 6}};
        System.out.println(scheduler.maxMeetingTimeWithNRooms(meetings4, 1)); // 5
    }
}
