package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RoundedJPanel extends JPanel {

    private final String text;
    private final Color bgColor;
    private final Color fgColor;

    public RoundedJPanel(String text, Color bgColor, Color fgColor) {
        this.text = text;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    @Override
    public Dimension getPreferredSize() {
        // Calculate width based on text
        FontMetrics fm = getFontMetrics(getFont());
        int textWidth = fm.stringWidth(text) + 30;
        int height = fm.getHeight() + 16;
        int maxWidth = 260; // limit bubble width
        return new Dimension(Math.min(textWidth, maxWidth), height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rounded background
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Draw text
        g2.setColor(fgColor);
        FontMetrics fm = g2.getFontMetrics();
        int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(text, 12, textY);

        g2.dispose();
    }
}
