import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TTTBoardTest {
    private TTTBoard board;

    @BeforeEach
    public void setUp() {
        // Initializes a fresh board before every single test run
        board = new TTTBoard();
    }

    @Test
    public void testInitialBoardIsEmpty() {
        // Verify that all spots are valid moves on a brand new board
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertTrue(board.isValidMove(r, c), "Initial board cells must be empty");
            }
        }
        assertFalse(board.isWin("X"));
        assertFalse(board.isWin("O"));
        assertFalse(board.isTie());
    }

    @Test
    public void testValidAndInvalidMoves() {
        // Assert a fresh cell is valid
        assertTrue(board.isValidMove(0, 0));

        // Make a move
        board.setMove(0, 0, "X");

        // Assert that the exact same cell is no longer valid
        assertFalse(board.isValidMove(0, 0), "Cell (0,0) should be occupied and invalid");
    }

    @Test
    public void testRowWinCondition() {
        // Simulate a winning row for X across the top row
        board.setMove(0, 0, "X");
        board.setMove(0, 1, "X");
        board.setMove(0, 2, "X");

        assertTrue(board.isWin("X"), "Horizontal win condition failed for row 0");
        assertFalse(board.isWin("O"));
    }

    @Test
    public void testColumnWinCondition() {
        // Simulate a winning column for O down the middle column
        board.setMove(0, 1, "O");
        board.setMove(1, 1, "O");
        board.setMove(2, 1, "O");

        assertTrue(board.isWin("O"), "Vertical win condition failed for column 1");
        assertFalse(board.isWin("X"));
    }

    @Test
    public void testDiagonalWinCondition() {
        // Simulate a winning descending diagonal for X
        board.setMove(0, 0, "X");
        board.setMove(1, 1, "X");
        board.setMove(2, 2, "X");

        assertTrue(board.isWin("X"), "Descending diagonal win condition failed");
    }

    @Test
    public void testTieCondition() {
        /* Simulate a full cat's game (Tie) board layout:
           X O X
           X X O
           O X O
        */
        board.setMove(0, 0, "X"); board.setMove(0, 1, "O"); board.setMove(0, 2, "X");
        board.setMove(1, 0, "X"); board.setMove(1, 1, "X"); board.setMove(1, 2, "O");
        board.setMove(2, 0, "O"); board.setMove(2, 1, "X"); board.setMove(2, 2, "O");

        assertFalse(board.isWin("X"), "X should not be declared winner in a tie");
        assertFalse(board.isWin("O"), "O should not be declared winner in a tie");
        assertTrue(board.isTie(), "Board is full with no winner; tie flag should be true");
    }

    @Test
    public void testClearBoardResetsState() {
        // Play a few moves
        board.setMove(0, 0, "X");
        board.setMove(1, 1, "O");

        // Reset the board array state
        board.clearBoard();

        // Assert everything is perfectly clean again
        assertTrue(board.isValidMove(0, 0));
        assertTrue(board.isValidMove(1, 1));
        assertFalse(board.isTie());
    }
}