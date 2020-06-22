package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Carte;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    private Integer margin = 0;
    private Carte carte;
    private boolean stretch = false;

    public ImagePanel(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(path);
            e.printStackTrace();
        }

    }

    public ImagePanel(String path, Carte carte){
        this(path);
        this.carte = carte;
    }

    public ImagePanel() {
        this("src/images/empty.png");
    }

    public ImagePanel(String path, Integer margin) {
        this(path);
        this.margin = margin;
    }

    public void setImage(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(path);
            e.printStackTrace();
        }
    }

    public void stretch() {
        this.stretch = true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!stretch) {
            int h = image.getHeight();
            int w = image.getWidth();

            if (h > this.getHeight()) {
                w = w * this.getHeight() / h;
                h = this.getHeight();
            }

            if (w > this.getWidth()) {
                h = h * this.getWidth() / w;
                w = this.getWidth();
            }

            h = h - margin * h/this.getHeight() * 2;
            w = w - margin * w/this.getWidth() * 2;

            Image newImage = this.image.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);

            g.drawImage(newImage, (this.getWidth() - w) / 2, (this.getHeight() - h) / 2, w, h, null);
        } else {
            Image newImage = this.image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING);
            g.drawImage(newImage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    public Carte getCarte() {
        return carte;
    }
}
