package m2104.ile_interdite.vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class TitleFrame extends JFrame {

    JLabel title;
    JPanel content;
    JPanel main;
    TitleFrame win = this;
    Point pos = null;
    TitleFrame(String title) {


        this.title = new JLabel(title, SwingConstants.CENTER);
        this.title.setBorder(new EmptyBorder(10, 10, 6, 10));
        this.title.setOpaque(true);
        this.title.setBackground(Color.BLACK);
        this.title.setForeground(Color.WHITE);
        this.title.setFont(new Font("Roboto",Font.BOLD,20));

        this.title.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (pos == null) {
                    pos = new Point(e.getXOnScreen(), e.getYOnScreen());
                }
                win.setLocation((int) (win.getLocationOnScreen().getX() + (e.getXOnScreen() - pos.getX())), (int) (win.getLocationOnScreen().getY() + (e.getYOnScreen() - pos.getY())));
                pos = new Point(e.getXOnScreen(), e.getYOnScreen());
            }
        });

        this.title.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pos = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.main = new JPanel(new GridLayout(1, 1));
        this.main.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10));
        main.setBackground(Color.white);
        this.content = new JPanel(new GridLayout(1, 1));
        this.content.add(this.main);
        this.content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

        this.setLayout(new BorderLayout());
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.content, BorderLayout.CENTER);
        this.setUndecorated(true);
    }

    public void centrer() {
        this.setLocationRelativeTo(null);
    }

}
