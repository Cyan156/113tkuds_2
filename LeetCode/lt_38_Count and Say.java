
class Solution {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1"; // 基本情況

                }String prev = countAndSay(n - 1); // 遞迴求前一項
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < prev.length(); i++) {
            // 如果下一個字符不同或到尾，則加入計數和字符
            if (i + 1 == prev.length() || prev.charAt(i) != prev.charAt(i + 1)) {
                sb.append(count).append(prev.charAt(i));
                count = 1; // 重置計數
            } else {
                count++;
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 使用遞迴或迭代生成序列。遞迴方式：
   - countAndSay(n) = 對 countAndSay(n-1) 進行「說出數字」。
2. 核心操作：
   - 遍歷前一項字符串，統計連續相同字符數量。
   - 當遇到不同字符或到尾時，將 count + character 加入結果。
3. 重複這個過程直到 n 項。
4. 時間複雜度：O(2^n)（因為序列長度大約呈指數增長），空間複雜度為遞迴棧深 O(n)。
 */
