
class Solution {

    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int row, int col) {
        // 找到下一個格子
        if (col == 9) {
            row++;
            col = 0;
            if (row == 9) {
                return true; // 填完所有格子

                    }}

        if (board[row][col] != '.') {
            return backtrack(board, row, col + 1); // 已填數字，跳過
        }

        for (char num = '1'; num <= '9'; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;
                if (backtrack(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.'; // 回溯
            }
        }
        return false; // 如果沒有數字可填，回上一層
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        // 檢查行、列、3x3 宮格是否可填
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
            if (board[i][col] == num) {
                return false;
            }
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == num) {
                return false;
            }
        }
        return true;
    }
}

/*
解題思路：
1. 這是一個典型的回溯問題，核心是「試數字 -> 符合規則就繼續 -> 不行就回溯」。
2. backtrack(board, row, col)：
   - 遇到已填數字，直接跳到下一格。
   - 遇到空格 '.'，嘗試填 1~9。
   - 每次填完後，遞迴嘗試下一格。
   - 如果某個數字導致後續無法填滿，回溯，把格子還原成 '.'。
3. isValid() 用來檢查行、列、3x3 宮格是否符合 Sudoku 規則。
4. 終止條件：row == 9 表示整個棋盤填完。
5. 時間複雜度最壞 O(9^(N))，其中 N 是空格數量；空間複雜度 O(N) 由遞迴棧造成。
 */
