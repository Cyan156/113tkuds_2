
class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 從第二個字串開始，逐一比較
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到當前字串以它為開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }
}

/*
解題邏輯說明：
1. 共同前綴 (Common Prefix) 的特性：
   - 若字串陣列中有一個共同前綴，那麼所有字串都必須以這個前綴開頭。
   - 若遇到不符合的字串，就要縮短 prefix。

2. 解法流程：
   - 先假設第一個字串 strs[0] 是共同前綴。
   - 從第二個字串開始，逐一檢查：
       - 用 indexOf(prefix) 判斷當前字串是否以 prefix 開頭。
       - 若不是，縮短 prefix（刪掉最後一個字元），再檢查一次。
       - 持續縮短直到符合，或 prefix 變成空字串。
   - 最後留下的 prefix 就是最長共同前綴。

3. 範例：
   strs = ["flower","flow","flight"]
   - prefix = "flower"
   - 與 "flow" 比較 → 縮短成 "flow"
   - 與 "flight" 比較 → 縮短成 "fl"
   → 最後結果 = "fl"

4. 時間複雜度：O(S)，S 為所有字元總數。
5. 空間複雜度：O(1)。
 */
