import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];
    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }
    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" "));
    }
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player) {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
                    return true;
                }
                if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        }
        return false;
    }

    // IGNORE THIS IT DID NOT WORK
//    private static boolean isTie() {
//        boolean tie = false;
//        for (int row = 0; row < ROW; row++) {
//            for (int col = 0; col < COL; col++) {
//                if (board[row][col].equals(" ")) {
//                    tie = false;
//                }
//                for(int row1 = 0; row1 < board.length && !tie; row1++) {
//                    if(board[0][row] == "X" || (board[1][row] == "X") || (board[2][row] == "X")) {
//                        if((board[0][row] == "O")|| (board[1][row] == "O") || (board[2][row] == "O")) {
//                            tie = true;
//                        }
//                    }
//                }
//                if(!tie) {
//                    if(board[0][0] == "X" || board[1][1] == "X" || board[2][2] == "X") {
//                        if(board[0][0] == "O" || board[1][1] == "O" || board[2][2] == "O") {
//                            tie = true;
//                        }
//                    }
//                }
//                if(!tie) {
//                    if(board[0][2] == "X" || board[1][1] == "X" || board[2][0] == "X") {
//                        if(board[0][2] == "O" || board[1][1] == "O" || board[2][0] == "O") {
//                            tie = true;
//                        }
//                    }
//                }
//            }
//        }
//        return tie;
//    }
private static boolean isTie() {
    for (int row = 0; row < ROW; row++) {
        for (int col = 0; col < COL; col++) {
            if (board[row][col].equals(" ")) {
                return false;
            }
        }
    }
    return true;
}
    private static void displayBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col] == "") {
                    System.out.printf("| ", board[row][col]);
                }
                else {
                    System.out.print("|" + board[row][col]);
                }
            }
            System.out.println("|");
        }
    }
    private static void playGame() {
        Scanner in = new Scanner(System.in);
        String player = "X";
        boolean done = false;
        do {
            int row = SafeInput.getRangedInt(in, "Player " + player + ", enter row (1-3)", 1, ROW);
            int col = SafeInput.getRangedInt(in, "Player " + player + ", enter column (1-3)", 1, COL);

            row--;
            col--;

            if (isValidMove(row, col)) {
                board[row][col] = player;
                System.out.println();
                displayBoard();

                if (isWin(player)) {
                    System.out.println("\nPlayer " + player + " wins.");
                    break;
                }
                else if (isTie()) {
                    System.out.println("\nIt's a tie.");
                    break;
                }

                if (player.equals("X")){
                    player = "O";
                }
                else {
                    player = "X";
                }
            }
            else {
                System.out.println("Invalid move!");
            }

        }
        while (!done);
    }

    public static void main(String[] args) {
        clearBoard();
        boolean playAgain;
        do {
            displayBoard();
            playGame();
            playAgain = SafeInput.getYNConfirm(new Scanner(System.in), "Would you like to play again?");
            if (playAgain) {
                clearBoard();
            }
        } while (playAgain);
    }
}