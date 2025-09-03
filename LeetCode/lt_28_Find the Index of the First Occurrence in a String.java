
class Solution {

    public int strStr(String haystack, String needle) {
        // 特殊情況：needle 為空字串，根據題目返回 0
        if (needle.length() == 0) {
            return 0;
        }

        int n = haystack.length();
        int m = needle.length();

        // 遍歷 haystack，尋找長度為 m 的子字串
        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i; // 找到第一個匹配，返回索引
            }
        }

        return -1; // 未找到
    }
}

/*
解題思路：
1. 特殊情況：若 needle 為空字串，直接返回 0。
2. 遍歷 haystack：
   - 對每個起點 i，取長度為 needle.length() 的子字串 substring(i, i + m)
   - 如果子字串等於 needle，返回 i
3. 若整個 haystack 遍歷完仍未匹配，返回 -1
4. 時間複雜度：O((n-m+1)*m)，n = haystack 長度，m = needle 長度
5. 空間複雜度：O(1)（Java substring 不額外複製字串）
 */
