
class Solution {

    public int removeElement(int[] nums, int val) {
        int writeIndex = 0; // 指向下一個要寫入非 val 元素的位置

        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != val) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // 返回非 val 元素的數量 k
    }
}

/*
解題思路：
1. 使用雙指標：
   - readIndex 遍歷整個陣列
   - writeIndex 指向下一個要寫入的非 val 元素位置
2. 遍歷陣列：
   - 如果 nums[readIndex] != val，將其寫入 nums[writeIndex]，並將 writeIndex++
   - 否則忽略
3. 最後 writeIndex 就是非 val 元素的數量 k
4. 時間複雜度：O(n)，每個元素遍歷一次
5. 空間複雜度：O(1)，原地修改陣列
 */
