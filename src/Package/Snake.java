package Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Snake extends JPanel implements ActionListener {
    private Image snake;
    private int lengthSnake = 3;
    private int[] xSnake = new int[25];
    private int[] ySnake = new int[25];
    private int xApple;
    private int yApple;
    private boolean inGame = true;
    private boolean moveLeft = true;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;
    private Random random = new Random();
    private Timer timer;

    Apple ap = new Apple();

    public Snake() {
        setBackground(Color.gray);
        loadImages();
        initializeGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initializeGame() {
        for (int i = 0; i < lengthSnake; i++) {
            xSnake[i] = 200 - i * 16;
            ySnake[i] = 200;
        }
        timer = new Timer(250,this);
        timer.start();
        createApple();
    }

    public void loadImages() {
        Image snakeImage = new ImageIcon("src/snakeCell.png").getImage();
        snake = snakeImage;
        ap.loadImagesApple();
    }

    public void move() {
        for (int i = lengthSnake; i > 0; i--) {
            xSnake[i] = xSnake[i - 1];
            ySnake[i] = ySnake[i - 1];
        }
        if (moveLeft) {
            xSnake[0] -= 16;
        }
        if (moveRight) {
            xSnake[0] += 16;
        }
        if (moveUp) {
            ySnake[0] -= 16;
        }
        if (moveDown) {
            ySnake[0] += 16;
        }
    }

    public void createApple(){
        xApple = random.nextInt(25) * 16;
        yApple = random.nextInt(25) * 16;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            for (int i = 0; i < lengthSnake; i++) {
                g.drawImage(snake, xSnake[i], ySnake[i], this);
            }
            g.drawImage(ap.getApple(), xApple, yApple, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !moveRight) {
                moveLeft = true;
                moveUp = false;
                moveDown = false;
            }
            if (key == KeyEvent.VK_RIGHT && !moveLeft) {
                moveRight = true;
                moveUp = false;
                moveDown = false;
            }

            if (key == KeyEvent.VK_UP && !moveDown) {
                moveRight = false;
                moveUp = true;
                moveLeft = false;
            }
            if (key == KeyEvent.VK_DOWN && !moveUp) {
                moveRight = false;
                moveDown = true;
                moveLeft = false;
            }
        }
    }
}
