
class Solution {

    public int divide(int dividend, int divisor) {
        // 特殊情況：溢位處理
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 記錄符號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉成 long 防止溢位
        long dividendL = Math.abs((long) dividend);
        long divisorL = Math.abs((long) divisor);

        int result = 0;

        // 從高位開始位移減法
        for (int i = 31; i >= 0; i--) {
            if ((dividendL >> i) >= divisorL) {
                dividendL -= divisorL << i;
                result += 1 << i;
            }
        }

        return negative ? -result : result;
    }
}

/*
解題思路：
1. 特殊情況：dividend = MIN_VALUE 且 divisor = -1，結果會溢位，返回 Integer.MAX_VALUE。
2. 判斷結果符號：負號 = dividend 與 divisor 异號。
3. 將 dividend 和 divisor 轉為 long 並取絕對值，避免溢位。
4. 使用位移 + 減法實現除法：
   - 從高位 i = 31 到 0，檢查 (dividend >> i) >= divisor
   - 若成立，減去 (divisor << i)，並累加結果 (1 << i)
5. 最後根據符號返回結果
6. 時間複雜度：O(log n)^2（位移 + 減法）
7. 空間複雜度：O(1)
 */
