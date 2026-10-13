import javax.swing.JButton;
import java.awt.Font;

public class TTTTileButton extends JButton {
    private final int row;
    private final int col;

    public TTTTileButton(int row, int col) {
        this.row = row;
        this.col = col;
        this.setFont(new Font("Arial", Font.BOLD, 40));
        this.setText(" "); // Start empty
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
