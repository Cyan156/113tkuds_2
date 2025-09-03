
import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // 回溯函數
    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // 當當前字串長度達到 2*n 時，代表已經生成完整組合
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // 如果還可以添加左括號
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // 如果還可以添加右括號
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}

/*
解題思路：
1. 使用回溯法生成所有可能的括號組合。
2. 追蹤當前生成的字串(current)，以及已經使用的左括號(open)和右括號(close)數量。
3. 條件：
   - open < n → 可以添加左括號
   - close < open → 可以添加右括號
4. 當字串長度達到 2*n 時，加入結果列表。
5. 時間複雜度：O(4^n / sqrt(n))，Catalan 數量
6. 空間複雜度：O(4^n / sqrt(n))，存儲所有結果
 */
