
import java.util.Stack;

class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        // 遍歷每個字符
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')'); // 對應的閉括號推入 stack
             }else if (c == '{') {
                stack.push('}'); 
            }else if (c == '[') {
                stack.push(']'); 
            }else {
                // 遇到閉括號時，檢查是否與 stack 頂端匹配
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        // 最後 stack 應該為空，才是有效括號
        return stack.isEmpty();
    }
}

/*
解題思路：
1. 使用堆疊(Stack)來追蹤尚未匹配的開括號。
2. 遇到開括號時，將對應的閉括號推入堆疊。
3. 遇到閉括號時：
   - 若堆疊為空，表示沒有對應的開括號 → false
   - 若堆疊頂端元素不匹配 → false
   - 否則 pop 掉 stack 頂端元素
4. 遍歷完字串後，stack 應該為空，代表所有括號都正確匹配 → true
5. 時間複雜度：O(n)
6. 空間複雜度：O(n)
 */
