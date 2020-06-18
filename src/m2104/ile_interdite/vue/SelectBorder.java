package m2104.ile_interdite.vue;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class SelectBorder extends AbstractBorder {

    private Color color;
    private final int size = 4;
    private final int margin = 2;

    SelectBorder(Color color) {
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);

        g.setColor(this.color);
        g.fillRect(margin, margin, width - 2 * margin, size);
        g.fillRect(margin, height - size - margin, width - 2 * margin, size);
        g.fillRect(margin, margin, size, height - 2 * margin);
        g.fillRect(width - size - margin, margin, size, height - 2 * margin);

        g.setColor(Color.BLACK);
        g.drawRect(margin, margin, width - 2 * margin, height - 2 * margin);
        g.drawRect(margin + size, margin + size, width - 2 * (size + margin), height - 2 * (size + margin));
    }
}
