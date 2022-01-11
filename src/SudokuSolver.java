public class SudokuSolver {
    static int BOARD = 3;
    static int BOARD_SIZE = BOARD * BOARD;

    /**
     * Check if the new number is valid for a particular row or not
     */
    private static boolean isRowValid(int[][] board, int numberToCheck, int rowNumber) {
        for (int column = 0; column < BOARD_SIZE; column++) {
            if (board[rowNumber][column] == numberToCheck) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the new number is valid for a particular column or not
     */
    private static boolean isColumnValid(int[][] board, int numberToCheck, int columnNumber) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][columnNumber] == numberToCheck) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the new number is valid for a nXn block or not
     */
    private static boolean isBoxValid(int[][] board, int numberToCheck, int row, int column) {
        int topLeftRow = row - row % BOARD;
        int topLeftColumn = column - column % BOARD;

        for (int i = topLeftRow; i < topLeftRow + BOARD; i++) {
            for (int j = topLeftColumn; j < topLeftColumn + BOARD; j++) {
                if (board[i][j] == numberToCheck) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Main method
     */
    public static void main(String[] a) {
        int[][] sudokuBoard = {
                {0, 0, 3, 1, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 2, 8, 0},
                {0, 2, 8, 0, 0, 6, 3, 1, 5},
                {0, 4, 0, 0, 5, 3, 0, 6, 0},
                {0, 1, 6, 0, 8, 0, 5, 4, 0},
                {0, 7, 0, 6, 1, 0, 0, 3, 0},
                {6, 8, 2, 4, 0, 0, 1, 5, 0},
                {0, 5, 7, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 0, 0, 2, 8, 0, 0},
        };
        System.out.println("Unsolved Board :");
        printBoard(sudokuBoard);

        doTheMagic(sudokuBoard);

        System.out.println("Solved Board :");
        printBoard(sudokuBoard);

        System.out.println("Iterations taken to resolve this board: "+ itrCount);
    }
    static String dottedHrLine ="  -----------------------------------";
    public static void printBoard(int[][] board){
        System.out.println(dottedHrLine);
        for(int row = 0; row < BOARD_SIZE; row ++){
            System.out.print(" | ");
            for(int column = 0; column < BOARD_SIZE; column++){
                if(board[row][column] != 0){
                    System.out.print(board[row][column]);
                }else{
                    System.out.print(" ");
                }

                if(column != BOARD_SIZE-1){
                    if(column % BOARD == BOARD -1 ){
                        System.out.print(" | ");
                    } else{
                        System.out.print(" - ");
                    }
                }
            }
            System.out.print(" | ");
            System.out.println();
            if(row % BOARD == 2)
                System.out.println(dottedHrLine);
        }
    }
    // To count the number of iterations it took to
    static int itrCount = 0;

    /**
     * This method checks and fills all the missing sudoku numbers
     */
    public static boolean doTheMagic(int[][] sudokuBoard) {
        itrCount++;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (sudokuBoard[row][column] == 0) {
                    for (int i = 1; i < 10; i++) {
                        boolean isValid = isEntryValid(sudokuBoard, i, row, column);
                        if (isValid) {
                            sudokuBoard[row][column] = i;

                            boolean result = doTheMagic(sudokuBoard);
                            if (result) {
                                return true;
                            } else {
                                sudokuBoard[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if the new number is valid or not
     */
    public static boolean isEntryValid(int[][] board, int newNumber, int row, int column) {
        return isRowValid(board, newNumber, row) && isColumnValid(board, newNumber, column) && isBoxValid(board, newNumber, row, column);
    }

}
