package Package;

import javax.swing.*;

public class MainWindow extends JFrame {
    private final int cellsSize = 16;
    private final int numberOfCells = 25;
    private final int size = cellsSize * numberOfCells;


    public MainWindow() {
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(size, size);
        setLocation(400, 400);
        add(new Snake());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();

    }

    public int getCellsSize() {
        return cellsSize;
    }
}
