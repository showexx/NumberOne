import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends JPanel implements ActionListener {
    private Image snake;
    private int lengthSnake = 3;
    private int[] xSnake = new int[25];
    private int[] ySnake = new int[25];

    public Snake(){
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
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < lengthSnake; i++) {
            g.drawImage(snake,xSnake[i],ySnake[i],this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
