
class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int writeIndex = 1; // 指向下一個要寫入的唯一元素位置

        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != nums[readIndex - 1]) {
                // 發現新元素，寫入 writeIndex 並移動 writeIndex
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // 返回唯一元素的數量 k
    }
}

/*
解題思路：
1. 使用雙指標：
   - readIndex 遍歷整個陣列
   - writeIndex 指向下一個唯一元素要寫入的位置
2. 遍歷陣列：
   - 如果 nums[readIndex] != nums[readIndex - 1]，表示遇到新元素
   - 將新元素寫入 nums[writeIndex]，writeIndex++
3. 最後 writeIndex 就是唯一元素的數量 k
4. 時間複雜度：O(n)，每個元素遍歷一次
5. 空間複雜度：O(1)，原地修改陣列
 */
