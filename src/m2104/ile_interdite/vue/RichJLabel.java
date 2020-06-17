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
            left_color = Color.BLACK;
            right_x = 2;
            right_y = 3;
            right_color = Color.black;
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

        } // end paintComponent()

        public static void main(String[] args) {

            JPanel panel1 = new JPanel( new BorderLayout() );
            panel1.setBackground( Color.BLUE );
            panel1.setBorder( BorderFactory.createBevelBorder( BevelBorder.LOWERED ));

            JPanel interiorPanel = new JPanel( new BorderLayout() );
            panel1.add(interiorPanel, BorderLayout.CENTER);
            RichJLabel label = new RichJLabel("100", 0);


            // drop shadow w/ highlight


            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setVisible( true );
            label.setForeground( Color.YELLOW );

            interiorPanel.add(label, BorderLayout.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 140));
            label.setFont(label.getFont().deriveFont(140f));

            //resize code
            Font labelFont = label.getFont();

            JFrame frame = new JFrame("Label SSCCEE");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(panel1);
            frame.pack();
            frame.setVisible(true);

            int componentWidth = interiorPanel.getWidth();
            String labelText = label.getText();
            int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
            // Find out how much the font can grow in width.
            double widthRatio = (double)componentWidth / (double)stringWidth;
            int newFontSize = (int)(labelFont.getSize() * widthRatio);
            int componentHeight = interiorPanel.getHeight();

            // Pick a new font size so it will not be larger than the height of label.
            int fontSizeToUse = Math.min(newFontSize, componentHeight);
            System.out.println("fontSizeToUse: " + fontSizeToUse);
            if (fontSizeToUse<1) {
                System.err.println("Font size less than 1!");
                System.exit(1);
            }

            // Set the label's font size to the newly determined size.
            label.setFont(new Font(labelFont.getName(), Font.BOLD, fontSizeToUse));
            label.setForeground(Color.gray);
        }}

