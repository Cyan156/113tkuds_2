
import java.util.HashSet;
import java.util.Set;

class Solution {

    public boolean isValidSudoku(char[][] board) {
        // 使用 Set 來檢查每一行、每一列、每個 3x3 子宮格是否有重複
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current = board[i][j];
                if (current != '.') {
                    // 生成三種 key 分別代表行、列、子宮格
                    String rowKey = "row" + i + current;
                    String colKey = "col" + j + current;
                    String boxKey = "box" + (i / 3) + (j / 3) + current;

                    // 如果出現重複，立即返回 false
                    if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

/*
解題思路：
1. 針對每個非空格的數字，用 Set 記錄它出現在哪一行、哪一列、哪個 3x3 子宮格。
2. 三種 key 分別是：
   - rowKey: "row" + 行號 + 數字
   - colKey: "col" + 列號 + 數字
   - boxKey: "box" + (行號/3) + (列號/3) + 數字
3. 每個 key 都試圖加入 HashSet：
   - 若加入失敗 (already exists)，表示重複 -> 返回 false。
4. 全部掃描完沒有重複，即為有效 Sudoku -> 返回 true。
5. 時間複雜度 O(9*9) = O(1)，空間 O(9*9) = O(1)。
 */
