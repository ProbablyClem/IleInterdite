package m2104.ile_interdite.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Button extends JButton   {

    private int w;
    private int h;
    private RoundRectangle2D re;
    private final BasicStroke st = new BasicStroke(2f);
    private double wRatio = 13;
    private double hRatio = 2;
    private Color color1;
    private Color color2;

    private ImageIcon imgbutt;

    private double arcw;
    private double arch;

    public Button(String name, int w, int h,Color color1,Color color2) {

        super(name);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(w, h));
        setFocusable(false);
        this.color1 = color1;
        this.color2 = color2;
    }
    public Button(String name,int w,int h){
        super(name);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(w, h));
        setFocusable(false);
        this.color1 = Color.GRAY;
        this.color2 = Color.white;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.w = getWidth();
        this.h = getHeight();
        this.arcw = getWidth() / wRatio;
        this.arch = getHeight() / hRatio;
        re = new RoundRectangle2D.Double(st.getLineWidth() / 2, st.getLineWidth() / 2, w - (st.getLineWidth() / 0.5), h - (st.getLineWidth() / 0.5), arcw, arch);
        GradientPaint push = new GradientPaint((w / 2), (h / 2), getColor1(), (w / 2), h, getColor2(), false);
        GradientPaint up = new GradientPaint((w / 2), (h / 2), getColor2(), (w / 2), h,getColor1(), false);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setPaint(push);
            g2.fill(re);
        } else {
            g2.setPaint(up);
            g2.fill(re);
        }

        super.paintComponent(g2);

    }


    @Override
    public void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.setStroke(st);
        g2.draw(re);
        g2.dispose();

    }
    public static void main(String[] args) {

        JFrame j = new JFrame();
        JButton po = new Button("Button",450,40,Color.BLACK,Color.BLUE);
        JButton pa = new Button("JButton",150,30);

        j.setLayout(new FlowLayout(FlowLayout.CENTER));
        j.getContentPane().add(pa);
        j.getContentPane().add(po);
        j.getRootPane().setDefaultButton(po);
        j.setSize(700,700);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

    }
}

