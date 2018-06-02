package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainFrameLogo extends JPanel {

    private BufferedImage image;
    private final String pathToImage = "assets/mainFrameLogo/logo.png";


    public MainFrameLogo() {
//        setPreferredSize(new Dimension(768, 240));
        setPreferredSize(new Dimension(1000, 240));
        try {
            image = ImageIO.read(new File(pathToImage));
        } catch (Exception exc) {
            System.out.println("Failed to display the image");
            System.exit(-1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 120, 0, this);
    }
}
