
import java.util.*;

public class LC34_SearchRange_DelaySpan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

    static int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        int right = lowerBound(nums, target + 1) - 1;
        if (left <= right && left < nums.length && nums[left] == target) {
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }

    static int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < target) {
                l = m + 1; 
            }else {
                r = m;
            }
        }
        return l;
    }
}
