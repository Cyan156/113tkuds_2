
class Solution {

    public String intToRoman(int num) {
        // 羅馬數字的對應表，包含常見的減法組合
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        // 從最大值開始依序匹配
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];         // 減去當前數值
                sb.append(symbols[i]);    // 加上對應符號
            }
        }

        return sb.toString();
    }
}

/*
解題邏輯說明：
1. 羅馬數字的組成有「一般形式」(如 M, C, X, I) 與「特殊減法形式」(如 IV, IX, XL, XC, CD, CM)。
2. 我們預先建立兩個陣列：
   - values[]：對應的整數數值 (含特殊減法組合)。
   - symbols[]：對應的羅馬符號。
3. 從大到小依序比對，能減就減，並把對應符號加到字串。
   例如：
   num = 1994
   - 減 1000 → "M"
   - 減 900 → "CM"
   - 減 90  → "XC"
   - 減 4   → "IV"
   → 最後結果 "MCMXCIV"
4. 時間複雜度：O(1)，因為最多跑固定 13 個值。
5. 空間複雜度：O(1)。
 */
