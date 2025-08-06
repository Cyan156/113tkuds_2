
public class TicTacToeBoard {

    private char[][] board;
    private int movesCount;

    public TicTacToeBoard() {
        board = new char[3][3];
        movesCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean placePiece(int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        if (board[row][col] != ' ') {
            return false;
        }
        board[row][col] = player;
        movesCount++;
        return true;
    }

    public boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    public boolean isDraw() {
        return movesCount == 9 && !checkWin('X') && !checkWin('O');
    }

    public boolean isGameOver() {
        return checkWin('X') || checkWin('O') || isDraw();
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.printf(" %c | %c | %c \n", board[i][0], board[i][1], board[i][2]);
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
    }

    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        game.placePiece(0, 0, 'X');
        game.placePiece(1, 1, 'O');
        game.placePiece(0, 1, 'X');
        game.placePiece(2, 2, 'O');
        game.placePiece(0, 2, 'X');
        game.printBoard();
        System.out.println("X 獲勝？ " + game.checkWin('X'));
        System.out.println("遊戲結束？ " + game.isGameOver());
    }
}
