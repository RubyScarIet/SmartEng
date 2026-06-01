package view.admin;
import javax.swing.border.AbstractBorder;
import java.awt.*;
public class RoundedBorder extends AbstractBorder {
    private int radius;
    private Color color;
    private int thickness;
    public RoundedBorder(int radius, Color color) {
        this(radius, color, 1);
    }
    public RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2.dispose();
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(10, 15, 10, 15);
    }
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = 15;
        insets.top = 10;
        insets.right = 15;
        insets.bottom = 10;
        return insets;
    }
}
