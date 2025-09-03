
import java.util.*;

class Solution {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result; // 空字串，直接回傳空 list
        }

        // 電話按鍵對應表
        String[] mapping = {
            "", // 0 沒有對應字母
            "", // 1 沒有對應字母
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
        };

        backtrack(result, new StringBuilder(), digits, 0, mapping);
        return result;
    }

    // 回溯遞迴
    private void backtrack(List<String> result, StringBuilder current, String digits, int index, String[] mapping) {
        // 如果已經組成完整字串，加入結果
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 取得當前數字對應的字母集合
        String letters = mapping[digits.charAt(index) - '0'];

        // 嘗試每個字母
        for (char c : letters.toCharArray()) {
            current.append(c);                        // 選擇
            backtrack(result, current, digits, index + 1, mapping); // 遞迴
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 題目要求：給定一個只包含數字 2-9 的字串，輸出所有可能的字母組合。
   - 電話按鍵對應表：2 → "abc"，3 → "def"，…，9 → "wxyz"
   - 例如輸入 "23"，可能組合有 ["ad","ae","af","bd","be","bf","cd","ce","cf"]。

2. 演算法設計：回溯 (Backtracking)
   - 建立數字到字母的對應表 (mapping)。
   - 使用遞迴函式 backtrack：
     - 參數包含：目前已組成的字串、目前處理到的 digits 索引。
     - 若 index == digits.length()，代表字串完成 → 加入答案。
     - 否則取出當前數字對應的字母，依序嘗試加入，並遞迴繼續。
     - 每次遞迴後要「回溯」(刪掉最後一個字元)，繼續嘗試其他可能。

3. 時間複雜度：
   - 最多 digits 長度為 4。
   - 每個數字最多對應 4 個字母。
   - 所以最多產生 4^4 = 256 種組合。
   - 複雜度 O(4^n)，n = digits 長度。

4. 空間複雜度：
   - 遞迴深度最多 n (<= 4)，額外使用 O(n) 的 StringBuilder。
   - 總體 O(n)。
 */
