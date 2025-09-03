
import java.util.*;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        // 建立 words 的頻率表
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // 遍歷 s，檢查每個可能的起點
        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < wordCount) {
                String sub = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (!wordMap.containsKey(sub)) {
                    break;
                }

                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if (seen.get(sub) > wordMap.get(sub)) {
                    break;
                }

                j++;
            }
            if (j == wordCount) {
                result.add(i);
            }
        }

        return result;
    }
}

/*
解題思路：
1. 計算每個單字長度 wordLen，單字數量 wordCount，總長度 totalLen = wordLen * wordCount
2. 使用 HashMap 記錄 words 的頻率 wordMap
3. 遍歷 s 所有可能的起點 i (0 <= i <= s.length - totalLen)：
   - 對每個起點建立一個 seen Map 記錄當前窗口的單字出現次數
   - 每次取長度為 wordLen 的子字串 sub：
       - 如果 sub 不在 wordMap 中，break
       - 如果 sub 出現次數超過 wordMap，break
       - 否則繼續
   - 如果 j 遍歷到 wordCount，表示這個起點符合條件，加入 result
4. 返回所有符合條件的起點索引 result
5. 時間複雜度：O(n * m)，n = s.length, m = words.length（最壞情況每個起點都遍歷 words）
6. 空間複雜度：O(m)，額外使用 seen Map
 */
