
class Solution {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        // 找左邊界
        result[0] = binarySearch(nums, target, true);

        // 如果沒有找到，直接回傳 [-1, -1]
        if (result[0] == -1) {
            return result;
        }

        // 找右邊界
        result[1] = binarySearch(nums, target, false);

        return result;
    }

    // 左/右邊界二分搜尋
    private int binarySearch(int[] nums, int target, boolean findFirst) {
        int left = 0, right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid; // 記錄當前匹配位置
                if (findFirst) {
                    // 尋找左邊界，縮小右邊界
                    right = mid - 1;
                } else {
                    // 尋找右邊界，縮小左邊界
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid - 1;
            }
        }

        return index;
    }
}

/*
解題思路：
1. 題目要求 O(log n)，所以使用二分搜尋。
2. 分兩次二分搜尋：
   - 第一次找 target 的左邊界 (findFirst = true)
     -> 當 nums[mid] == target 時，仍然往左邊縮小範圍找第一個位置。
   - 第二次找 target 的右邊界 (findFirst = false)
     -> 當 nums[mid] == target 時，往右邊縮小範圍找最後一個位置。
3. 如果找不到 target，回傳 [-1, -1]。
4. 最終返回左右邊界組成的陣列。
 */
