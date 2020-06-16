package m2104.ile_interdite.vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFrame extends JPanel {

    private BufferedImage image;
    private Integer margin = 0;

    public ImageFrame(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageFrame(String path, Integer margin) {
        this(path);
        this.margin = margin;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image.getHeight() > image.getWidth() || this.getWidth() > this.getHeight()) {
            g.drawImage(image, Math.abs(this.getWidth() - image.getWidth() * this.getHeight() / image.getHeight()) / 2 + margin , margin, image.getWidth() * (this.getHeight()-margin*2) / image.getHeight(), this.getHeight() - margin*2, null);
        } else if (image.getHeight() < image.getWidth() || this.getWidth() < this.getHeight()) {
            g.drawImage(image, margin, Math.abs(this.getHeight() - image.getHeight() * this.getWidth() / image.getWidth()) / 2  + margin, (this.getWidth() - margin*2), image.getHeight() * (this.getWidth() - margin*2) / image.getWidth(), null);
        } else {
            g.drawImage(image, margin, margin, (this.getWidth() - margin*2), (this.getHeight() - margin*2), null);
        }
    }
}
