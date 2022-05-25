package Package;

import javax.swing.*;
import java.awt.*;

public class Apple extends JPanel {
    private Image apple;

    public Apple() {
        loadImagesApple();
    }


    public void loadImagesApple() {
        Image appleImage = new ImageIcon("src/appleCell.png").getImage();
        apple = appleImage;
    }

    public Image getApple() {
        return apple;
    }
}
