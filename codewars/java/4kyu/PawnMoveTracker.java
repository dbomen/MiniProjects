public class PawnMoveTracker {
    
    public static String[][] movePawns(String[] moves) {
        ChessBoard cb = new ChessBoard();
        for (int i = 0; i < moves.length; i++) {
            try {
                cb.move(moves[i]);
            } catch (RuntimeException e) {
                return new String[][]{{String.format("%s is invalid", moves[i])}};
            }
        }

        return cb.board;
    }

    public static class ChessBoard {
        String[][] board;
        boolean whiteToMove;
        
        public ChessBoard() {
            this.board = new String[8][8];
            this.fill();
            this.whiteToMove = true;
        }

        private void fill() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if      (i == 1)  this.board[i][j] = "p";
                    else if (i == 6)  this.board[i][j] = "P";
                    else              this.board[i][j] = ".";
                }
            }
        }

        public int[] getRowAndCol(String chessMove) {
            char col = chessMove.charAt(0);
            char row = chessMove.charAt(1);
            char who = 'x';

            if (row == 'x') {
                col = chessMove.charAt(2);
                row = chessMove.charAt(3);
                who = chessMove.charAt(0); who -= 'a';
            }

            return new int[]{'8' - row, col - 'a', who};
        }

        public void move(String chessMove) {
            int[] move = getRowAndCol(chessMove);
            int row = move[0];
            int col = move[1];
            int who = move[2];
            boolean takingMove = move[2] != 'x';
            String beforeMoving = this.board[row][col];

            if (takingMove) { // is taking move

                boolean isRightTaking = (who - col > 0) ? true : false;
                isRightTaking = (this.whiteToMove) ? isRightTaking : !isRightTaking;

                if (this.whiteToMove) { // white to move
                    if (isRightTaking) { // capture on the right
                        if (this.board[row + 1][col + 1] == "P" && this.board[row][col] == "p")  makeMove(new int[]{row + 1, col + 1}, move);
                    } else { // capture on the left
                        if (this.board[row + 1][col - 1] == "P" && this.board[row][col] == "p")  makeMove(new int[]{row + 1, col - 1}, move);
                    }
                } else { // black to move
                    if (isRightTaking) { // capture on the right
                        if (this.board[row - 1][col - 1] == "p" && this.board[row][col] == "P")  makeMove(new int[]{row - 1, col - 1}, move);
                    } else { // capture on the left
                        if (this.board[row - 1][col + 1] == "p" && this.board[row][col] == "P")  makeMove(new int[]{row - 1, col + 1}, move);
                    }
                }
            } else { // is normal move
                if (!(this.board[row][col] != ".")) {
                    if (this.whiteToMove) { // white to move
                        if (this.board[row + 1][col] == "P")                  makeMove(new int[]{row + 1, col}, move);
                        if (row + 2 == 6 && this.board[row + 2][col] == "P")  makeMove(new int[]{row + 2, col}, move);
                    } else { // black to move
                        if (this.board[row - 1][col] == "p")                  makeMove(new int[]{row - 1, col}, move);
                        if (row - 2 == 1 && this.board[row - 2][col] == "p")  makeMove(new int[]{row - 2, col}, move);
                    }
                }
            }

            if (this.board[row][col] == beforeMoving)  throw new RuntimeException();
            
            this.whiteToMove = !this.whiteToMove;
        }

        public void makeMove(int[] currentPosition, int[] movingTo) {
            this.board[currentPosition[0]][currentPosition[1]] = ".";
            this.board[movingTo[0]][movingTo[1]] = (whiteToMove) ? "P" : "p";
        }
    }
}