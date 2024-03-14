import java.util.Arrays;

public class PawnMoveTracker {
    
    public static String[] movePawns(String[] moves) {
        ChessBoard cb = new ChessBoard();
        cb.printBoard();
        cb.getRowAndCol("c3");
        return null;
    }

    public static class ChessBoard {
        String[][] board;
        
        public ChessBoard() {
            this.board = new String[8][8];
            this.fill(this.board, 1, "p");
            this.fill(this.board, 6, "P");
        }

        private void fill(String[][] board, int row, String filler) {
            for (int i = 0; i < 8; i++) {
                board[row][i] = filler;
            }
        }

        public void printBoard() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.board[i][j] != null)  System.out.format("%s ", this.board[i][j]);
                    else  System.out.print(". ");
                }
                System.out.println();
            }
        }

        public int[] getRowAndCol(String chessMove) {
            char col = chessMove.charAt(0);
            char row = chessMove.charAt(1);

            System.out.format("%d %d\n", '8' - row, col - 'a');

            return new int[]{row - '0', col - 'a'};
        }
    }
}