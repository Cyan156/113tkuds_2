
class Solution {

    public int maxArea(int[] height) {
        int left = 0;                  // 左指針
        int right = height.length - 1; // 右指針
        int maxWater = 0;              // 紀錄最大容積

        while (left < right) {
            // 計算當前容器的寬與高
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = width * h;

            // 更新最大值
            maxWater = Math.max(maxWater, area);

            // 移動指針：永遠移動較矮的一邊
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}

/*
解題邏輯說明：
1. 使用「雙指針」方法，從陣列最左與最右開始。
2. 每次計算由 left 與 right 構成的容器容量：
      容量 = (right - left) * min(height[left], height[right])
3. 更新最大容積 maxWater。
4. 移動高度較小的指針：
      - 若左邊比較矮，移動 left++。
      - 若右邊比較矮或相等，移動 right--。
   因為高度由較小的邊決定，不移動它不可能增加容積。
5. 重複直到 left 與 right 相遇，回傳最大值。
時間複雜度：O(n)，只需掃描一次。
空間複雜度：O(1)。
 */
