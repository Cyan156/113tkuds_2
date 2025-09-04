

class Solution {

    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxLen = 0;

        // Left -> Right
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++; 
            }else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) { // 不可能再延伸成合法，重置
                left = right = 0;
            }
        }

        // Right -> Left（處理左括號偏多的情況）
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                left++; 
            }else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) { // 不可能再延伸成合法，重置
                left = right = 0;
            }
        }

        return maxLen;
    }
}

/*
解題思路（Two-pass，O(n) 時間 / O(1) 空間）：
1) 從左到右掃描：'(' 計入 left，')' 計入 right。
   - 若 left == right，更新答案為 2*right。
   - 若 right > left，當前段不可能再成為合法子串，重置計數器。
2) 從右到左再掃一次（鏡像處理左括號偏多的情況）：
   - 同理，left == right 時更新；left > right 時重置。
為何可行：合法子串內左右括號數相等；出現不平衡時，當前區段無法再延伸成合法，需要重新起算。
 */
