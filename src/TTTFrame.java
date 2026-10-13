import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTFrame extends JFrame implements ActionListener {
    private final TTTBoard board;
    private final TTTTileButton[][] buttons;
    private String currentPlayer;

    public TTTFrame() {
        board = new TTTBoard();
        buttons = new TTTTileButton[3][3];
        currentPlayer = "X";

        setTitle("OOP Tic Tac Toe");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Core Layout Setup
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c] = new TTTTileButton(r, c);
                buttons[r][c].addActionListener(this);
                boardPanel.add(buttons[r][c]);
            }
        }

        // Add a quit button panel at the bottom
        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener(e -> System.exit(0));
        JPanel controlPanel = new JPanel();
        controlPanel.add(quitButton);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TTTTileButton clickedButton = (TTTTileButton) e.getSource();
        int row = clickedButton.getRow();
        int col = clickedButton.getCol();

        // 1. Verify move eligibility using the board model
        if (!board.isValidMove(row, col)) {
            JOptionPane.showMessageDialog(this, "Illegal move! Spot already taken.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Perform the Move
        board.setMove(row, col, currentPlayer);
        clickedButton.setText(currentPlayer);

        // 3. Evaluate state
        if (board.isWin(currentPlayer)) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else if (board.isTie()) {
            JOptionPane.showMessageDialog(this, "It's a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else {
            // 4. Switch Player Turn
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private void resetGame() {
        board.clearBoard();
        currentPlayer = "X";
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setText(" ");
            }
        }
    }
}
