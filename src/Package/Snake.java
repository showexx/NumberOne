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
    private int[] xSnake = new int[400];
    private int[] ySnake = new int[400];
    private int xApple;
    private int yApple;
    private int delayTime = 250;
    private boolean inGame = true;
    private boolean moveLeft;
    private boolean moveRight = true;
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

        timer = new Timer(250, this);
        timer.start();
    }

    public void initializeGame() {
        createApple();
        for (int i = 0; i < lengthSnake; i++) {
            xSnake[i] = 48 - i * 16;
            ySnake[i] = 48;
        }
    }

    public void loadImages() {
        Image snakeImage = new ImageIcon("src/snakeCell.png").getImage();
        snake = snakeImage;
        ap.loadImagesApple();
    }

    public void createApple() {
        xApple = random.nextInt(20) * 16;
        yApple = random.nextInt(20) * 16;
    }

    public void createAppleInSnake(){
        for (int i = 1; i < lengthSnake; i++) {
            if (xApple == xSnake[i] && yApple == ySnake[i]) {
                createApple();
            }
        }
    }

    public void eatApple() {
        if (xSnake[0] == xApple && ySnake[0] == yApple) {
            createApple();
            lengthSnake++;
            int x = timer.getDelay();
            timer.setDelay(x - 5);
        }
    }

    public void checkBorder() {
        if (xSnake[0] > 400 || xSnake[0] < 0 || ySnake[0] > 400 || ySnake[0] < 0) {
            inGame = false;
        }
        for (int i = 1; i < lengthSnake; i++) {
            if (inGame && xSnake[0] == xSnake[i] && ySnake[0] == ySnake[i]) {
                inGame = false;
            }
        }
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
            checkBorder();
            eatApple();
            createAppleInSnake();
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
