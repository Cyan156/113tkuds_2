
import java.util.*;

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums); // 先排序
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // 避免 i 重複
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // 避免 j 重複
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1, right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    // 用 long 避免整數溢位

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 避免 left 重複
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 避免 right 重複
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;  // 需要更大
                    } else {
                        right--; // 需要更小
                    }
                }
            }
        }
        return result;
    }
}

/*
解題思路：
1. 題目要求：找出所有不同的四元組 [a,b,c,d]，使得 nums[a] + nums[b] + nums[c] + nums[d] == target。
   - 不允許重複組合。
   - 回傳順序不限。

2. 演算法設計：
   (1) 先將 nums 排序，方便去除重複並使用雙指標。
   (2) 用兩層 for 迴圈固定前兩個數 nums[i], nums[j]。
   (3) 接著用雙指標 (left, right) 在剩下的範圍內尋找另外兩個數字。
   (4) 每次計算 sum：
       - 若等於 target → 加入答案，並移動指標跳過重複值。
       - 若 sum < target → left++。
       - 若 sum > target → right--。
   (5) 注意：避免重複解 → i 與 j 要檢查是否與前一個相同；left 與 right 也要略過相同的值。

3. 時間複雜度：
   - 排序：O(n log n)
   - 兩層迴圈 O(n^2)，內層雙指標 O(n)
   - 總體 O(n^3)

4. 空間複雜度：O(1)（不計輸出結果）。
 */
