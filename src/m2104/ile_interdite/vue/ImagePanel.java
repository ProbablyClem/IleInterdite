package m2104.ile_interdite.vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    private Integer margin = 0;

    public ImagePanel(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(path);
            e.printStackTrace();
        }
    }

    public ImagePanel() {
        this("src/images/empty.png");
    }

    public ImagePanel(String path, Integer margin) {
        this(path);
        this.margin = margin;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // ce code pas lisible rend les images lisibles (échange équivalent)
        if (image.getHeight() > image.getWidth() || this.getWidth() > this.getHeight()) {
            Image newImage = this.image.getScaledInstance(this.image.getWidth() * (this.getHeight()-margin*2) / this.image.getHeight(), this.getHeight() - margin*2, Image.SCALE_AREA_AVERAGING);
            g.drawImage(newImage, Math.abs(this.getWidth() - image.getWidth() * this.getHeight() / image.getHeight()) / 2 + margin , margin, image.getWidth() * (this.getHeight()-margin*2) / image.getHeight(), this.getHeight() - margin*2, null);
        } else if (image.getHeight() < image.getWidth() || this.getWidth() < this.getHeight()) {
            Image newImage = this.image.getScaledInstance((this.getWidth() - margin*2), image.getHeight() * (this.getWidth() - margin*2) / image.getWidth(), Image.SCALE_AREA_AVERAGING);
            g.drawImage(newImage, margin, Math.abs(this.getHeight() - image.getHeight() * this.getWidth() / image.getWidth()) / 2  + margin, (this.getWidth() - margin*2), image.getHeight() * (this.getWidth() - margin*2) / image.getWidth(), null);
        } else {
            Image newImage = this.image.getScaledInstance((this.getWidth() - margin*2), (this.getHeight() - margin*2), Image.SCALE_AREA_AVERAGING);
            g.drawImage(newImage, margin, margin, (this.getWidth() - margin*2), (this.getHeight() - margin*2), null);
        }
    }
}
