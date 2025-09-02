
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int romanToInt(String s) {
        // 建立羅馬字元對應表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int prev = 0; // 紀錄前一個字元的數值

        // 從右往左掃描
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));

            // 判斷加或減
            if (curr < prev) {
                total -= curr; // 如果當前數字比右邊小，表示要減
            } else {
                total += curr; // 否則直接加
            }

            prev = curr; // 更新前一個字元
        }

        return total;
    }
}

/*
解題邏輯說明：
1. 羅馬數字通常是由大到小排列，但遇到特殊情況 (如 IV, IX, XL, XC, CD, CM)，小的數字會出現在大的數字左邊，表示要做減法。
   例如：
   - IV = 5 - 1 = 4
   - IX = 10 - 1 = 9
   - MCM = 1000 + (1000 - 100) = 1900

2. 演算法：
   - 建立一個 HashMap 存放羅馬字元與數值的對應。
   - 從右往左掃描字串：
       - 若當前字元的值 < 前一個字元的值，則做減法。
       - 否則做加法。
   - 這樣能自然處理所有「減法情況」。

3. 時間複雜度：O(n)，n 為字串長度 (最大 15)。
4. 空間複雜度：O(1)，HashMap 大小固定。
 */
