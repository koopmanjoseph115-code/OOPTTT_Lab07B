public class TTTBoard {
    private final String[][] grid;
    private static final int ROWS = 3;
    private static final int COLS = 3;

    public TTTBoard() {
        grid = new String[ROWS][COLS];
        clearBoard();
    }

    public void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = " ";
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        return grid[row][col].equals(" ");
    }

    public void setMove(int row, int col, String player) {
        grid[row][col] = player;
    }

    public boolean isWin(String player) {
        return checkRowWin(player) || checkColWin(player) || checkDiagWin(player);
    }

    private boolean checkRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            if (grid[r][0].equals(player) && grid[r][1].equals(player) && grid[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            if (grid[0][c].equals(player) && grid[1][c].equals(player) && grid[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagWin(String player) {
        boolean descDiag = grid[0][0].equals(player) && grid[1][1].equals(player) && grid[2][2].equals(player);
        boolean ascDiag = grid[0][2].equals(player) && grid[1][1].equals(player) && grid[2][0].equals(player);
        return descDiag || ascDiag;
    }

    public boolean isTie() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c].equals(" ")) {
                    return false; // Still empty spots left
                }
            }
        }
        return true; // No empty spots and no winner
    }
}