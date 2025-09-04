
import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);  // 排序方便去重
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start,
            List<Integer> tempList, List<List<Integer>> result) {
        if (target < 0) {
            return;  // 超過目標值，剪枝

        }
        if (target == 0) {       // 找到符合目標的組合
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 去重：同一層相同數字只選一次
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            tempList.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, tempList, result); // i+1，數字只能用一次
            tempList.remove(tempList.size() - 1);  // 回溯
        }
    }
}

/*
解題思路：
1. 先對 candidates 排序，方便後續去重。
2. 使用回溯 (Backtracking) 枚舉所有可能組合。
3. 每層遞迴時：
   - 如果目標值 < 0，直接返回（剪枝）。
   - 如果目標值 == 0，將當前組合加入結果。
4. 為了避免重複組合：
   - 同一層循環中，如果當前數字和上一個數字相同，跳過。
5. 注意：
   - 遞迴下一層使用 i+1，保證每個數字只能使用一次。
6. 時間複雜度：
   - 最壞情況 O(2^n)，n 為 candidates 長度。
7. 空間複雜度：
   - 遞迴棧深 O(n) + 暫存列表。
 */
