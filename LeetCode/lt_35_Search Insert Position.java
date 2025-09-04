
class Solution {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到 target
            } else if (nums[mid] < target) {
                left = mid + 1; // target 在右半邊
            } else {
                right = mid - 1; // target 在左半邊
            }
        }

        // 如果沒找到 target，left 就是應插入的位置
        return left;
    }
}

/*
解題思路：
1. 題目要求 O(log n)，所以使用二分搜尋。
2. 每次取中間元素 mid，比較 nums[mid] 與 target：
   - 相等：直接返回 mid。
   - nums[mid] < target：target 在右半邊，left = mid + 1。
   - nums[mid] > target：target 在左半邊，right = mid - 1。
3. 當 left > right 時，target 不存在於陣列中。
   - 此時 left 正好指向 target 應該插入的位置。
4. 返回 left 即可。
 */
