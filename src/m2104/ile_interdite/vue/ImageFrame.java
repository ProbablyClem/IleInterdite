package m2104.ile_interdite.vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFrame extends JPanel {

    private BufferedImage image;

    public ImageFrame(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image.getHeight() > image.getWidth() || this.getWidth() > this.getHeight()) {
            g.drawImage(image, Math.abs(this.getWidth() - image.getWidth() * this.getHeight() / image.getHeight()) / 2 , 0, image.getWidth() * this.getHeight() / image.getHeight(), this.getHeight(), null);
        } else if (image.getHeight() < image.getWidth() || this.getWidth() < this.getHeight()) {
            g.drawImage(image, 0, Math.abs(this.getHeight() - image.getHeight() * this.getWidth() / image.getWidth()) / 2, this.getWidth(), image.getHeight() * this.getWidth() / image.getWidth(), null);
        } else {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
