package m2104.ile_interdite.vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TitleFrame extends JFrame {

    JLabel title;
    JPanel main;

    TitleFrame(String title) {
        this.title = new JLabel(title, SwingConstants.CENTER);
        this.title.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.title.setOpaque(true);
        this.title.setBackground(Color.BLACK);
        this.title.setForeground(Color.WHITE);
        this.title.setFont(new Font("Roboto",Font.BOLD,20));
        this.main = new JPanel(new GridLayout(1, 1));

        this.setLayout(new BorderLayout());
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.main, BorderLayout.CENTER);
        this.setUndecorated(true);
    }

    public void centrer() {
        this.setLocationRelativeTo(null);
    }

}
