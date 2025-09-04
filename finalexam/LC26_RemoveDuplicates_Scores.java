
import java.util.*;

public class LC26_RemoveDuplicates_Scores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int len = removeDuplicates(nums);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i]);
            if (i != len - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static int removeDuplicates(int[] nums) {
        int write = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }
}
