package Package;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Snake extends JPanel {
    private Image snake;
    private int lengthSnake = 3;
    private int[] xSnake = new int[25];
    private int[] ySnake = new int[25];
    private int xApple;
    private int yApple;
    private boolean inGame = true;
    private Random random = new Random();
    Apple ap = new Apple();

    public Snake() {
        setBackground(Color.gray);
        loadImages();
        initializeGame();

    }

    public void initializeGame() {
        for (int i = 0; i < lengthSnake; i++) {
            xSnake[i] = 200 - i * 16;
            ySnake[i] = 200;
        }
    }

    public void loadImages() {

        Image snakeImage = new ImageIcon("src/snakeCell.png").getImage();
        snake = snakeImage;
        ap.loadImagesApple();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        xApple = random.nextInt(25) + 1;
        yApple = random.nextInt(25) + 1;
        if (inGame) {
            for (int i = 0; i < lengthSnake; i++) {
                g.drawImage(snake, xSnake[i], ySnake[i], this);
            }
            g.drawImage(ap.getApple(), xApple, yApple, this);
        }
    }

}
