
import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start,
            List<Integer> tempList, List<List<Integer>> result) {
        if (target < 0) {
            return; // 超過目標值，剪枝

                }if (target == 0) {      // 找到符合目標的組合
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            tempList.add(candidates[i]);                // 選擇當前數字
            backtrack(candidates, target - candidates[i], i, tempList, result); // 可以重複選
            tempList.remove(tempList.size() - 1);       // 回溯，移除最後一個元素
        }
    }
}

/*
解題思路：
1. 使用回溯 (Backtracking) 枚舉所有可能的組合。
2. 對每個數字，有兩種選擇：
   - 選擇它：將其加入當前組合，目標值減去該數字，並遞迴。
   - 不選它：遞迴到下一個數字。
3. 注意：
   - start 參數控制從哪個索引開始，避免重複組合。
   - 當 target < 0 時，直接剪枝。
   - 當 target == 0 時，將當前組合加入結果。
4. 時間複雜度：
   - 最壞情況 O(2^t) ，t 為目標值。
5. 空間複雜度：
   - 遞迴棧深 O(target) + 暫存列表。
 */
