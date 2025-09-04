
class Solution {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // 使用二分搜尋，O(log n)
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 情況 1: 找到目標
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷哪一半是有序的
            if (nums[left] <= nums[mid]) {
                // 左半邊有序
                // 檢查 target 是否落在 [left, mid) 區間
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左半邊
                } else {
                    left = mid + 1;  // target 在右半邊
                }
            } else {
                // 右半邊有序
                // 檢查 target 是否落在 (mid, right] 區間
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target 在右半邊
                } else {
                    right = mid - 1; // target 在左半邊
                }
            }
        }

        // 沒找到 target
        return -1;
    }
}
