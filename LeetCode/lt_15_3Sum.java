
import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序，方便用雙指針避免重複

        for (int i = 0; i < nums.length - 2; i++) {
            // 若數字相同，跳過避免重複解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 移動 left，避免重複
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 移動 right，避免重複
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 總和太小，移動左指針增加和
                } else {
                    right--; // 總和太大，移動右指針減少和
                }
            }
        }

        return result;
    }
}

/*
解題邏輯說明：
1. 問題要求：找出所有三個數字相加為 0 的不重複組合。
2. 解法：
   - 先排序 nums，使得相同元素相鄰，方便跳過重複解。
   - 固定一個數字 nums[i]，然後用「雙指針」找另外兩個數字。
     - left 指向 i+1，right 指向最後。
     - 計算 sum = nums[i] + nums[left] + nums[right]。
       * 若 sum == 0 → 加入結果，並跳過重複的 left/right。
       * 若 sum < 0 → 左指針右移 (增加總和)。
       * 若 sum > 0 → 右指針左移 (減少總和)。
   - 外層迴圈時，如果 nums[i] == nums[i-1]，直接跳過避免重複。
3. 範例：
   nums = [-1,0,1,2,-1,-4]
   排序後 = [-4,-1,-1,0,1,2]
   → 解答 = [[-1,-1,2],[-1,0,1]]
4. 時間複雜度：O(n^2)，外層迴圈 O(n)，內層雙指針 O(n)。
5. 空間複雜度：O(1)，額外空間僅用於結果儲存。
 */
