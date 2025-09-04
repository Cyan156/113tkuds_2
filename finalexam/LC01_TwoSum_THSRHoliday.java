
import java.util.*;

public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 target
        int n = sc.nextInt();
        long target = sc.nextLong();

        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        // HashMap: key = 需要的數字, value = 對應索引
        Map<Long, Integer> need = new HashMap<>();

        int idx1 = -1, idx2 = -1;

        for (int i = 0; i < n; i++) {
            long x = seats[i];

            // 是否有人在等這個 x
            if (need.containsKey(x)) {
                idx1 = need.get(x);
                idx2 = i;
                break;
            }

            // 記錄「需要 target - x」
            long remain = target - x;
            need.put(remain, i);
        }

        System.out.println(idx1 + " " + idx2);
    }
}
