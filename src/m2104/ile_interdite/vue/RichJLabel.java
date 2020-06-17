package m2104.ile_interdite.vue;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

    public class RichJLabel extends JLabel {

        private int tracking;
        private int left_x, left_y, right_x, right_y;
        private Color left_color, right_color;

        public RichJLabel(String text, int tracking) {
            super(text, JLabel.CENTER);
            this.tracking = tracking;
            left_x = -3;
            left_y = -3;
            left_color = Color.BLACK.brighter();
            right_x = 2;
            right_y = 3;
            right_color = Color.black.brighter();
        }





        public Dimension getPreferredSize() {
            String text = getText();
            FontMetrics fm = this.getFontMetrics(getFont());

            int w = fm.stringWidth(text);
            w += (text.length()-1)*tracking;
            w += left_x + right_x;
            int h = fm.getHeight();
            h += left_y + right_y;

            return new Dimension(w,h);
        }

        public void paintComponent(Graphics g) {

            ((Graphics2D)g).setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            char[] chars = getText().toCharArray();

            FontMetrics fm = this.getFontMetrics(getFont());

            int h = fm.getAscent();
            int x = 0;

            for(int i=0; i<chars.length; i++) {
                char ch = chars[i];
                int w = fm.charWidth(ch) + tracking;

                g.setColor(left_color);
                g.drawString(""+chars[i],x-left_x,h-left_y);

                g.setColor(right_color);
                g.drawString(""+chars[i],x+right_x,h+right_y);

                g.setColor(getForeground());
                g.drawString(""+chars[i],x,h);

                x+=w;
            }

            ((Graphics2D)g).setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);

        }
        }

