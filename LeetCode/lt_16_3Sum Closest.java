
import java.util.Arrays;

class Solution {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序，方便使用雙指標
        int n = nums.length;
        // 初始化最接近值，用前三個數字的總和
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果更接近 target，就更新 closestSum
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    left++;   // 總和太小，移動左指標
                } else if (sum > target) {
                    right--;  // 總和太大，移動右指標
                } else {
                    return target; // 剛好命中，直接回傳
                }
            }
        }
        return closestSum;
    }
}

/*
解題思路：
1. 將陣列排序，讓數字由小到大排列。
2. 用 for 迴圈固定一個數 nums[i]，其餘兩個數用雙指標 (left, right) 逼近。
3. 每次計算三數和 sum：
   - 若 |sum - target| < |closestSum - target|，更新 closestSum。
   - 若 sum < target → left++ (嘗試讓總和變大)。
   - 若 sum > target → right-- (嘗試讓總和變小)。
   - 若 sum == target → 直接回傳 target，因為這已經是最接近的值。
4. 遍歷所有可能組合後，回傳最接近的總和 closestSum。

時間複雜度：O(n^2) → 排序 O(n log n) + 雙指標遍歷 O(n^2)
空間複雜度：O(1) → 只使用常數額外變數
 */
